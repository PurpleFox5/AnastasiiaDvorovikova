**HW3:**
1.	Rewrite (complete) Driver using “singleton” pattern. Are there any advantages?
Нужно добавить в класс приватное статическое поле, содержащее Singleton объект;
Сделать конструктор класса приватным;
Объявить статический создающий метод, который будет использоваться для получения единственного экземпляра драйвера.
2.	Suggest improvements for .properties reading. What are the purposes?
Разделим .properties на webproperties и nativeproperties.
Для загрузки и чтения properties не создаем отдельный класс, а читаем properties в классе Driver, который получает на вход enum PropertyFile;
Загружаем properties в try-with-resources, а читаем стандартным методом класса java.util.Properties getProperty(String key).
5.	Which checks would you place in the “web” test?
Проверить заголовок «Internet Assigned Numbers Authority»
Проверить отображается ли изображение “iana”
Проверить, что количество блоков = 3, а так же их заголовки
Перейти на другую страницу, проверить заголовок