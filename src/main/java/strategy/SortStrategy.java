/**
 created by Shorokhov Andrey
 */

package strategy;

import java.util.Comparator;
import java.util.List;

/**
 * Интерфейс стратегия сортировки. Реализует паттерн Стратегия.
 * Определяет контракт для различных алгоритмов сортировки.
 * @param <T> тип сортируемых объектов
 */
public interface SortStrategy<T> {

    /**
     * Выполняет сортировку списка по заданному алгоритму.
     * @param list список для сортировки
     * @param comparator компаратор, определяющий порядок сортировки
     */
    void sort(List<T> list, Comparator<T> comparator);
}
