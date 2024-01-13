package com.vitaly.hibernatepostgrescrudapp.dao;
//  11-Jan-24
// gh crazym8nd
import java.util.Collections;
import java.util.List;

import com.vitaly.hibernatepostgrescrudapp.model.Label;
import com.vitaly.hibernatepostgrescrudapp.utils.HibernateUtil;

import org.hibernate.Session;

public class LabelDao {

    public List<Label> getLabels() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Label", Label.class).list();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Label getLabel(Integer label_id) {
        Label label;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            label = (Label) session.createQuery("FROM Label WHERE id = " + label_id).list().get(0);
        }
        if(label != null ){
            return label;
        } else {
            return new Label(-1, null, null);
        }

    }

    public Label saveLabel(Label label) {
        if (label != null) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.persist(label);
                session.getTransaction().commit();
            }
        }
        return label;
    }

    public Label update(Label label) {
        if (label != null) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.merge(label);
                session.getTransaction().commit();
                return label;
            }
        } else {
            return new Label(-1, null, null);
        }
    }
    public void deleteById(Integer integer){
        if(getLabel(integer).getStatus() != null){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                session.beginTransaction();
                Label label = session.get(Label.class, integer);
                session.remove(label);
                session.getTransaction().commit();
            }
        } else {
            throw new IllegalArgumentException("Label not found");
        }
    }

}


