package data;

import entity.Book;
import entity.Car;
import entity.RootVegetable;
import interfaces.DataProviderStrategy;
import validator.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ManualDataProviderStrategy implements DataProviderStrategy<Object> {

    @Override
    public List<Object> provideData(int length, String dataType) {
        List<Object> data = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while (!(line = bufferedReader.readLine()).equals("done") && data.size() < length) {
                String[] parameters = line.split(", ");
                switch (dataType) {
                    case "books":
                        Book book = new Book.BookBuilder()
                                .setAuthor(parameters[0])
                                .setTitle(parameters[1])
                                .setQuantityPage(Integer.parseInt(parameters[2]))
                                .build();
                        if (Validator.bookValid(book)) data.add(book);
                        else System.out.println("Проверьте правильность введенных данных");
                        break;
                    case "cars":
                        Car car = new Car.CarBuilder()
                                .setPower(Double.parseDouble(parameters[0]))
                                .setYearOfProduction(Integer.parseInt(parameters[1]))
                                .setModel(parameters[2])
                                .build();
                        if (Validator.carValid(car)) data.add(car);
                        else System.out.println("Проверьте правильность введенных данных");
                        break;
                    case "vegetables":
                        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                                .setType(parameters[0])
                                .setWeight(Double.parseDouble(parameters[1]))
                                .setColor(parameters[2])
                                .build();
                        if (Validator.vegetableValid(rootVegetable)) data.add(rootVegetable);
                        else System.out.println("Проверьте правильность введенных данных");
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
