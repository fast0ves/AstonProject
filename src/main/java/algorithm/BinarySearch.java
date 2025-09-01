/**
 created by Shorokhov Andrey
 */
package algorithm;

import java.util.Comparator;
import java.util.List;

/**
 * Универсальная реализация бинарного поиска для отсортированных коллекций.
 * Работает с любыми типами данных через компаратор
 */
public class BinarySearch<T> {

    /**
     * Итеративная реализация бинарного поиска
     *
     * @param sortedList отсортированный список
     * @param key искомый элемент
     * @param comparator компаратор для сравнения элементов
     * @return индекс элемента или -1 если не найден
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
