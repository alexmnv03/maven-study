# Тут находится общая информация по работе с maven 

## Изучаем maven
> Apache Maven — фреймворк для автоматизации сборки проектов на основе описания их структуры в файлах на языке POM (англ. Project Object Model), являющемся подмножеством XML

Maven - это просто набор плагинов.
Каждый плагни содержит несколько goal.
Все что мы делаем в Maven - работаем с плагинами и вызываем у него один или несолько goal.
goal - цель, указывается после двоеточия в командной строке или в помнике
mvn compiler:compile
compile - это цель
У каждого плагина есть обязательная цель это help
mvn compiler:help

## Создадим вручну maven проект из командной строки
Откроем корневую папку проекта и выолним команду
'''mvn archetype:generate'''
на первые вопросы нажимаем ввод
groupId вводим com.alex.mavenstudy
artifactId вводим part-020
Проект создан

Можно запустить сразу с опцями
'''
mvn archetype:generate -DarchetypeGroupId=com.alex.mavenstudy -DartifactId=part-010
'''
Версия проекта - это
major.minor.increment-qualifier
major - основная версия
minor - фик или небольшой релиз
increment-qualifier - небольшие баг-фиксы
Еще к версии может добавляться 1.3.45-SNAPSHOT - что говорит о том, что это не релизная версия,
а версия на этапе разработки, т.е. ПО в продакшене еще не было

## Выполним компиляцию
mvn compile
После чего увидим в папке target наш class

Запустим тесты
mvn test

## Создадим Jar файл
mvn package
после чего в папке target появится файл part-010-1.0-SNAPSHOT.jar
Запустим его на выполнение
java -cp target/part-010-1.0-SNAPSHOT.jar com.alex.mavenstudy.App
и увидим результат выполнения

Тоже самое можно сделать используя IntelliJ IDEA, при этом можем выбрать готовый archetype или
пустой проект maven с чистым помником.

-----------------------------------------
### У каждого плагина есть несколько целей. И мы должны указать конкретную цель для плагина, 
который мы используем.
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
> формат параметров -Dkey=value
'''-DgroupId=com.alex.mylib'''
> Есть еще жестко определенные зарезервированные опции, которые мы не можем изменять, добавлять
> -Xms512m - указываем размер хипа в Mb
> И еще один вариант очень редкий для конфигурирования компилятора
> -XX...

## В Maven есть три жизненых цикла
- clean - включает в себя только clean
- defaul - содержит много плагинов, и запуск каждого из них запускает все предыдущие из списка
- site - создает документацию

Жизненный цикл чтобы упроститьт запуск нескольких плагинов, например запуск жизненного цикла
mvn compile
это тоже самое как
mvn resources:resources compiler:compile

mvn package запускает еще больше плагин
resources:resources compiler:compile тесты и сборку проекта в jar

### Самые полпулярные плагины жизненого цикла defaul
- validate
- compile
- test
- package
- verify
- install
- deploy

> Допустим у нас какой-то тест не проходит, а надо собрать проект, мы можем отключить тесты
mvn package с параметром skip и тесты не будут запущены

mvn package -Dmaven.test.skip=true

тоже самое мы можем сделать и в pom-файле
прописав его в секции configuration:
'''
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <configuration>
                <skip>true</skip>
            </configuration>
        </plugin>
    </plugins>
</build>
'''


********************************************
Для однозначной идентификации любого проекта есть три параметра
groupId - обычно url компании
artifactId - имя проекта
version - версия

> Если в имени тега на конеце -s то это значит, что тег может содержать список этих же тегов
> только без -s


# Структура pom файла
pom состоит из четырех частей
- Gebneral - ни на что не влияет
    - licenses
    - general
- Build Enviroment - конфигурация различных Environment, т.е. окружения, например проект может
  быть собран проект (продакшен)
    - Environment information
    - profiles
- Pom Relationships - это основная часть конфигруции
    - groupId
    - artifactId
    - multimodule - для многомодульных проектов
    - inheritances - наследование
    - dependensies
- Build settings жизненный цикл проекта
    - plugins
    - resources
    - extension
    - reporting

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

