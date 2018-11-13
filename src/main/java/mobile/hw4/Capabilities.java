package mobile.hw4;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
class Capabilities {
    @SerializedName("aut")
    private String aut = null; // (mobile) app
    @SerializedName("sut")
    private String sut = null; // site under testing
    @SerializedName("platform")
    private String platform = "";
    @SerializedName("driver")
    private String driver = "";
    @SerializedName("deviceName")
    private String deviceName = "";
    @SerializedName("appPackage")
    private String appPackage = "";
    @SerializedName("appActivity")
    private String appActivity = "";
    @SerializedName("udid")
    private String udid = "";
}
