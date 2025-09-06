package data;

import interfaces.DataProviderStrategy;

import java.util.List;

public class DataProvider<T> {

    private DataProviderStrategy<T> dataProviderStrategy;

    public DataProvider(DataProviderStrategy<T> dataProviderStrategy) {
        this.dataProviderStrategy = dataProviderStrategy;
    }

    public List<T> provideData(int length, String dataType) {
        return dataProviderStrategy.provideData(length, dataType);
    }

}
