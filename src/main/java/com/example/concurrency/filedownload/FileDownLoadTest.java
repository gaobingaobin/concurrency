package com.example.concurrency.filedownload;

/**
 * @Description
 * @Author gaobin
 * @Date 2022-01-14 20:44
 */
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FileDownLoadTest {


    private static final int TCOUNT = 10;

    private CountDownLatch latch = new CountDownLatch(TCOUNT);

    private long completeLength = 0;

    private long fileLength;
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        long begin = System.currentTimeMillis();
        String fileUrl = "https://aiv-global-feed-prod.s3.amazonaws.com/Global/Metadata/2022-01-14/09/territory%3DCO/part-00000-ef08b281-8689-4fe0-94c0-85f4599abd5e.c000.csv?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFwaCXVzLWVhc3QtMSJHMEUCIDD6oFFvKn2IlhxGnFEzYErTulrxppyPLSX9sxDnALIeAiEA642Ah52ffGOyEq7D14EBI00X0oHkR%2FDQHLYdg2m0tJgquwIIdRADGgw1ODE3ODA5NDc0MzgiDH%2FQuNHKYRAQ7KfL3SqYAmxEX70PNUpsL0J%2FdExLbzWENFD7rqPFocOcj6joiDD5t64YG5gdlCjvnEEp4Z%2B4n%2BD%2F15dGPRMPqJwZxDEf1wdUEk4mqFAQ8Z8qIXrp7fW4XkO9IMYBCv2w0J7wOccM5lztZnapKwdZZEWAMxNjcNi7SJORki2ziqTX7i5da4HGNIf9%2BNqzxzpzQr2tKhjye6rVj3t90kZIqJsP2dLfb%2FxbW2D6zl9FF2uo7XRnt87JPvH8CNDmL9XbTtxy6rFmMcyt1G%2FfKa%2FU8KEZ%2F2hXpm%2B6V4gJl8z9Ro4uqznQXTMt44GsJZnxpoHlK6VT76sMALQmtjkmKIg3IoT%2FgbStJ3RZuaf3UhvNaVa4YsGS1n86UL6mpvXXOTswwcuFjwY6mgH49x%2FIRj0I402186fWFK4phCTYgENpqE8LtVWzK18yZpWbF9%2BguBPax0%2BuxcF5LjpQqKQ2Eb53aEcd3CMxSqj27F3S%2FAKFApQnV2Mv0BYUOg6OVj5ZeVf3frnk3SpwcB%2B6NOk%2BCIDm597aTsY65f4BYs4DoBC5NDAH1GWtvReBx9f3l%2FifZS8mdMRbvq4wySRxBiWxwGIsnyRi&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220114T121522Z&X-Amz-SignedHeaders=host&X-Amz-Expires=17999&X-Amz-Credential=ASIAYO5GYYHXDCS7IOX7%2F20220114%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=e89a5df487d59b205d93fa98155b9c6dd7817cefe569e634e0d5c1c2024c1cdf";
        new FileDownLoadTest().download(fileUrl,"f:/primevideo/test.csv");
        System.out.println(System.currentTimeMillis() - begin);
    }


    public void download(String address,String fileName) throws Exception{
        ExecutorService service = Executors.newFixedThreadPool(TCOUNT);
        URL url = new URL(address);
        URLConnection cn = url.openConnection();
//        cn.setRequestProperty("Referer", "http://www.test.com");
        fileLength = cn.getContentLength();
        long packageLength = fileLength/TCOUNT;
        long leftLength = fileLength%TCOUNT;
        RandomAccessFile file = new RandomAccessFile(fileName,"rw");
        //计算每个线程请求文件的开始和结束位置
        long pos = 0;
        long endPos = pos + packageLength;
        for(int i=0; i<TCOUNT; i++){
            if(leftLength >0){
                endPos ++;
                leftLength--;
            }
            service.execute(new DownLoadThread(url, file, pos, endPos));
            pos = endPos;
        }
        latch.await();
    }

    class DownLoadThread implements Runnable{

        private URL url;
        private RandomAccessFile file;
        private long from;
        private long end;

        DownLoadThread(URL url, RandomAccessFile file, long from, long end){
            this.url = url;
            this.file = file;
            this.from = from;
            this.end = end;
        }

        @Override
        public void run() {
            long pos = from;
            byte[] buf = new byte[512];
            try {
                HttpURLConnection cn = (HttpURLConnection) url.openConnection();
                cn.setRequestProperty("Range", "bytes=" + from + "-" + end);
                if(cn.getResponseCode() != 200 && cn.getResponseCode()!=206){
                    run();
                    return;
                }
                BufferedInputStream bis = new BufferedInputStream(cn.getInputStream());
                int len ;
                while((len = bis.read(buf)) != -1){
                    synchronized(file){
                        file.seek(pos);
                        file.write(buf, 0, len);
                    }
                    pos += len;
                    completeLength +=len;
                    System.out.println(completeLength * 100 /fileLength + "%");
                }
                cn.disconnect();
                latch.countDown();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}
