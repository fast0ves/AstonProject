package thread;

import algorithm.MergeSort;
import java.util.Comparator;
import java.util.List;

public class SortingService<T> {
    private final ThreadPoolManager threadPoolManager;
    private final MergeSort<T> mergeSort;

    public SortingService() {
        this.threadPoolManager = new ThreadPoolManager();
        this.mergeSort = new MergeSort<>();
    }

    public void sortThreadPool(List<T> list, Comparator<T> comparator, String sortName) {
        threadPoolManager.execute(() -> {
            System.out.println("Начало сортировки " + sortName + " в потоке: " +
                    Thread.currentThread().getName());

            mergeSort.sort(list, comparator);

            System.out.println("Завершена сортировка " + sortName + " в потоке: " +
                    Thread.currentThread().getName());
        });
    }

    public void shutdown() {
        threadPoolManager.shutdown();
    }
}