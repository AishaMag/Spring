# Spring
Разработка заглушек


1) команда для запуска jar-файла без явного указания профиля настроек
   java -Xms2048m -Xmx4096m -jar Spring-main\target\stub-0.0.1-SNAPSHOT.jar(путь до заглушки)
2) команда для запуска jar-файла с профилем test1
   java -Dspring.profiles.active=test1 -Xms2048m -Xmx4096m -jar Spring-main\target\stub-0.0.1-SNAPSHOT.jar(путь до заглушки)
3) команда для запуска jar-файла с профилем test2
   java -Dspring.profiles.active=test2 -Xms2048m -Xmx4096m -jar Spring-main\target\stub-0.0.1-SNAPSHOT.jar(путь до заглушки)
