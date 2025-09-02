/**
 * created by Shorokhov Andrey
 */

package data;

import java.util.List;

/**
 * Интерфейс для поставщиков данных различных типов
 * @param <T> тип данных, которые предоставляет провайдер
 */
public interface DataProviderStrategy<T> {

    /**
     * Основной метод получения данных
     * @return список объектов типа T
     * @param length длина массива для данных
     * @param dataType тип данных с которыми пользователь решил работать
     */
    List<T> provideData(int length, String dataType);
}
