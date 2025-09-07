import data.DataProvider;
import data.RandomDataProviderStrategy;
import entity.Book;
import entity.Car;
import entity.RootVegetable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RandomDataProviderTest {

    @Test
    public void notExistFile() {
        List expectedData = new ArrayList();
        DataProvider dataProvider = new DataProvider(new RandomDataProviderStrategy("src/main/resources/car"));
        List data = dataProvider.provideData(3, "car");

        Assertions.assertEquals(expectedData, data);
    }

    @Test
    public void correctFilePath() {
        DataProvider dataProvider = new DataProvider(new RandomDataProviderStrategy("src/main/resources/cars"));
        List<Car> data = dataProvider.provideData(3, "cars");

        Assertions.assertEquals(false, data.isEmpty());
    }

    @Test
    public void provideBooksData() {
        DataProvider dataProvider = new DataProvider(new RandomDataProviderStrategy("src/main/resources/books"));
        List<Book> data = dataProvider.provideData(2, "books");

        Assertions.assertEquals(2, data.size());
        Assertions.assertNotNull(data.get(0).getAuthor());
        Assertions.assertNotNull(data.get(0).getTitle());
        Assertions.assertTrue(data.get(0).getQuantityPage() > 0);
    }

    @Test
    public void provideVegetablesData() {
        DataProvider dataProvider = new DataProvider(new RandomDataProviderStrategy("src/main/resources/vegetables"));
        List<RootVegetable> data = dataProvider.provideData(5, "vegetables");

        Assertions.assertEquals(5, data.size());
        Assertions.assertNotNull(data.get(0).getType());
        Assertions.assertNotNull(data.get(0).getColor());
        Assertions.assertTrue(data.get(0).getWeight() > 0);
    }

    @Test
    public void provideCarsData() {
        DataProvider dataProvider = new DataProvider(new RandomDataProviderStrategy("src/main/resources/cars"));
        List<Car> data = dataProvider.provideData(3, "cars");

        Assertions.assertEquals(3, data.size());
        Assertions.assertNotNull(data.get(0).getModel());
        Assertions.assertTrue(data.get(0).getPower() > 0);
        Assertions.assertTrue(data.get(0).getYearOfProduction() > 0);
    }


    @Test
    public void provideMoreLengthThanInFile() {
        DataProvider dataProvider = new DataProvider(new RandomDataProviderStrategy("src/main/resources/cars"));
        List<Car> data = dataProvider.provideData(100, "cars");

        Assertions.assertEquals(30, data.size());
    }

}
