HW2:
1.	Setup project that has been discussed in the class. Make sure you can run both tests (native an d web) successfully.
2.	For existing native mobile autotest try to use another locator (xpath, classname, ?). Define these locators using Appium Inspector. Are there any difference with id version?
Локатор id состоит из названия пакета + “:id/” + id элемента
Пример: com.example.android.contactmanager:id/contactList
Xpath медленный и Appium советует “Using XPath locators is not recommended and can lead to fragile tests.”
Можно по классу className, но название класса может повторятся в приложении, например в тестовом приложении несколько классов с именем android.widget.EditText, android.widget.TextView и другие
Можно еще искать локаторы по “accessibility id”, которые удобны в написании
3.	Modify existing tests to run on a real device. What should be changed? 
Изменяется название устройства (deviceName в capabilities)
4.	Connect a real device to Appium (describe required actions) and run tests. Are there any difference with run on emulator?
В Appium Desired Capabilities для Native будут такими:
{
  "platformName": "Android",
  "deviceName": "c746c9857cf5",
  "app": "C:\\Users\\nasta\\Documents\\Study\\EpamJava\\DvorovikovaAnastasiia\\src\\main\\resources\\ContactManager.apk",
}
Для web:
{
  "platformName": "Android",
  "deviceName": "c746c9857cf5",
  "browserName": "Chrome"
}
Тоже меняем deviceName
5.	What should be improved/changed in existing test code? Why, for what?
Заменить Thread.sleep 
Значения прямо в классах (захардкожены), вынести их в конфигурационный файл *.properties
Неправильная стркутура проекта (не тестовые классы находятся в ветке src/test)
Были абсолютные пути, из-за которых будет не запустить наши тесты другому пользователю со своего компьютера, исправлены на пути относительно проекта
Лучше использовать enum MobileCapabilityType.DEVICE_NAME вместо “deviceName” и других параметров Capability
