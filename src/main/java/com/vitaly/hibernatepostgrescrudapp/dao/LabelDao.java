package com.vitaly.hibernatepostgrescrudapp.dao;
//  11-Jan-24
// gh crazym8nd
import java.util.List;

import com.vitaly.hibernatepostgrescrudapp.model.Label;
import com.vitaly.hibernatepostgrescrudapp.utils.HibernateUtil;

import org.hibernate.Session;

public class LabelDao {

    public List<Label> getLabels()

    {
        List<Label> labels = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            labels = session.createQuery("from Label", Label.class).list();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return labels;
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
