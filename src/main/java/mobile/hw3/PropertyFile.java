package mobile.hw3;

public enum PropertyFile {

    WEB_PROPERTY("webtest.properties"),
    NATIVE_PROPERTY("nativetest.properties"),

    ANDROID_NATIVE("androidNative"),
    IOS_WEB("iOsWeb");

    String value;

    PropertyFile(String s) {
        value = s;
    }

    @Override
    public String toString() {
        return value;
    }
}
