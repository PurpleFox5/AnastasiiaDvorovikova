package mobile.hw4;

public enum PropertyFile {

    WEB_PROPERTY("webtest.properties"),
    NATIVE_PROPORTY("nativetest.properties");

    String value;

    PropertyFile(String s) {
        value = s;
    }

    @Override
    public String toString() {
        return value;
    }
}
