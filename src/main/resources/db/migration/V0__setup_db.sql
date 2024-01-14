CREATE TABLE labels(
                       id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
                       name TEXT,
                       status TEXT
);
CREATE TABLE posts(
                      id       int GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
                      content  TEXT,
                      created  TEXT,
                      updated  TEXT,
                      post_status TEXT
);
CREATE TABLE writers(
                        id       int GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
                        first_name  TEXT,
                        last_name  TEXT,
                        status  TEXT
);
CREATE TABLE writer_posts(
                            writer_id int NOT NULL,
                            post_id int NOT NULL,
                            constraint writer_posts_writer_fk
                            foreign key (writer_id) references writers,

                            constraint writer_posts_posts__fk
                            foreign key (post_id) references posts,

                            constraint writer_posts_pk
                            unique (post_id, writer_id)
);
CREATE TABLE post_labels(
                            post_id int NOT NULL,
                            label_id int NOT NULL,
                            constraint post_labels_post_fk
                            foreign key (post_id) references posts,

                            constraint post_labels_labels_fk
                            foreign key (label_id) references labels,

                            constraint post_labels_pk
                            unique (post_id, label_id)

);