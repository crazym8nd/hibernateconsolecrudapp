package com.vitaly.hibernatepostgrescrudapp.dao;
//  11-Jan-24
// gh crazym8nd
import java.util.Collections;
import java.util.List;

import com.vitaly.hibernatepostgrescrudapp.model.Label;
import com.vitaly.hibernatepostgrescrudapp.utils.HibernateUtil;

import org.hibernate.Session;

public class LabelDao {

    public List<Label> getLabels()
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Label", Label.class).list();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Label getLabel(Integer label_id) {
        Label label = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            label = (Label) session.createQuery("from Label where label_id =" + label_id).list().get(0);
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return label;
    }


}
