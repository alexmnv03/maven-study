# add lib in project 

Рассмотрим как  добавить jar файл в локальный репозиторий maven 

Задача: у нас есть библиотека в виде jar-файл и нам надо подключить ее к нашему проекту.
Рассмотрим это на примере известного плагина lombok и поможет нам в этом maven
1. Скачаем jar-файл и сохраним его в любой дирректории, например D:\my-lib\my-lang
2. необходимо поместить его в локальный репозиторий maven, который находится в папке .m2
для чего выполним следующую команду
mvn install:install-file -Dfile=D:\my-lib\my-lang\my-lang.jar \
	-DgroupId=com.alex.mylib \
	-DartifactId=mylang \
	-Dversion=0.0.1 \
	-Dpackaging=jar \
	-DgeneratePom=true

При запуске из под Windows в PowerShell заработало только так:
mvn install:install-file "-Dfile=D:\my-lib\my-lang\my-lang.jar" "-DgroupId=com.alex.mylib" "-DartifactId=mylang" "-Dversion=0.0.1" "-Dpackaging=jar" "-DgeneratePom=true"


После чего эта библиотека оказется в локальном репозитории maven

3. Подключаем как обычно нашу библиотеку в проекте и проверяем.
   

 
Если мы хотим положить библиотек в какою-то свою папку, например local-maven-repo, в корне вашего проекта Maven.
Создаем ее, перейдите в корень вашего проекта и выполните следующую команду
mvn deploy:deploy-file -DgroupId=[GROUP] -DartifactId=[ARTIFACT] -Dversion=[VERS] -Durl=file:./local-maven-repo/ -DrepositoryId=local-maven-repo -DupdateReleaseInfo=true -Dfile=[FILE_PATH]   

Так же необходимо добавить локальный репозиторий внутри вашего<project>pom.xml:

<repositories>
    <repository>
        <id>local-maven-repo</id>
        <url>file:///${project.basedir}/local-maven-repo</url>
    </repository>
</repositories>
