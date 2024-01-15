package com.vitaly.hibernatepostgrescrudapp.dao;
//  14-Jan-24
// gh crazym8nd



import com.vitaly.hibernatepostgrescrudapp.model.Status;
import com.vitaly.hibernatepostgrescrudapp.model.Writer;
import com.vitaly.hibernatepostgrescrudapp.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

public class WriterDao {

    public List<Writer> getWriters(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Writer w LEFT JOIN FETCH w.writerPosts WHERE status = " + Status.ACTIVE, Writer.class).list();
        } catch (Exception e){
            return Collections.emptyList();
        }
    }

    public Writer getWriterById(Integer writerId){
        Writer writer;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            writer = (Writer) session.createQuery("FROM Writer WHERE id =" + writerId).list().get(0);
        }
        if (writer != null){
            return writer;
        } else {
            return new Writer(-1, "NO SUCH WRITER", "",null,null);
        }
    }

    public Writer saveWriter(Writer writer){
        if (writer != null){
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.persist(writer);
                session.getTransaction().commit();
            }
        }
        return writer;
    }

    public Writer update (Writer writer){
        if(writer != null && writer.getStatus() == Status.ACTIVE){
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.merge(writer);
                session.getTransaction().commit();
                return writer;
            }
        } else {
            return new Writer(-1, "NO SUCH WRITER", "",null,null);
        }
    }

    public void deleteById(Integer writerId){
        if(getWriterById(writerId).getStatus() != null && getWriterById(writerId).getStatus() != Status.ACTIVE){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                session.beginTransaction();
                Writer writer = session.get(Writer.class, writerId);
                writer.setStatus(Status.DELETED);
                session.merge(writer);
                session.getTransaction().commit();
            }
        } else {
            throw new IllegalArgumentException("Writer not found");
        }
    }
}
