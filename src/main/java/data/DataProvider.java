package data;

import java.util.List;

public class DataProvider {

    private DataProviderStrategy<Object> dataProviderStrategy;

    public DataProvider(DataProviderStrategy<Object> dataProviderStrategy) {
        this.dataProviderStrategy = dataProviderStrategy;
    }

    public List<Object> provideData(int length, String dataType) {
        return dataProviderStrategy.provideData(length, dataType);
    }
}
