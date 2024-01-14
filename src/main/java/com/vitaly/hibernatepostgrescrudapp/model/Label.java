package com.vitaly.hibernatepostgrescrudapp.model;
//  11-Jan-24
// gh crazym8nd
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "labels")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @NonNull
    @Enumerated(EnumType.STRING)
    private Status status;

    public Label(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Label " + id +
                ", name: " + name;
    }
}
