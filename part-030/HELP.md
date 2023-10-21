# Verify phase

Эта фаза отвечает за интеграционные тесты, ей предшествуют три подфайзы
pre-integration-test
integration-test
post-integration-test
но для запуска integration тестов рекомендуеся указывать Goal verify

Для этих тестов используется специальный плагин maven-failsafe-plugin

Так же как и для обычных тестов сущевтуеют посфиксы по умолячанию для файлов интеграционныйх тестов
Создадим файл ExampleIT.java

Вызовем обе gols у данного плагина именно так советуют делать в документации

запускаем
mvn verify
Мы видим что наш тест прошел

Если мы запускаем в ручную, без IDE, то необходимо добвать опции компилятора
<maven.compiler.source>17</maven.compiler.source>
<maven.compiler.target>17</maven.compiler.target>
