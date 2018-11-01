package enums;

public enum Titles {
    HOME_PAGE("Home Page"),
    DIFFERENT_ELEMENTS("Different Elements"),
    DATA_PAGE("Dates");

    public String name;
    Titles(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
