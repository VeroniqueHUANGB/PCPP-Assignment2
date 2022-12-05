package testingconcurrency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CyclicBarrier;

import javax.swing.plaf.synth.SynthEditorPaneUI;

import java.util.concurrent.BrokenBarrierException;

public class ConcurrentSetTest {

    // Variable with set under test
    private ConcurrentIntegerSet setBuggy;

    // TODO: Very likely you should add more variables here
    private ConcurrentIntegerSetSync setSync;
    private ConcurrentIntegerSetLibrary setLib;
    private CyclicBarrier barrier;

    // Uncomment the appropriate line below to choose the class to
    // test
    // Remember that @BeforeEach is executed before each test
    @BeforeEach
    public void initialize() {
	// init set
	setBuggy = new ConcurrentIntegerSetBuggy();
	setSync = new ConcurrentIntegerSetSync();	
	setLib = new ConcurrentIntegerSetLibrary();
    }

    // TODO: Define your tests below
    /**
     * Question 1
     */
    @RepeatedTest(2000)
    @DisplayName("Test add")
    public void testAdd() throws Exception {
        int nThreads = 100;
        barrier = new CyclicBarrier(nThreads + 1);

        for(int i = 0; i < nThreads; i++) {
            new Thread(() -> {
                try {
                    barrier.await();
                    setBuggy.add(1);
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        try {
            barrier.await();
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        assertEquals(1, setBuggy.size(), "set.size() == "+setBuggy.size()+", but we expected "+1);
    }

    /**
     * Question 2
     */
    @RepeatedTest(5000)
    @DisplayName("Test remove")
    public void testRemove() throws Exception {
        int threads = 100;
        setBuggy.add(1);
        var barrier = new CyclicBarrier(threads + 1);

        for (int i = 0; i < threads; i++) {
            new Thread(() -> {
                try {
                    barrier.await();
                    setBuggy.remove(1);
                    barrier.await();
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }).start();
        }

        try {
            barrier.await();
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        assertEquals(0, setBuggy.size(), "set.size() == "+setBuggy.size()+", but we expected "+0);
    }

    /**
     * Question 3
     */
    @RepeatedTest(1000)
    public void testingSyncAdd() {
        setSyncAdd t1 = new setSyncAdd();
        setSyncAdd t2 = new setSyncAdd();

        t1.start(); t2.start();

        try{
            t1.join();
            t2.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        assertEquals(1,setSync.size());
    }

    //errors in finds concurrency errors in the 
    //remove(Integer element) method in ConcurrentIntegerSetBuggy
    @RepeatedTest(1000)
    public void testingSyncRemove(){
        setSync.add(1);

        setSyncRemove t1 = new setSyncRemove();
        setSyncRemove t2 = new setSyncRemove();

        t1.start(); t2.start();

        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        assertEquals(0, setSync.size());
    }

    /**
     * Question 4
     */
    @RepeatedTest(1000)
    public void testingLibAdd() {
        setLibAdd t1 = new setLibAdd();
        setLibAdd t2 = new setLibAdd();

        t1.start(); t2.start();

        try{
            t1.join();
            t2.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        assertEquals(1,setLib.size());
    }

    //errors in finds concurrency errors in the 
    //remove(Integer element) method in ConcurrentIntegerSetBuggy
    @RepeatedTest(1000)
    public void testingLibRemove(){
        setLib.add(1);

        setLibRemove t1 = new setLibRemove();
        setLibRemove t2 = new setLibRemove();

        t1.start(); t2.start();

        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        assertEquals(0, setLib.size());
    }


    /*** Test Threads ***/
    public class setBuggyAdd extends Thread {
        public void run() {
            setBuggy.add(1);
        }
    }

    public class setBuggyRemove extends Thread {
        public void run(){
            setBuggy.remove(1);
        }
    }

    public class setSyncAdd extends Thread {
        public void run() {
            setSync.add(1);
        }
    }

    public class setSyncRemove extends Thread {
        public void run(){
            setSync.remove(1);
        }
    }

    public class setLibAdd extends Thread {
        public void run() {
            setLib.add(1);
        }
    }

    public class setLibRemove extends Thread {
        public void run(){
            setLib.remove(1);
        }
    }
}
