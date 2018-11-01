package enums;

public enum RadioButtons {
    GOLD("Gold"),
    SILVER("Silver"),
    BRONZE("Bronze"),
    SELEN("Selen");

    String name;

    RadioButtons(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
