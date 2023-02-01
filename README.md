# maven-study

## Изучаем maven
> Apache Maven — фреймворк для автоматизации сборки проектов на основе описания их структуры в файлах на языке POM (англ. Project Object Model), являющемся подмножеством XML

Создадим вручну maven проект из командной строки
Откроем корневую папку проекта и выолним команду
'''mvn archetype:generate'''
на первые вопросы нажимаем ввод
groupId вводим com.alex.mavenstudy
artifactId вводим part-010
Проект создан

Выполним компиляцию
mvn compile
После чего увидим в папке target наш class

Запустим тесты
mvn test

Создадим Jar файл
mvn package
после чего в папке target появится файл part-010-1.0-SNAPSHOT.jar
Запустим его на выполнение
java -cp target/part-010-1.0-SNAPSHOT.jar com.alex.mavenstudy.App
и увидим результат выполнения

Тоже самое можно сделать используя IntelliJ IDEA, при этом можем выбрать готовый archetype или 
пустой проект maven с чистым помником.

