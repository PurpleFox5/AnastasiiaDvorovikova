package dataProviders;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider
    public Object[][] simpleDataProviders() {
        return new Object[][]{
                {"String1", 1},
                {"String2", 2},
                {"String4", 3}
        };
    }

}
