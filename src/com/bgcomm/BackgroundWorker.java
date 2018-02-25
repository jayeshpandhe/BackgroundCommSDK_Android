package com.bgcomm;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BackgroundWorker {
    // Sets the amount of time an idle thread will wait for a task before terminating
    private static final int KEEP_ALIVE_TIME = 1;

    // Sets the initial threadpool size to 8
    private static final int CORE_POOL_SIZE = 8;

    // Sets the maximum threadpool size to 8
    private static final int MAXIMUM_POOL_SIZE = 8;

    // Sets the Time Unit to seconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT;

    // private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    // A queue of Runnables for the thread pool
    private final BlockingQueue<Runnable> mWorkQueue;

    // A managed pool of background threads
    private final ThreadPoolExecutor mThreadPool;

    // A single instance of PhotoManager, used to implement the singleton pattern
    private static BackgroundWorker mInstance = null;

    private static final Object LOCK = new Object();

    static {
        // The time unit for "keep alive" is in seconds
        KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        mInstance = new BackgroundWorker();
    }

    private BackgroundWorker() {
        mWorkQueue = new LinkedBlockingQueue<>();
        mThreadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, mWorkQueue);
    }

    public static Future<?> submitRunnable(Runnable runnable) {
        Future future = null;
        if(runnable != null)
            future = mInstance.mThreadPool.submit(runnable);
        return future;
    }

    public void cancelAll() {
        synchronized (LOCK) {
            for(Runnable r : mWorkQueue) {
                if(r != null) {
                    Future futureTask = mInstance.mThreadPool.submit(r);
                    futureTask.cancel(true);
                }
            }
        }
    }
}
