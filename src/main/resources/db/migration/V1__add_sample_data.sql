INSERT INTO labels (name,status) VALUES
                                     ('label A','ACTIVE'),
                                     ('label B','ACTIVE'),
                                     ('label C','DELETED'),
                                     ('label D','ACTIVE');
INSERT INTO posts (content, created, updated, post_status, writer_id)  VALUES
                                                                ('test content 1','NOW','NEW','ACTIVE',1),
                                                                ('test content 2','NOW','NEW','ACTIVE',2),
                                                                ('test content 3','NOW','NEW','DELETED',3),
                                                                ('test content 4','NOW','NEW','ACTIVE',4);
INSERT INTO writers (first_name, last_name, status) VALUES
                                                        ('Raymond','Douglas','ACTIVE'),
                                                        ('Erich Maria','Remarque','ACTIVE'),
                                                        ('George','Orwell','DELETED'),
                                                        ('Jack','London','ACTIVE');
INSERT INTO post_labels (post_id, label_id) VALUES
                                                 (1,1),
                                                 (1,2),
                                                 (2,2),
                                                 (4,4);
INSERT INTO writer_posts (writer_id, post_id) VALUES
                                                 (1,1),
                                                 (2,2),
                                                 (4,4);