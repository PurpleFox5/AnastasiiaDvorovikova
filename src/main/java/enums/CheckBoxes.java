package enums;

public enum CheckBoxes {
    WATER("Water"),
    EARTH("Earth"),
    WIND("Wind"),
    FIRE("Fire");

    String name;

    CheckBoxes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
