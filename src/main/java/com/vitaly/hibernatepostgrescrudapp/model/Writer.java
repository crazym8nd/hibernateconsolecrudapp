package com.vitaly.hibernatepostgrescrudapp.model;
//  13-Jan-24
// gh crazym8nd


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "writers")
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "writer_posts",
            joinColumns = @JoinColumn(name = "writer_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<Post> writerPosts = new ArrayList<>();

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
}
