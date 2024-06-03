/*
 * Program to demonstrate multi-threading using the producer consumer problem
 */

package javapractice;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Custom queue for ensuring the produced item is consumed before producing more items

class ProducerConsumerQueue {

    static final int MAX_SIZE = 10;
    
    // The variable currentItem is synchronized using the synchronized block and methods and provides both mutual exclusion and visibility
    // The variable currentItem can be set to volatile and the synchronized blocks can be removed if it is set to be volatile. It will only provide visibility
    // volatile int currentItem = 0;
    
    int currentItem = 0;
    
    boolean valueSet = false;
    
    Logger fileLogger;
    
    ProducerConsumerQueue(Logger fileLogger) {
    	this.fileLogger = fileLogger;
    }

    // Synchronized methods
    synchronized void get() {
        while (!valueSet) {
        	// Exit if all items are consumed
            if (currentItem >= MAX_SIZE) {
            	return; 
            }
            
            // Else if more items are going to be produced wait
            try {
                wait();
            } catch (InterruptedException ex) {
                fileLogger.info("InterruptedException caught");
            }
        }
        fileLogger.info("Consumed: Item" + this.currentItem + " by " + Thread.currentThread().getName());
        valueSet = false;
        notifyAll();
    }

    synchronized void put() {
        while (valueSet) {
        	// Exit if queue is full i.e all items are produced
            if (currentItem >= MAX_SIZE) {
            	return;
            }
                   
            // Else if more items are going to be consumed wait
            try {
                wait();
            } catch (InterruptedException ex) {
                fileLogger.info("InterruptedException caught");
            }
        }
        if (this.currentItem < MAX_SIZE) {
            this.currentItem++;
            fileLogger.info("Produced: Item" + currentItem + " by " + Thread.currentThread().getName());
            valueSet = true;
            notifyAll();
        }
    }
}

// Producer which implements the Runnable interface to support Multi-threading

class Producer implements Runnable {

    ProducerConsumerQueue queue;
    Thread demoThread;
    int producerNo;

    Producer(ProducerConsumerQueue queue, int producerNo) {
        this.queue = queue;
        this.demoThread = new Thread(this);
        this.producerNo = producerNo;
    }

    public void run() {
    	Thread.currentThread().setName("Producer" + producerNo);
        while (true) {
        	// Synchronized block to ensure that latest value of queue.currentItem is atomically checked only by a single thread at a time
            synchronized (queue) {
                if (queue.currentItem >= ProducerConsumerQueue.MAX_SIZE) {
                	break;
                }
            }
            queue.put();
        }
    }
}

// Consumer which implements the Runnable interface to support Multi-threading

class Consumer implements Runnable {

    ProducerConsumerQueue queue;
    Thread demoThread;
    int consumerNo;
    
    Consumer(ProducerConsumerQueue queue, int consumerNo) {
        this.queue = queue;
        this.demoThread = new Thread(this);
        this.consumerNo = consumerNo;
    }

    public void run() {
    	Thread.currentThread().setName("Consumer" + consumerNo);
        while (true) {
        	// Synchronized block to ensure that latest value of queue.currentItem is atomically checked only by a single thread at a time
            synchronized (queue) {
                if (queue.currentItem >= ProducerConsumerQueue.MAX_SIZE && !queue.valueSet) break;
            }
            queue.get();
        }
    }
}

public class MultiThreadingDemo {
	
	private static Logger fileLogger = LogManager.getLogger(MultiThreadingDemo.class.getName());

    public static void main(String[] args) {

    	// Common queue for all producer and consumer threads
        ProducerConsumerQueue demoQueue = new ProducerConsumerQueue(fileLogger);
        
        // Create a fixed-size thread pool with 4 threads
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

        // Submit producer and consumer tasks to the thread pool
        for (int currentIndex = 1; currentIndex < 3; currentIndex++) {
            executor.submit(new Producer(demoQueue, currentIndex));
            executor.submit(new Consumer(demoQueue, currentIndex));
        }

        // Shutdown the executor once all tasks are completed
        executor.shutdown();

        try {
            // Wait until all tasks are completed or the timeout occurs (whichever happens first)
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
        	fileLogger.info("Main thread was interrupted");
        }


        fileLogger.info("Producer-Consumer demo finished");
    }
}
