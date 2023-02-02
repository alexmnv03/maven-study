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

-----------------------------------------
У каждого плагина есть несколько целей. И мы должны указать конкретную цель для плагина, который мы используем.
Например
mvn compiler:compile
где
compiler - это плагин
compile - это цель
Любое действие это запуск какой-нибудь цели (goal) у плагина
Это все плагины:
install
compiler
...

Плагин resources копирует файл из папки resources в target
mvn resources:resources

можно запускать несоклько плагинов сразу
mvn resources:resources compiler:compile

> Все параметры начинаются с символа -D

В Maven есть три жизненых цикла
- clean - включает в себя только clean
- defaul - содержит много плагинов, и запуск каждого из них запускает все предыдущие из списка
- site - создает документацию

Жизненный цикл чтобы упроститьт запуск нескольких плагинов, например запуск жизненного цикла
mvn compile
это тоже самое как
mvn resources:resources compiler:compile

mvn package запускает еще больше плагин
resources:resources compiler:compile тесты и сборку проекта в jar

Самые полпулярные плагины жизненого цикла defaul
- validate
- compile
- test
- package
- verify
- install
- deploy

Допустим у нас какой-то тест не проходит, а надо собрать проект, мы можем отключить тесты
mvn package с параметром skip и тесты не будут запущены
