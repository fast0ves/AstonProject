import data.DataProvider;
import data.FileDataProviderStrategy;
import entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileDataProviderTest {

    @Test
    public void notExistFile() {
        List expectedData = new ArrayList();
        DataProvider dataProvider = new DataProvider(new FileDataProviderStrategy("src/main/resources/book"));
        List data = dataProvider.provideData(3, "books");

        Assertions.assertEquals(expectedData, data);
    }

    @Test
    public void correctFilePath() {
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

        DataProvider dataProvider = new DataProvider(new FileDataProviderStrategy("src/main/resources/books"));
        List<Book> data = dataProvider.provideData(3, "books");

        Assertions.assertEquals(expectedData, data);
    }

    @Test
    public void incorrectDataInFile() {
        List expectedData = new ArrayList();

        DataProvider dataProvider = new DataProvider(new FileDataProviderStrategy("src/main/resources/books"));
        List data = dataProvider.provideData(3, "cars");

        Assertions.assertEquals(expectedData, data);
    }

    @Test
    public void zeroLengthValue() {
        List expectedData = new ArrayList<>();

        DataProvider dataProvider = new DataProvider(new FileDataProviderStrategy(
                "src/main/resources/vegetables"));
        List data = dataProvider.provideData(0, "vegetables");

        Assertions.assertEquals(expectedData, data);
    }

    @Test
    public void emptyFilePath() {
        List expectedData = new ArrayList();

        DataProvider dataProvider = new DataProvider(new FileDataProviderStrategy(""));
        List data = dataProvider.provideData(1, "cars");

        Assertions.assertEquals(expectedData, data);
    }

    @Test
    public void negativeLengthValue() {
        List expectedData = new ArrayList();

        DataProvider dataProvider = new DataProvider(new FileDataProviderStrategy("src/main/resources/cars"));
        List data = dataProvider.provideData(-1, "cars");

        Assertions.assertEquals(expectedData, data);
    }

    @Test
    public void returnedTypeData() {
        Random random = new Random();
        DataProvider dataProviderCar = new DataProvider(new FileDataProviderStrategy("src/main/resources/cars"));
        List dataCar = dataProviderCar.provideData(3, "cars");
        DataProvider dataProviderBook = new DataProvider(new FileDataProviderStrategy("src/main/resources/books"));
        List dataBook = dataProviderBook.provideData(6, "books");
        DataProvider dataProviderRootVegetable = new DataProvider(
                new FileDataProviderStrategy("src/main/resources/vegetables"));
        List dataRootVegetable = dataProviderRootVegetable.provideData(9, "vegetables");

        Assertions.assertEquals("entity.Car", dataCar.get(random.nextInt(2)).getClass().getName());
        Assertions.assertEquals("entity.Book", dataBook.get(random.nextInt(5)).getClass().getName());
        Assertions.assertEquals("entity.RootVegetable", dataRootVegetable
                .get(random.nextInt(8))
                .getClass()
                .getName());
    }

}
