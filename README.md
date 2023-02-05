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

********************************************
Для однозначной идентификации любого проекта есть три параметра
groupId - обычно url компании
artifactId - имя проекта
version - версия

> Если в имени тега на конеце -s то это значит, что тег может содержать список этих же тегов 
> только без -s

# Супер pom
К каждому проекту пристегивается pom-файл с общими настройками. Это супер-pom Посмотреть его 
можно в файле maven-model-builder-3.8.4.jar для чего переименовываем его в zip и открыфваем как 
обычный архив и увидим там файл pom.xml

# Свойства
В помнике можно использовать переменные, например
<version>${project.groupId}-name</version>
project.groupId - свойство помника project->groupId
так моно обратиться к любому свойству нашего помника включа супер pomЮ который автоматически 
добавляется к нашему помнику
можно исползовать переменные среды
${env.JAVA_HOME}
или
${settings.localRepository}
А можем сами прписать любое свойство в теге
<properties>
    <my.version>1.7</my.version>
</properties>
${my.version}

# Настройки которые ни на чтно не вляют
Это особый тип настроек, которые ни как не вляют на сборку, например 
<name>
<url>
<licenses>
<organization>
<developers>

# maven repository
По умолчанию maven скачивает все библиотеки со своего репозитория, который прописан в файле 
супер-pom в секции 
<repositorys>

# dependencies
Что бы использовать сторонние библиотеки, то необходимо описать их в секции dependencies
Подключаем библиотеки по трем параметрам, которые мы рассмотрели выше
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.11</version>
        <scope>test</scope>
    </dependency>
</dependencies>

Все библиотеки сохраняются в папке .m2

# dependencie -> scope
dependencies имеет такой пареметр как scope, который говрит когда использовать эту библиотеку
например
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.11</version>
    <scope>compile</scope>
</dependency>
compile - указывает, что она будет исползована при компиляции и при запуске, эту настройку можно 
не указывать т.к. она идет по умолчанию 

provaded - указывает, чтоб будет использована только при компиляции
runtime - указывает, чтоб будет использована только при запуске
test - указывает, чтоб будет использована только при тестировании
import - указывает, что берем не jar файл а pom и берем зависимость из этого помника

> если jar использует при запуске сам jar файл не помещается в наш jar, а вот если мы используем
> war, тогда помещается

# dependencie -> транзитивные
Каждая dependencie может содержать другие dependencie и сама уже скачивать их
Посмотреть их можно командой 
mvn dependency:analyze
или в виде дерева
mvn dependency:tree

Бываеют такие ситуации, когда несколько библиотек испозует одну и туже библиотеку но с разными 
версиями и мы хотим, чтобы использовалась только одна библиотека нам надо удалить какую то 
зависимость. Для этого есть спец опция exclusion, т.е. мы исключаем ее
Смотрим зависимости и в velocity мы решили удалить зависимость oro
mvn dependency:tree
<dependencies>
    <dependency>
        <groupId>org.apache.velocity</groupId>
        <artifactId>velocity</artifactId>
        <version>1.5</version>
        <exclusions>
            <exclusion>
                <groupId>oro</groupId>
                <artifactId>oro</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>








