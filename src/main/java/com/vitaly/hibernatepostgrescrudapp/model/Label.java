package com.vitaly.hibernatepostgrescrudapp.model;
//  11-Jan-24
// gh crazym8nd
import jakarta.persistence.*;

@Entity
@Table(name = "labels")
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
    private Status status;

    public Label() {}
    public Label(String name, Status status) {
        this.name = name;
        this.status = status;
    }
    public Label(Integer id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Label "+ id +" ("+ name + "," + status + ')';
    }
}
