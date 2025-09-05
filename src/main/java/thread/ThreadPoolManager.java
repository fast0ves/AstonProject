package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {
    private final ExecutorService executor;

    public ThreadPoolManager() {
        this.executor = Executors.newFixedThreadPool(2);
    }

    public void execute(Runnable task) {
        executor.execute(task);
    }

    public void awaitTermination() throws InterruptedException {
        executor.shutdown();
        if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
            executor.shutdownNow();
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}