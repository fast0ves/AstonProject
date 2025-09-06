/**
 created by Shorokhov Andrey
 */
package algorithm;

import interfaces.SortStrategy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Реализация алгоритма сортировки слиянием (MergeSort)
 * Этот класс реализует паттерн Стратегия через интерфейс SortStrategy
 *
 * @param <T> тип элементов в сортируемом списке
 */

public class MergeSort<T> implements SortStrategy<T> {

    /**
     * Сортирует переданный список с использованием алгоритма сортировки слиянием
     *
     * @param list список для сортировки
     * @param comparator компаратор, определяющий порядок сортировки
     */
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || comparator == null) {
            throw new IllegalArgumentException("List или Comparator не могут быть null");
        }

        if (list.size() < 2) {

            return;
        }

        int split = list.size() / 2;
        List<T> left = new ArrayList<>(list.subList(0, split));
        List<T> right = new ArrayList<>(list.subList(split, list.size()));

        sort(left, comparator);
        sort(right, comparator);
        combine(list, left, right, comparator);
    }

    /**
     * Объединяет два отсортированных списка в один отсортированный список
     * @param list результирующий список для объединения
     * @param left левый отсортированный список
     * @param right правый отсортированный список
     * @param comparator компаратор для сравнения элементов
     */
    public void combine(List<T> list, List<T> left, List<T> right, Comparator<T> comparator) {

        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0) {
                list.set(resultIndex++, left.get(leftIndex++));
            } else {
                list.set(resultIndex++, right.get(rightIndex++));
            }
        }

        while (leftIndex < left.size()) {
            list.set(resultIndex++, left.get(leftIndex++));
        }

        while (rightIndex < right.size()) {
            list.set(resultIndex++, right.get(rightIndex++));
        }
    }
}
