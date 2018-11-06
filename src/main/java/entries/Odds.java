package entries;

import com.google.gson.annotations.SerializedName;

public enum Odds {

    @SerializedName("1")
    ONE("1"),

    @SerializedName("3")
    THREE("3"),

    @SerializedName("5")
    FIVE("5"),

    @SerializedName("7")
    SEVEN("7");

    public String s;

    Odds(String value) {
        this.s = s;
    }

    public static Odds getOdds(String s) {
        for (Odds odd : Odds.values()) {
            if (odd.s.equalsIgnoreCase(s)) {
                return odd;
            }
        }
        return null;
    }
}
