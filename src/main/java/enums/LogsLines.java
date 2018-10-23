package enums;

public enum LogsLines {
    WIND_TRUE("Wind: condition changed to true"),
    WATER_TRUE("Water: condition changed to true"),
    WIND_FALSE("Wind: condition changed to false"),
    WATER_FALSE("Water: condition changed to false"),
    SELEN("value changed to Selen"),
    YELLOW("value changed to Yellow"),
    SLIDER(" link clicked");


    public String line;

    LogsLines(String line){
        this.line = line;
    }
}
