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

public class FileDataProviderStrategy implements DataProviderStrategy {
    private String filePath;

    public FileDataProviderStrategy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List provideData(int length, String dataType) {
        List data = new ArrayList<>();
        if (Files.exists(Path.of(filePath))) {
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
                                if (Validator.bookValid(book)) data.add(book);
                                else System.out.println("Проверьте правильность данных в файле");
                            } else System.out.println("Некорректные данные в строке файла");
                            break;
                        case "cars":
                            if (parameters.length == 3 && Validator.checkNumber(parameters[1]) &&
                                    Validator.checkDouble(parameters[0])) {
                                Car car = new Car.CarBuilder()
                                        .setPower(Double.parseDouble(parameters[0]))
                                        .setYearOfProduction(Integer.parseInt(parameters[1]))
                                        .setModel(parameters[2])
                                        .build();
                                if (Validator.carValid(car)) data.add(car);
                                else System.out.println("Проверьте правильность данных в файле");
                            } else System.out.println("Некорректные данные в строке файла");
                            break;
                        case "vegetables":
                            if (parameters.length == 3 && Validator.checkDouble(parameters[1])) {
                                RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                                        .setType(parameters[0])
                                        .setWeight(Double.parseDouble(parameters[1]))
                                        .setColor(parameters[2])
                                        .build();
                                if (Validator.vegetableValid(rootVegetable)) data.add(rootVegetable);
                                else System.out.println("Проверьте правильность данных в файле");
                            } else System.out.println("Некорректные данные в строке файла");
                            break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Неверный путь");
            }
        } else {
            System.out.println("Файла не существует");
        }
        return data;
    }
}
