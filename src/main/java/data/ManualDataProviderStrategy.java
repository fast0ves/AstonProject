package data;

import entity.Book;
import entity.Car;
import entity.RootVegetable;
import interfaces.DataProviderStrategy;
import validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualDataProviderStrategy implements DataProviderStrategy {
    private Scanner scanner;

    public ManualDataProviderStrategy(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public List provideData(int length, String dataType) {
        List data = new ArrayList<>();
        String line;
        while (!(line = scanner.nextLine()).equals("done") && data.size() < length && scanner.hasNextLine()) {
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
                        else System.out.println("Проверьте правильность введенных данных");
                    } else System.out.println("Некорректные данные");
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
                        else System.out.println("Проверьте правильность введенных данных");
                    } else System.out.println("Некорректные данные");
                    break;
                case "vegetables":
                    if (parameters.length == 3 && Validator.checkDouble(parameters[1])) {
                        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                                .setType(parameters[0])
                                .setWeight(Double.parseDouble(parameters[1]))
                                .setColor(parameters[2])
                                .build();
                        if (Validator.vegetableValid(rootVegetable)) data.add(rootVegetable);
                        else System.out.println("Проверьте правильность введенных данных");
                    } else System.out.println("Некорректные данные");
                    break;
            }
        }
        return data;
    }
}
