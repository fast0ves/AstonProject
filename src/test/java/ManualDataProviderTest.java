import data.DataProvider;
import data.ManualDataProviderStrategy;
import entity.Book;
import entity.Car;
import entity.RootVegetable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualDataProviderTest {

    @Test
    public void inputDataBook() {
        List<Book> expectedData = new ArrayList<>();
        Book book = new Book.BookBuilder()
                .setQuantityPage(671)
                .setTitle("Преступление и наказание")
                .setAuthor("Фёдор Достоевский")
                .build();
        Book book1 = new Book.BookBuilder()
                .setQuantityPage(1300)
                .setAuthor("Лев Толстой")
                .setTitle("Война и мир")
                .build();
        Book book2 = new Book.BookBuilder()
                .setQuantityPage(384)
                .setTitle("Мастер и Маргарита")
                .setAuthor("Михаил Булгаков")
                .build();
        expectedData.add(book);
        expectedData.add(book1);
        expectedData.add(book2);

        String inputData = "Фёдор Достоевский, Преступление и наказание, 671\n" +
                "Лев Толстой, Война и мир, 1300\n" +
                "Михаил Булгаков, Мастер и Маргарита, 384\ndone";

        Scanner scanner = new Scanner(inputData);

        DataProvider dataProvider = new DataProvider(new ManualDataProviderStrategy(scanner));
        List<Book> data = dataProvider.provideData(3, "books");

        Assertions.assertEquals(expectedData, data);
    }

    @Test
    public void inputDataCar() {
        List<Car> expectedData = new ArrayList<>();
        Car car = new Car.CarBuilder()
                .setPower(150)
                .setYearOfProduction(2022)
                .setModel("Toyota Camry")
                .build();
        Car car1 = new Car.CarBuilder()
                .setPower(1000)
                .setModel("Tesla Model S Plaid")
                .setYearOfProduction(2023)
                .build();
        expectedData.add(car);
        expectedData.add(car1);

        String inputData = "150, 2022, Toyota Camry\n" +
                "1000, 2023, Tesla Model S Plaid\ndone";

        Scanner scanner = new Scanner(inputData);

        DataProvider dataProvider = new DataProvider(new ManualDataProviderStrategy(scanner));
        List<Car> data = dataProvider.provideData(2, "cars");

        Assertions.assertEquals(expectedData, data);
    }

    @Test
    public void inputDataRootVegetables() {
        List<RootVegetable> expectedData = new ArrayList<>();
        RootVegetable rootVegetable = new RootVegetable.RootVegetableBuilder()
                .setType("Морковь")
                .setColor("оранжевый")
                .setWeight(150)
                .build();
        RootVegetable rootVegetable1 = new RootVegetable.RootVegetableBuilder()
                .setType("Свекла")
                .setColor("бордовый")
                .setWeight(300)
                .build();
        RootVegetable rootVegetable2 = new RootVegetable.RootVegetableBuilder()
                .setType("Редис")
                .setColor("красный")
                .setWeight(20)
                .build();
        RootVegetable rootVegetable3 = new RootVegetable.RootVegetableBuilder()
                .setType("Репа")
                .setColor("белый")
                .setWeight(200)
                .build();
        expectedData.add(rootVegetable);
        expectedData.add(rootVegetable1);
        expectedData.add(rootVegetable2);
        expectedData.add(rootVegetable3);

        String inputData = "Морковь, 150, оранжевый\n" +
                "Свекла, 300, бордовый\n" +
                "Редис, 20, красный\n" +
                "Репа, 200, белый\ndone";

        Scanner scanner = new Scanner(inputData);

        DataProvider dataProvider = new DataProvider(new ManualDataProviderStrategy(scanner));
        List<RootVegetable> data = dataProvider.provideData(4, "vegetables");

        Assertions.assertEquals(expectedData, data);
    }


    @Test
    public void doneInput() {
        String inputData = "done";
        Scanner scanner = new Scanner(inputData);
        DataProvider dataProvider = new DataProvider(new ManualDataProviderStrategy(scanner));
        List data = dataProvider.provideData(3, "cars");

        Assertions.assertTrue(data.isEmpty());
    }
}
