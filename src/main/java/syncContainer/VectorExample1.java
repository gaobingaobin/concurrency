package syncContainer;

import com.example.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class VectorExample1 {



    public static void main(String[] args) throws InterruptedException {
     Vector<Integer> v = new Vector<>();
     v.add(1);
     v.add(2);
     v.add(3);
     test3(v);



    }
   /** 方法只执行一次*/
   //java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v){
        for (Integer i: v) {
          if(i.equals(3)){
              v.remove(i);
          }
        }
    }
    //java.util.ConcurrentModificationException

    private static void test2(Vector<Integer> v){
       Iterator<Integer> iterable = v.iterator();
       while (iterable.hasNext()){
           Integer i = iterable.next();
           if(i.equals(3)){
               v.remove(i);
           }

       }

    }
    // 成功操作
    private static void test3(Vector<Integer> v){
        Iterator<Integer> iterable = v.iterator();
       for (int i= 0;i<v.size();i++){
           if(v.get(i).equals(3)){
               v.remove(i);
           }
       }

    }
}
