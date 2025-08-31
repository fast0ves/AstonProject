/**
 created by Shorokhov Andrey
 */

package validator;

/**
 * Интерфейс стратегии валидации. Реализует паттерн Стратегия.
 * Определяет контракт для различных стратегий валидации данных.
 * @param <T> типа валидируемых данных
 */
public interface ValidatorStrategy<T> {

    /**
     * Выполняет валидацию объекта
     * @param object объект для валидации
     * @return true если объект валиден, false в противном случае
     */
    boolean validate(T object);

    /**
     * Возвращает сообщение об ошибке валидации
     * @return сообщение об ошибке или null если валидация успешна
     */
    String getErrorMessage();

    /**
     * Возвращает тип стратегии валидации
     * @return строковое представление типа валидатора
     */
    String getValidatorType();
}
