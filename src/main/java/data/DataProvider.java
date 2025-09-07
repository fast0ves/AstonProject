package data;

import interfaces.DataProviderStrategy;

import java.util.List;

/**
 * Класс-обертка для работы со стратегиями предоставления данных.
 * Реализует паттерн Делегирование, инкапсулируя работу со стратегиями.
 * Позволяет легко менять стратегии предоставления данных во время выполнения.
 *
 * @param <T> тип предоставляемых данных
 */
public class DataProvider<T> {

    /** Стратегия предоставления данных */
    private DataProviderStrategy<T> dataProviderStrategy;

    /**
     * Создает новый экземпляр DataProvider с указанной стратегией.
     *
     * @param dataProviderStrategy стратегия предоставления данных (не может быть null)
     * @throws IllegalArgumentException если strategy null
     */
    public DataProvider(DataProviderStrategy<T> dataProviderStrategy) {
        this.dataProviderStrategy = dataProviderStrategy;
    }

    /**
     * Предоставляет данные с использованием установленной стратегии.
     * Делегирует вызов методу provideData стратегии.
     *
     * @param length количество элементов для получения (должно быть положительным)
     * @param dataType тип данных для предоставления (не может быть null или пустым)
     * @return список данных указанного типа и длины
     * @throws IllegalArgumentException если параметры невалидны
     */
    public List<T> provideData(int length, String dataType) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        if (dataType == null || dataType.trim().isEmpty()) {
            throw new IllegalArgumentException("Data type cannot be null or empty");
        }
        return dataProviderStrategy.provideData(length, dataType);
    }

}