# dependency -> scope
dependencies имеет такой пареметр как scope, который говрит когда использовать эту библиотеку
например
<dependency>
<groupId>junit</groupId>
<artifactId>junit</artifactId>
<version>4.11</version>
<scope>compile</scope>
</dependency>
compile - указывает, что эта зависимость нужна для компиляции проекта, т.е. без нее мы не 
скомпилируем проект, она будет использована при компиляции и при запуске, эту 
настройку можно
не указывать т.к. она идет по умолчанию

provaded - указывает, что она будет предоставлена кем то другим, т.е. будет добавлена другим 
    приложением и ее не нужно добавялть. Она указывается только чтобы вы могли увидеть нужные 
    нам функции из этой зависимости в своем коде
runtime - указывает, чтоб будет использована только при запуске, например для postgresql можно 
    смело указывать эту зависимость, т,к. он нам не нужен на этапе компиляции, а только при запуске
test - указывает, чтоб будет использована только при тестировании
import - указывает, что берем не jar файл а pom и берем зависимость из этого помника
system - не рекомендуется используется, она сообщает что либа лежит на локальном компе 

> если jar использует при запуске сам jar файл не помещается в наш jar, а вот если мы используем
> war, тогда помещается

# dependency -> транзитивные
Каждая dependencie может содержать другие dependencie и сама уже скачивать их
Посмотреть их можно командой
mvn dependency:analyze
или в виде дерева
mvn dependency:tree
Так же мы можем увидеть лишние зависимости и удалить их если мы их не используем
analyze так же сообщает о проблемах в конфигурации, какие зависмости лишние или устарели

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

## optional - не рекомендуется использвать
false - стоит по умолчанию
true - означает что если другой проект будет использовать наш в качестве зависимости, эта 
    зависимость не будет подключена

# Многомодульность

В главном помнике у нас будет такая зависимость
<modules>
<module>NameModule</module>
</modules>
Все модули будут наследваться от главного помника, т.е. можно в нем прописать какие-то общие
настройки и они будут добавлены во все модули, но надо не забыть прописать его как paren в
каждом модеуле, если это необходимо

groupId у родителя и наследника должны совпадать (обычно так делают), хотя можно и его тоже
переопределить
а artifactId наследник уже переопределяет

Каждый из модулей можно билдить отдельно, а можно сразу все модули сбилдить


# dependency -> Management
Допустим у нас в паренте обределена какая-то зависимость, которая используется в двух модулях
разными людми, но каждый из них подключил свою версию этой библиотеки. Чтобы во всех модулях
использовать только одну версию придумали

<dependencyManagement>
    <dependencies>
        <dependency>
         <groupId>junit</groupId>
         <artifactId>junit</artifactId>
         <version>4.11</version>
        </dependency>
    </dependencies>
</dependencyManagement>
Т.е. мы в родител указали зависимость junit и версию, а в модуляз наследникахз мы можем 
указывать эту зависимость, но без версии. А версия будет подтягиваться от родителя


# exec plagin
mvn exec:java -Dexec.mainClass:com.namePackage.nameClassMain

com.namePackage - полное имя пакета, если в классе указан пакет
nameClassMain - имя класса с модулем main

# profile

Используются для использования разных параметров в зависимости от указанного profile при звпуске

<profiles>
    <profile>
        <id>dev</id>
        <buld>...</buld>
    </profile>
    <profile>
        <id>prod</id>
        <buld>...</buld>
    </profile>
</profiles>

Выбор profile осуществляется следующим образом:
mvn clean package -Pdev

ActiveByDefault - делает этот профайл активнм по умолчанию

## test
Если мы откроем surefire-plugin и найдем там описание секции include, в которой прописано какие 
классы должны вызываться при запуске тестов, по умолчанию это
** /Test*.java
** /*Test.java
** /*Tests.java
** /*TestCase.java
** - означает в любом пакете
Т.е. вызываться будут только файлы соотвествующие этому шаблону
Но мы можем это переопределить

'''
<build>
 <plugins>
  <plugin>
   <groupId>org.apache.maven.plugins</groupId>
   <artifactId>maven-surefire-plugin</artifactId>
   <configuration>
    <includes>
        <include>
            ** /*Tst.java
        </include>
    </includes>
   </configuration>
  </plugin>
 </plugins>
</build>
'''
Теперь у нас будут запускать и файлы оканчивающикеся на Tst

