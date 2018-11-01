package enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MenuService {
    SUPPORT("SUPPORT"),
    DATES("DATES"),
    COMPLEX_TABLE("Complex Table"),
    SIMPLE_TABLE("Simple Table"),
    USER_TABLE("User Table"),
    TABLE_WITH_PAGES("Table with pages"),
    DIFFERENT_ELEMENTS("Different elements"),
    PERFORMANCE("Performance");

    String name;

    MenuService(String name) {
        this.name = name;
    }

    public static List<String> getNames() {
        return Arrays.stream(MenuService.values()).map(e->e.name).collect(Collectors.toList());
    }
}
