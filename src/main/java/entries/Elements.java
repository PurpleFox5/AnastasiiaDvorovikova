package entries;

import com.google.gson.annotations.SerializedName;

public enum Elements {
    @SerializedName("Water")
    WATER("Water"),

    @SerializedName("Earth")
    EARTH("Earth"),

    @SerializedName("Fire")
    FIRE("Fire"),

    @SerializedName("Wind")
    WIND("Wind");

    String s;

    Elements(String s) {
        this.s = s;
    }

    public static Elements getElement(String s) {
        for (Elements element : Elements.values()) {
            if (element.s.equalsIgnoreCase(s)) {
                return element;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return s;
    }
}

