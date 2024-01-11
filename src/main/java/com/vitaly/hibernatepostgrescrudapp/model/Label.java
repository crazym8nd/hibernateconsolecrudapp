package com.vitaly.hibernatepostgrescrudapp.model;
//  11-Jan-24
// gh crazym8nd

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class Label {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Status status;

    public Label() {}
    public Label(String name, Status status) {
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
}
