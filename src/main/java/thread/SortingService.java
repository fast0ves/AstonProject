package thread;

import algorithm.MergeSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingService<T> {
    private final ThreadPoolManager threadPoolManager;
    private final MergeSort<T> mergeSort;

    public SortingService() {
        this.threadPoolManager = new ThreadPoolManager();
        this.mergeSort = new MergeSort<>();
    }

    public void sortInTwoThreads(List<T> list, Comparator<T> comparator, String sortName) {
        System.out.println("Начало многопоточной сортировки " + sortName + " в 2 потока");

        if (list == null || list.size() <= 1) {
            return;
        }

        int mid = list.size() / 2;
        List<T> leftHalf = new ArrayList<>(list.subList(0, mid));
        List<T> rightHalf = new ArrayList<>(list.subList(mid, list.size()));

        Runnable leftTask = () -> {
            System.out.println("Сортировка левой половины в потоке: " +
                    Thread.currentThread().getName());
            mergeSort.sort(leftHalf, comparator);
        };

        Runnable rightTask = () -> {
            System.out.println("Сортировка правой половины в потоке: " +
                    Thread.currentThread().getName());
            mergeSort.sort(rightHalf, comparator);
        };

        threadPoolManager.execute(leftTask);
        threadPoolManager.execute(rightTask);


        try {
            threadPoolManager.awaitTermination();


            System.out.println("Слияние отсортированных половин");
            mergeSort.combine(list, leftHalf, rightHalf, comparator);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Сортировка была прервана");
        }

        System.out.println("Завершена многопоточная сортировка " + sortName);
    }

    public void shutdown() {
        threadPoolManager.shutdown();
    }
}