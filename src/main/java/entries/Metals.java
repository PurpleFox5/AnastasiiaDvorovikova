package entries;

import com.google.gson.annotations.SerializedName;

public enum Metals {
    @SerializedName("Gold")
    GOLD("Gold"),

    @SerializedName("Silver")
    SILVER("Silver"),

    @SerializedName("Bronze")
    BRONZE("Bronze"),

    @SerializedName("Selen")
    SELEN("Selen");

    String s;

    Metals(String s) {
        this.s = s;
    }

    public static Metals getMetal(String s) {
        for (Metals metal : Metals.values()) {
            if (metal.s.equalsIgnoreCase(s)) {
                return metal;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return s;
    }
}
