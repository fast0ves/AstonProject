package thread;

import algorithm.MergeSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Сервис для многопоточной сортировки данных.
 * Реализует параллельную сортировку списка с использованием двух потоков.
 * Использует алгоритм сортировки слиянием и пул потоков для параллельного выполнения.
 *
 * @param <T> тип элементов, которые будут сортироваться
 */
public class SortingService<T> {

    /** Менеджер пула потоков для выполнения задач сортировки */
    private final ThreadPoolManager threadPoolManager;

    /** Реализация алгоритма сортировки слиянием */
    private final MergeSort<T> mergeSort;

    /**
     * Конструктор класса.
     * Инициализирует менеджер пула потоков и алгоритм сортировки.
     */
    public SortingService() {
        this.threadPoolManager = new ThreadPoolManager();
        this.mergeSort = new MergeSort<>();
    }

    /**
     * Выполняет многопоточную сортировку списка.
     * Разделяет список на две части, сортирует каждую часть в отдельном потоке,
     * затем объединяет результаты.
     *
     * @param list список для сортировки
     * @param comparator компаратор для сравнения элементов
     * @param sortName имя сортировки для логирования
     */
    public void sortInTwoThreads(List<T> list, Comparator<T> comparator, String sortName) {
        System.out.println("Начало многопоточной сортировки " + sortName + " в 2 потока");

        if (list == null || comparator == null) {
            throw new IllegalArgumentException("List и Comparator не могут быть null");
        }

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

    /**
     * Завершает работу сервиса и освобождает ресурсы.
     */
    public void shutdown() {
        threadPoolManager.shutdown();
    }
}