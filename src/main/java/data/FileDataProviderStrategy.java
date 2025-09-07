package data;

import entity.Book;
import entity.Car;
import entity.RootVegetable;
import interfaces.DataProviderStrategy;
import validator.Validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация стратегии получения данных из файла.
 * Класс отвечает за чтение и парсинг данных из текстового файла,
 * создание соответствующих объектов и их валидацию.
 */
public class FileDataProviderStrategy implements DataProviderStrategy<Object> {

    /**
     * Путь к файлу с данными
     */
    private String filePath;

    /**
     * Конструктор класса.
     *
     * @param filePath путь к файлу с данными
     */
    public FileDataProviderStrategy(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Метод для получения данных из файла.
     * Читает данные из файла, парсит их и создает соответствующие объекты.
     *
     * @param length   максимальное количество записей для чтения
     * @param dataType тип данных для чтения (books, cars, vegetables)
     * @return список объектов, созданных из данных файла
     */
    @Override
    public List provideData(int length, String dataType) {
        List<Object> data = new ArrayList<>();

        if (!Files.exists(Path.of(filePath))) {
            System.out.println("Файла не существует: " + filePath);

            return data;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null && data.size() < length) {
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
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());

        }

        return data;
    }
}


