import data.DataProvider;
import data.FileDataProviderStrategy;
import data.RandomDataProviderStrategy;
import entity.Car;
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

}
