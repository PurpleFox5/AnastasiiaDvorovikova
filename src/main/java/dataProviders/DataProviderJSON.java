package dataProviders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entries.Data;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class DataProviderJSON {
    @DataProvider
    public Object[] dataProviders() {

        Map<String, Data> map = null;

        try (Reader reader = new FileReader("src\\main\\resources\\metalsColorsDataSet.json")) {
            Gson gson = new GsonBuilder().create();
            map = gson.fromJson(reader, new TypeToken<Map<String, Data>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map.values().toArray();
    }
}
