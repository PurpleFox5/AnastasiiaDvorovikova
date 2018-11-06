package entries;

import com.google.gson.annotations.SerializedName;

public enum Colors {

    @SerializedName("Red")
    RED("Red"),

    @SerializedName("Green")
    GREEN("Green"),

    @SerializedName("Blue")
    BLUE("Blue"),

    @SerializedName("Yellow")
    YELLOW("Yellow");

    String s;

    Colors(String s) {
        this.s = s;
    }

    public static Colors getColor(String s) {
        for (Colors color : Colors.values()) {
            if (color.s.equalsIgnoreCase(s)) {
                return color;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return s;
    }
}
