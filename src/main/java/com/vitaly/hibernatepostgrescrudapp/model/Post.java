package com.vitaly.hibernatepostgrescrudapp.model;
//  13-Jan-24
// gh crazym8nd
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "created")
    private String created;

    @Column(name = "updated")
    private String updated;

    @Column(name = "post_status")
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinTable(name ="post_labels",
              joinColumns = @JoinColumn(name = "post_id"),
              inverseJoinColumns = @JoinColumn(name = "label_id"))
    private List<Label> postLabels = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Writer writer;
}
