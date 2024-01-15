package com.vitaly.hibernatepostgrescrudapp.model;
//  13-Jan-24
// gh razym8nd
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.FetchProfile;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
    @NonNull
    private PostStatus postStatus;

   @ManyToMany(cascade =CascadeType.ALL, fetch= FetchType.LAZY)
   @JoinTable(name ="post_labels",
              joinColumns = @JoinColumn(name = "post_id"),
              inverseJoinColumns = @JoinColumn(name = "label_id"))
    private List<Label> postLabels = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Writer writer;

    public Post(String created, String updated, PostStatus postStatus, List<Label> postLabels, Writer writer){
        this.created = created;
        this.updated = updated;
        this.postStatus = postStatus;
        this.postLabels = postLabels;
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Post "+ id +
                ", content: " + content +
                ", created: " + created +
                ", updated: " + updated +
                ", Labels: " + postLabels;
    }
}
