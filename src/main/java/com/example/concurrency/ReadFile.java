package com.example.concurrency;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.internal.util.file.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author gaobin
 * @Date 2021-12-13 15:49
 */
@Slf4j
public class ReadFile {


    public static void main(String[] args) throws IOException {


//     String url = "https://aiv-global-feed-prod.s3.amazonaws.com/Global/Metadata/2022-01-14/09/territory%3DCO/part-00000-ef08b281-8689-4fe0-94c0-85f4599abd5e.c000.csv?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEFwaCXVzLWVhc3QtMSJHMEUCIDD6oFFvKn2IlhxGnFEzYErTulrxppyPLSX9sxDnALIeAiEA642Ah52ffGOyEq7D14EBI00X0oHkR%2FDQHLYdg2m0tJgquwIIdRADGgw1ODE3ODA5NDc0MzgiDH%2FQuNHKYRAQ7KfL3SqYAmxEX70PNUpsL0J%2FdExLbzWENFD7rqPFocOcj6joiDD5t64YG5gdlCjvnEEp4Z%2B4n%2BD%2F15dGPRMPqJwZxDEf1wdUEk4mqFAQ8Z8qIXrp7fW4XkO9IMYBCv2w0J7wOccM5lztZnapKwdZZEWAMxNjcNi7SJORki2ziqTX7i5da4HGNIf9%2BNqzxzpzQr2tKhjye6rVj3t90kZIqJsP2dLfb%2FxbW2D6zl9FF2uo7XRnt87JPvH8CNDmL9XbTtxy6rFmMcyt1G%2FfKa%2FU8KEZ%2F2hXpm%2B6V4gJl8z9Ro4uqznQXTMt44GsJZnxpoHlK6VT76sMALQmtjkmKIg3IoT%2FgbStJ3RZuaf3UhvNaVa4YsGS1n86UL6mpvXXOTswwcuFjwY6mgH49x%2FIRj0I402186fWFK4phCTYgENpqE8LtVWzK18yZpWbF9%2BguBPax0%2BuxcF5LjpQqKQ2Eb53aEcd3CMxSqj27F3S%2FAKFApQnV2Mv0BYUOg6OVj5ZeVf3frnk3SpwcB%2B6NOk%2BCIDm597aTsY65f4BYs4DoBC5NDAH1GWtvReBx9f3l%2FifZS8mdMRbvq4wySRxBiWxwGIsnyRi&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220114T121522Z&X-Amz-SignedHeaders=host&X-Amz-Expires=17999&X-Amz-Credential=ASIAYO5GYYHXDCS7IOX7%2F20220114%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=e89a5df487d59b205d93fa98155b9c6dd7817cefe569e634e0d5c1c2024c1cdf";
//     String fileName = "test"+1+".csv";
//     String savePath = "f:/primevideo";
//        downLoadFromUrl(url,fileName,savePath);
//        //固定容器大小为10
//        final AtomicInteger count = new AtomicInteger(0);
//
        BlockingQueue<String> medias = new ArrayBlockingQueue<String>(10000);
        List<String> fileUrls =  getFileUrlList();
        log.info("文件地址已获取：{}",fileUrls.toString());

        ExecutorService executorProduce = Executors.newFixedThreadPool(3);
        for (int i=0; i<fileUrls.size();i++) {
//            executorProduce.execute(new Produce(medias,fileUrls.get(i),count));
              String url = fileUrls.get(i);
              String fileName = "test"+i+".csv";
              String savePath = "f:/primevideo";

              executorProduce.execute(() ->{
                  try {
                      downLoadFromUrl(url,fileName,savePath);
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              });

        }
//        log.info("总条数：{}",count);
        log.info("开始执行消费线程------------------------------------------------------------------------------------");
//        ExecutorService executorConsume = Executors.newFixedThreadPool(50);
//        executorConsume.submit(new Consume(medias));
//        executorConsume.shutdown();
        executorProduce.shutdown();


        String fileUrl = "https://aiv-global-feed-prod.s3.amazonaws.com/DE/Metadata/2021-12-14/03/territory%3DDE/part-00001-c795c329-b5ee-42d5-beba-828a4d3178bc.c000.csv?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEG8aCXVzLWVhc3QtMSJGMEQCIDm423GWxwEcfy1CoYKOpBJ3OKkP5lxuovlOqfSGprxeAiAVUcSKZszDuJeAT1xBrUMhO28zgYC%2Bm194eN%2B4x1Cvtiq7AghYEAIaDDU4MTc4MDk0NzQzOCIM2bT4RnboraAtpJ1bKpgCcjOdA1rWOUDqAN6ii%2B8Vr0%2Be4T4LfwPrei7YsM7HKTNOjMZyghx1ekcs87U%2F3Grt0UBJFWi4FYXbzqHHDTKpW507SHx13DFnyy%2BfGdXLyW3D6SUYtq8LH7R9p3xpfcNl6KrdCK6%2Fu1iyxM3xlmmFHfUdUaQXuwagQfQA70a2H9u62iJqEZKZe0jHIRGa%2B1P%2FBDahqxpwmaucW0nERbxr%2F3%2F5QvF7wNeApVhboioqCljv7N8KPqCvvuAjBB99HcD%2B8phoNwuMpYqmztA88XWM2VaOgcz0YEZBvaTYalnA6YLU7EEh7jE22LvDhLZa4r4In5KlONmWhjh95hq6QWj5RHNnB1x9MnML3LRbWUMbAvST1eJIEq9WYTDA%2BuCNBjqbAZ4BvOkx%2B%2FhN%2BEFYU8A2q5IrYGjz3IfjMqg7k4p6iBfOZZaesg7iYdYHHYuXiYR1HNbXFY38vVwFSTafYw00C5rsoy1p8nsm1SI3dErawdUFAwMJu329zyYLihtVzrPFopZMIrb9RTy%2FTHsxAKsZiifmXiX2ia%2FTLZJqNdUweyM73ms66ARgjPAei4Mgww8UWrsnnok1dub84ViS&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20211214T070329Z&X-Amz-SignedHeaders=host&X-Amz-Expires=17999&X-Amz-Credential=ASIAYO5GYYHXFJI5IP6U%2F20211214%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=d0a3122e77e584514b373ab332a01d28c3f1f2737f4b6c8fab3fe64691a5bede";
//        Thread produce = new Thread(new Produce(medias,fileUrl));

//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        executor.submit(new Consume(medias));
//        executor.shutdown();
//        Thread consume1 = new Thread(new Consume(medias));
//        Thread consume2 = new Thread(new Consume(medias));
//        Thread consume3 = new Thread(new Consume(medias));
//        Thread consume4 = new Thread(new Consume(medias));
////        produce.start();
//        consume1.start();
//        consume2.start();
//        consume3.start();
//        consume4.start();

    }

    static class Produce implements Runnable{
        private String fileUrl;
        private BlockingQueue<String> medias;
        private AtomicInteger atomicInteger;

        Produce(BlockingQueue<String> medias,String fileUrl,AtomicInteger atomicInteger){
            this.medias = medias;
            this.fileUrl = fileUrl;
            this.atomicInteger = atomicInteger;
        }

        @Override
        public void run() {
            try {
                readFile(fileUrl,medias,atomicInteger);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    static class Consume implements Runnable{
        private BlockingQueue<String> medias;
        private boolean flag = true;

        Consume(BlockingQueue<String> medias){
            this.medias = medias;
        }

//        public  void writeMyql(){
//            System.out.println("消费者线程 - " + Thread.currentThread().getName() + "启动");
//            int i = 0;
//            long start = System.currentTimeMillis(); // 记录起始时间
//            //Thread.sleep(3);  //用于测试当生产者生产满10个食品后是否进入等待状态
//            try {
//                while (flag){
//                    String media = medias.take();
//                    if("end".equals(media)){
//                        flag = false;
//                        medias.put("end");//将结束标志放进队列  以防别的消费者线程看不到
//
//                    }else{
//                        log.info(media);
//                        ++i;
//                    }
//
//                }//while
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            long end = System.currentTimeMillis();
//            log.info("消费者线程{}结束,消费数据{},时间：{}",Thread.currentThread().getName(),i,(end-start)/1000);
//        }

        @Override
        public void run() {
            System.out.println("消费者线程 - " + Thread.currentThread().getName() + "启动");
            int i = 0;
            long start = System.currentTimeMillis(); // 记录起始时间
            //Thread.sleep(3);  //用于测试当生产者生产满10个食品后是否进入等待状态
            try {
            while (flag){
                    String media = medias.take();
                    if(StringUtils.isEmpty(media)){
                        flag = false;
//                        medias.put("end");//将结束标志放进队列  以防别的消费者线程看不到

                    }else{
                        log.info(media);
                        ++i;
                    }

            }//while
            } catch (InterruptedException e) {
                    e.printStackTrace();
                }
           long end = System.currentTimeMillis();
           log.info("消费者线程{}结束,消费数据{},时间：{}",Thread.currentThread().getName(),i,(end-start)/1000);
        }
    }



    public static void readFile(String url, BlockingQueue<String> medias,AtomicInteger count) throws IOException {
        InputStream intstream = null;
        Scanner sc = null;
//        int i = 0;
        try {
            intstream = new URL(url).openStream();
            sc = new Scanner(intstream, "UTF-8");
            long startTime = System.currentTimeMillis();
            log.info("开始读取，并写入队列,开始时间：{}",startTime);
            while (sc.hasNextLine() ) {
                count.incrementAndGet();
                log.info("当前条数：{}",count);
//                medias.put(sc.nextLine());
//                ++i;
            }
//            medias.put("end");
            log.info("写入队列完毕,一共数据{}条",count);
            long endTime = System.currentTimeMillis();
            log.info("读取时间{}秒",(endTime-startTime)/1000);
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (intstream != null) {
                intstream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

    }

    public InputStream getInputStreamByUrl(String strUrl) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(20 * 1000);
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            IOUtils.copy(conn.getInputStream(), output);
            return new ByteArrayInputStream(output.toByteArray());
        } catch (Exception e) {
            log.error("getInputStreamByUrl 异常,exception is {}", e);
        } finally {
            try {
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception e) {
            }
        }
        return null;
    }


    public static List<String> getFileUrlList(){
        String url = "https://gp726cgms8.execute-api.us-east-1.amazonaws.com/avc/catalog";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization ", "Basic MVV0VWE0MXc3VVZkd3R2eDoxS1NjT3FJOXZPWklsUWp4elVZcHdUNVVVZVhYNUxyVw==");
        HashMap<String, Object> body = new HashMap<>();
        //设置访问的Entity
        HttpEntity entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if (exchange.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        List<String> list = (List<String>) JSON.parseObject(exchange.getBody()).get("metadata");
        return list;

    }

    /** 从网络Url中下载文件

     * @param urlStr

     * @param fileName

     * @param savePath

     * @throws IOException

     */

    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{

        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        //设置超时间为3秒

        conn.setConnectTimeout(3*1000);

        //防止屏蔽程序抓取而返回403错误

        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");



        //得到输入流
        log.info("开始下载文件{}",fileName);
        InputStream inputStream = conn.getInputStream();

        //获取自己数组

        byte[] getData = readInputStream(inputStream);



        //文件保存位置

        File saveDir = new File(savePath);

        if(!saveDir.exists()){

            saveDir.mkdir();

        }

        File file = new File(saveDir+File.separator+fileName);

        FileOutputStream fos = new FileOutputStream(file);

        fos.write(getData);

        if(fos!=null){

            fos.close();

        }

        if(inputStream!=null){

            inputStream.close();

        }





        System.out.println("info:"+url+" download success");



    }
    /**

     * 从输入流中获取字节数组

     * @param inputStream

     * @return

     * @throws IOException

     */

    public static byte[] readInputStream(InputStream inputStream) throws IOException {

        byte[] buffer = new byte[1024];

        int len = 0;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        while((len = inputStream.read(buffer)) != -1) {

            bos.write(buffer, 0, len);

        }
        log.info("一个文件读取完毕");
        bos.close();

        return bos.toByteArray();

    }

}
