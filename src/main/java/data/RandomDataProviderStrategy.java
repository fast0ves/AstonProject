package data;

import entity.Book;
import entity.Car;
import entity.RootVegetable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDataProviderStrategy implements DataProviderStrategy<Object> {
    private String filePath;

    public RandomDataProviderStrategy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Object> provideData(int length, String dataType) {
        List<Object> data = new ArrayList<>();
        Random random = new Random();
        if (Files.exists(Path.of(filePath))) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                String line;
                List<String> tempList = new ArrayList<>();
                while ((line = bufferedReader.readLine()) != null) {
                    tempList.add(line);
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
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Файла не существует");
        }
        return data;
    }
}
