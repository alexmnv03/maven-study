# module

помки могут наследоваться как и объекты
Чтобы другие помки могли наследоваться от текущей ее тип надо выбрать как pom

В главный помник добавляем секуцию <modules> где будем прописывать все наши модули
А в каждом модуле наследуемся от наше главной помки
<parent>
    <groupId>com.alex.mavenstudy</groupId>
    <artifactId>study</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</parent>

Но для этого надо запуститьт сборку нашего главного пормника, чтобы он прописался в репозитории
mvn install

Все свойства и зависимости из главного помника будут доступны в наследуемых.

От любой из наследуемых помников в свою очередь можно тоже наследоваться, только надо сделать 
его тип как pom

Мы можем не указывать версию текущей помки, тогда она будет ее брать от родителя, но если ее 
укажем, то мы как бы переопределяем ее

Но тут есть одна проблема, что наши дочернии помки получают все зависимости родительской, а это 
не совсем правильно. Чтобы это исправить надо в родительском помнике использовать  
dependencyManagement, см. пример
Туда мы помещаем все зависимости нашего проекта, настраиваем их. А в родительских помках помещаем 
только 
нужные нам зависимости, указывая только groupId и artifactId, а остальные подтянутся сами от 
родителя. Соответсвенно, зависимости, которые мы не указали у дочернего пом подтягиваться не будут.
А если нам в каком то модуле надо использовать зависимость другой версии или изменить ее 
настройки, то мы указываем ее версию и она будет переопределна.

Для плагинов делается тоже самое, только используя pluginManagement
