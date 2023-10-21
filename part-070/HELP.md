# Properties

Существует пять проперти к котором можно обращаться

- project в ней мы можем обратиться к любому свойсву из нашего помника
  ${project.version}
  project.name
  project.groupId
- settings - редко используется
  ${settings.localRepository}
- переменные прописаны в properties
- переменые окружения
    env.
  ${env.JAVA_HOME}
  ${env.OS}
- переменые среды
  System.getProperty

# Фильтринг
С помощью этих пременных можно переедовать их в файлы свойств из помника см. файл application.
properties
передаем версию проекта
project.version=${project.version}
получим текущую дирректорию
project.dir=${project.basedir}
тоже самое
project.dir=${basedir}

Но чтобы мавен подстваил в эти переменные значения, нужен фильтринг, т.к. по умолчанию туда ни 
чего не запишется

Чтобы проверить это запускаем 
mvn resourse:resourse
этот плагин скопирует наши ресурсы в дирректорию target, откроем там наш файл application.
properties и не увидим наших переменных

Чтобы это заработало нужно или в нашей помке или в корневой прописать следующий код
<resources>
    <resource>
        <directory>${project.basedir}/src/main/resources</directory>
        <filtering>true</filtering>
    </resource>
</resources>

Проверим и убедимся что в target файл с нужными нам значениями

    
