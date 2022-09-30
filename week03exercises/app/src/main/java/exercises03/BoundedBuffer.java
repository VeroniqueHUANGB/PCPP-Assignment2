package exercises03;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class BoundedBuffer implements BoundedBufferInteface {
    private final int bufferSize = 10;
    private final Queue<Object> queue = new LinkedList<>();
    private final Semaphore semConsumer  = new Semaphore(0);
    private final Semaphore semProducer = new Semaphore(bufferSize);


    @Override
    public Object take() throws Exception {
        try{
            semConsumer.acquire();
        }catch (InterruptedException e){
            System.out.println("Not being able to take from the queue");
        }
        Object o = queue.poll();
        semProducer.release();
        return o;
    }

    @Override
    public void insert(Object elem) throws Exception {
        try{
            semProducer.acquire();
        }catch (InterruptedException e){
            System.out.println("Not being able to put data to the queue");
        }
        queue.offer(elem);
        semConsumer.release();
    }

}
