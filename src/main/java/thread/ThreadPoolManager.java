package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Менеджер пула потоков для сортировки.
 * Создает минимум 2 потока, выполнение в ThreadPool
 */
public class ThreadPoolManager {
    private final ExecutorService executor;

    public ThreadPoolManager(ExecutorService executor) {
        this.executor = Executors.newFixedThreadPool(2);
    }

    /**
     * Выполняет задачу в пуле потоков
     * @param task задача для выполнения
     */
    public void execute(Runnable task) {
        executor.execute(task);
    }

    /**
     * Завершает работу пула потоков
     */
    public void shutdown() {
        executor.shutdown();
    }
}
