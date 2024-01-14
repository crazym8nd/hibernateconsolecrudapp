package com.vitaly.hibernatepostgrescrudapp.model;
//  13-Jan-24
// gh crazym8nd
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name ="post_labels",
              joinColumns = @JoinColumn(name = "post_id"),
              inverseJoinColumns = @JoinColumn(name = "label_id"))
    private List<Label> postLabels = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Writer writer;

    public Post(String created, String updated, PostStatus postStatus, List<Label> postLabels){
        this.created = created;
        this.updated = updated;
        this.postStatus = postStatus;
        this.postLabels = postLabels;
    }

    @Override
    public String toString() {
        return "Post "+ id +
                " (content " + content +
                ", created: " + created +
                ", updated: " + updated +
                ", postStatus: " + postStatus +
                ", postLabels: " + postLabels +
                ", writer: " + writer;
    }

}
