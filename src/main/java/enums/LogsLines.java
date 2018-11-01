package enums;

public enum LogsLines {
    CHECHBOX_TRUE(": condition changed to true"),
    CHECHBOX_FALSE(": condition changed to false"),
    RADIO_CHANGE("metal: value changed to "),
    DROPDOWN("Colors: value changed to Yellow"),

    RANGE2_FROM("Range 2(From):"),
    RANGE2_TO("Range 2(To):"),
    SLIDER(" link clicked");

    public String line;

    LogsLines(String line){
        this.line = line;
    }

    @Override
    public String toString() {
        return line;
    }
}
