**HW 4**
1.	Add support of appPackage and appActivity parameters for Android devices (reading from a .properties file and then setting in the DesiredCapabilities). Locally installed Appium DT has no need in these parameters, but for Appium server of Minsk Mobile Farm it’s mandatory.
Нужно на самой ферме выбрать вкладку Native на нужном устройстве, загрузить приложение в package выбрать com.example.android.contactmanager, а в Activity .ContactManager, а после уже запускать тесты
a.	Or try to use autoLaunch capability with app as before. Does this approach work?
Для того, чтобы установка приложения на устройстве проходила автоматически необхоимо в DesiredCapabilities добавить autoLaunch к уже прописанным путем до APP, appPackage и appActivity.

2.	Change settings to run web test on a certain iOS device on Mobile Test Farm. Run test with your changes. Did test pass?
Да, тест проходит
Были добавлены название устройства и udid в webtest.properties
deviceName=iPhone
udid=4dc76c4ed56e4db389bee0c6dcb97e13973b5821
А так же в Driver добавлена обработка capabilities
capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
capabilities.setCapability(MobileCapabilityType.UDID, UDID);
3.	Change settings to run native test on a certain/random Android device on Mobile Test Farm. Run test with your changes. Did test pass?
Нет, проверка виртуальной клавиатуры не проходит.
При изменении на `assertTrue(((AndroidDriver) getDriver()).isKeyboardShown());` тест стал проходить
4.	What’s wrong with our code? How to fix/improve it? Implement your suggestions.
В драйвере много строковых констант
В properties файлах нужно руками менять параметрами (чтение из файла)
Был написан класс Capabilities (со свойствами и аннотациями @SerializedName),DriverCapabilitiesFromJson, который методом gson.fromJson(reader, new TypeToken<Map<String, Capabilities>>() получает Capabilities. Чтение осуществляется с помощью методов capabilitiesJson.get<Name>(), которые генерируются аннотацией Lombok.
В enum proportiesFile были добавлены ANDROID_NATIVE("androidNative") и
IOS_WEB("iOsWeb").