package algorithm;

import java.util.Comparator;
import java.util.List;

/**
 * Универсальная реализация алгоритма бинарного поиска для отсортированных коллекций.
 * Поддерживает поиск элементов любого типа с использованием компаратора.
 */
public class BinarySearch<T> {

    /**
     * Выполняет итеративный бинарный поиск в отсортированном списке.
     *
     * @param sortedList отсортированный список для поиска (не может быть null)
     * @param key искомый элемент (не может быть null)
     * @param comparator компаратор для сравнения элементов (не может быть null)
     * @return индекс элемента в списке или -1 если элемент не найден
     * @throws IllegalArgumentException если любой из параметров null
     */
    public int search(List<T> sortedList, T key, Comparator<T> comparator) {
        if (sortedList == null || key == null || comparator == null) {
            throw new IllegalArgumentException("Параметры не могут быть null");
        }

        if (sortedList.isEmpty()) {

            return -1;
        }

        int left = 0;
        int right = sortedList.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            T midElement = sortedList.get(mid);

            int cmp = comparator.compare(midElement, key);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
