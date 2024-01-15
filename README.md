Задание:
Необходимо реализовать консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:

Writer (id, firstName, lastName, List<Post> posts)

Post (id, content, created, updated, List<Label> labels)

Label (id, name)

PostStatus (enum ACTIVE, UNDER_REVIEW, DELETED)

Технологии: Java, PostgreSQL, Hibernate, Flyway, Maven.


Для того,чтобы ознакомиться с проектом,вам понадобится:
-JAVA 17
-POSTGRES 15

1.Скачиваем или клонируем репозиторий.
2.Устанавливаем проект с помощью Maven
3.Выполняем migration из раздела Plugins/flyway
4.Запускаем процесс main из AppRunner


