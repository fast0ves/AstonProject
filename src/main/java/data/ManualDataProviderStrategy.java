package data;

import entity.Book;
import entity.Car;
import entity.RootVegetable;
import interfaces.DataProviderStrategy;
import validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ManualDataProviderStrategy implements DataProviderStrategy<Object> {
    private Scanner scanner;

    public ManualDataProviderStrategy(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public List<Object> provideData(int length, String dataType) {
        List<Object> data = new ArrayList<>();
        System.out.println("Введите ваши данные: ");
        System.out.println("Для завершения ввода введите 'done'");

        while (data.size() < length) {
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("done")) {
                break;
            }

            if (line.isEmpty()) {
                continue;
            }

            String[] parameters = line.split(", ");

            switch (dataType) {
                case "books":
                    if (parameters.length == 3 && Validator.checkNumber(parameters[2])) {
                        Book book = new Book.BookBuilder()
                                .setAuthor(parameters[0])
                                .setTitle(parameters[1])
                                .setQuantityPage(Integer.parseInt(parameters[2]))
                                .build();
                        if (Validator.bookValid(book)) {
                            data.add(book);
                            System.out.println("Книга добавлена:" + book);
                        } else {
                            System.out.println("Проверьте правильность введенных данных");
                        }
                    } else {
                        System.out.println("Количество страниц должно быть числом");
                    }
                    break;

                case "cars":
                    if (parameters.length == 3
                            && Validator.checkNumber(parameters[1])
                            && Validator.checkDouble(parameters[0])) {
                        Car car = new Car.CarBuilder()
                                .setPower(Double.parseDouble(parameters[0]))
                                .setYearOfProduction(Integer.parseInt(parameters[1]))
                                .setModel(parameters[2])
                                .build();
                        if (Validator.carValid(car)) {
                            data.add(car);
                            System.out.println("Машина добавлена: " + car);
                        } else {
                            System.out.println("Проверьте правильность введенных данных");
                        }
                    } else {
                        System.out.println("Мощность и год должны быть числами");
                    }
                    break;

                case "vegetables":
                    if (parameters.length == 3 && Validator.checkDouble(parameters[1])) {
                        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                                .setType(parameters[0])
                                .setWeight(Double.parseDouble(parameters[1]))
                                .setColor(parameters[2])
                                .build();
                        if (Validator.vegetableValid(rootVegetable)) {
                            data.add(rootVegetable);
                            System.out.println("Корнеплод добавлен: " + rootVegetable);
                        } else {
                                System.out.println("Проверьте правильность введенных данных");
                        }
                        } else {
                            System.out.println("Вес должен быть числом");
                        }
                        break;
                        default:
                            System.out.println("Неизвестный тип данных: " + dataType);
            }
        }
        return data;
    }
}
