Задание:<br>
Необходимо реализовать консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:<br>
Writer (id, firstName, lastName, List<Post> posts)<br>
Post (id, content, created, updated, List<Label> labels)<br>
Label (id, name)<br>
PostStatus (enum ACTIVE, UNDER_REVIEW, DELETED)<br>
Технологии: Java, PostgreSQL, Hibernate, Flyway, Maven.<br>

Для того,чтобы ознакомиться с проектом,вам понадобится:<br>
-JAVA 17<br>
-POSTGRES 15<br>

1.Скачиваем или клонируем репозиторий.<br>
2.Устанавливаем проект с помощью Maven<br>
3.Выполняем migration из раздела Plugins/flyway<br>
4.Запускаем процесс main из AppRunner<br>


