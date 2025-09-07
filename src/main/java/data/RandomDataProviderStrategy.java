package data;

import entity.Book;
import entity.Car;
import entity.RootVegetable;
import interfaces.DataProviderStrategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Реализация стратегии получения случайных данных из файла.
 * Класс считывает данные из файла и случайным образом выбирает
 * необходимое количество записей указанного типа.
 */
public class RandomDataProviderStrategy implements DataProviderStrategy<Object> {

    /**
     * Путь к файлу с данными
     */
    private String filePath;

    /**
     * Конструктор класса.
     *
     * @param filePath путь к файлу с данными
     */
    public RandomDataProviderStrategy(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Метод для получения случайных данных из файла.
     * Считывает все данные из файла, затем случайным образом
     * выбирает необходимое количество записей.
     *
     * @param length   количество записей для получения
     * @param dataType тип данных для получения (books, cars, vegetables)
     * @return список случайно выбранных объектов
     */
    @Override
    public List<Object> provideData(int length, String dataType) {
        List<Object> data = new ArrayList<>();
        Random random = new Random();

        if (!Files.exists(Path.of(filePath))) {
            System.out.println("Файла не существует: " + filePath);

            return data;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<String> tempList = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {
                tempList.add(line);
            }

            if (length > tempList.size()) {
                length = tempList.size();
            }

            while (data.size() < length) {
                int randNumberLine = random.nextInt(29);
                String[] parameters = tempList.get(randNumberLine).split(", ");

                switch (dataType) {
                    case "books":
                        Book book = new Book.BookBuilder()
                                .setAuthor(parameters[0])
                                .setTitle(parameters[1])
                                .setQuantityPage(Integer.parseInt(parameters[2]))
                                .build();
                        data.add(book);
                        break;

                    case "cars":
                        Car car = new Car.CarBuilder()
                                .setPower(Double.parseDouble(parameters[0]))
                                .setYearOfProduction(Integer.parseInt(parameters[1]))
                                .setModel(parameters[2])
                                .build();
                        data.add(car);
                        break;

                    case "vegetables":
                        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                                .setType(parameters[0])
                                .setWeight(Double.parseDouble(parameters[1]))
                                .setColor(parameters[2])
                                .build();
                        data.add(rootVegetable);
                        break;
                    default:
                        System.out.println("Неизвестный тип данных: " + dataType);
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла " + e.getMessage());
        }

        return data;
    }
}
