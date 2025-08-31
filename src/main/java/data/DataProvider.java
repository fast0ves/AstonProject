/**
 created by Shorokhov Andrey
 */

package data;

import java.util.List;

/**
 * Интерфейс для поставщиков данных различных типов
 * @param <T> тип данных, которые предоставляет провайдер
 */
public interface DataProvider<T> {

    /**
     * Основной метод получения данных
     * @return список объектов типа T
     */
    List<T> provideData();

    /**
     * Возвращает тип провайдера
     * @return строковое представление типа провайдера
     */
    String getProviderType();
}
