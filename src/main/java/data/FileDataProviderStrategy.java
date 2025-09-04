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
                            Book book = new Book.BookBuilder()
                                    .setAuthor(parameters[0])
                                    .setTitle(parameters[1])
                                    .setQuantityPage(Integer.parseInt(parameters[2]))
                                    .build();
                            if (Validator.bookValid(book)) data.add(book);
                            else System.out.println("Проверьте правильность данных в файле");
                            break;
                        case "cars":
                            Car car = new Car.CarBuilder()
                                    .setPower(Double.parseDouble(parameters[0]))
                                    .setYearOfProduction(Integer.parseInt(parameters[1]))
                                    .setModel(parameters[2])
                                    .build();
                            if (Validator.carValid(car)) data.add(car);
                            else System.out.println("Проверьте правильность данных в файле");
                            break;
                        case "vegetables":
                            RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                                    .setType(parameters[0])
                                    .setWeight(Double.parseDouble(parameters[1]))
                                    .setColor(parameters[2])
                                    .build();
                            if (Validator.vegetableValid(rootVegetable)) data.add(rootVegetable);
                            else System.out.println("Проверьте правильность данных в файле");
                            break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Файла не существует");
        }
        return data;
    }
}
