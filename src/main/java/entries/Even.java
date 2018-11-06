package entries;

import com.google.gson.annotations.SerializedName;

public enum Even {

    @SerializedName("2")
    TWO(2),

    @SerializedName("4")
    FOUR(4),

    @SerializedName("6")
    SIX(6),

    @SerializedName("8")
    EIGHT(8);

    public int value;

    Even(int value) {
        this.value = value;
    }
}
