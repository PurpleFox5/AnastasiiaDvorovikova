package entries;

import com.google.gson.annotations.SerializedName;

public enum Vegetables {
    @SerializedName("Cucumber")
    CUCUMBER("Cucumber"),

    @SerializedName("Tomato")
    TOMATO("Tomato"),

    @SerializedName("Vegetables")
    VEGETABLES("Vegetables"),

    @SerializedName("Onion")
    ONION("Onion");

    String s;

    Vegetables(String s) {
        this.s = s;
    }

    public static Vegetables getVegetable(String s) {
        for (Vegetables vegetable : Vegetables.values()) {
            if (vegetable.s.equalsIgnoreCase(s)) {
                return vegetable;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return s;
    }
}
