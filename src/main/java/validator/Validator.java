/**
 created by Shorokhov Andrey
 */
package validator;

import entity.Book;
import entity.Car;
import entity.RootVegetable;

/**
 * Простой валидатор для всех сущностей проекта.
 * Используется при чтении из файла, ручном вводе и генерации случайных данных
 */
public class Validator {

    public static boolean bookValid(Book book) {
        return book != null
                && book.getAuthor() != null && !book.getAuthor().isBlank()
                && book.getTitle() != null && !book.getTitle().isBlank()
                && book.getQuantityPage() > 0
                && book.getQuantityPage() < 10_000;
    }

    public static boolean carValid(Car car) {
        return car != null
                && car.getModel() != null && !car.getModel().isBlank()
                && car.getPower() > 0
                && car.getYearOfProduction() >= 1886
                && car.getYearOfProduction() <= java.time.Year.now().getValue();
    }

    public static boolean vegetableValid(RootVegetable rootVegetable) {
        return rootVegetable != null
                && rootVegetable.getColor() != null && !rootVegetable.getColor().isBlank()
                && rootVegetable.getType() != null && !rootVegetable.getType().isBlank()
                && rootVegetable.getWeight() > 0
                && rootVegetable.getWeight() <= 10_000;
    }
}
