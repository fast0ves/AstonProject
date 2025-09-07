package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Менеджер пула потоков для управления выполнением задач.
 * Предоставляет абстракцию над ExecutorService с фиксированным количеством потоков.
 * Обеспечивает корректное управление жизненным циклом пула потоков.
 */
public class ThreadPoolManager {

    /** Сервис исполнителя задач с фиксированным пулом потоков */
    private final ExecutorService executor;

    /**
     * Конструктор класса.
     * Создает пул с двумя потоками для выполнения задач.
     */
    public ThreadPoolManager() {
        this.executor = Executors.newFixedThreadPool(2);
    }

    /**
     * Выполняет заданную задачу в пуле потоков.
     *
     * @param task задача для выполнения
     */
    public void execute(Runnable task) {
        executor.execute(task);
    }

    /**
     * Ожидает завершения всех задач в пуле потоков.
     * Пытается корректно завершить работу пула в течение одной минуты.
     * Если задачи не завершаются за отведенное время, принудительно останавливает их.
     *
     * @throws InterruptedException если ожидание прерывается
     */
    public void awaitTermination() throws InterruptedException {
        executor.shutdown();
        if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
            executor.shutdownNow();
        }
    }

    /**
     * Инициирует корректное завершение работы пула потоков.
     * После вызова метода новые задачи не могут быть добавлены в пул.
     */
    public void shutdown() {
        executor.shutdown();
    }
}