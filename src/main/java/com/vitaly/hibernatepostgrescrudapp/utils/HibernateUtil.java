package com.vitaly.hibernatepostgrescrudapp.utils;
//  11-Jan-24
// gh crazym8nd

import com.vitaly.hibernatepostgrescrudapp.model.Label;
import com.vitaly.hibernatepostgrescrudapp.model.Post;
import com.vitaly.hibernatepostgrescrudapp.model.Writer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null){
            final StandardServiceRegistry registry =
                    new StandardServiceRegistryBuilder()
                            .build();
            try {
                sessionFactory =
                        new MetadataSources(registry)
                                .addAnnotatedClass(Label.class)
                                .addAnnotatedClass(Post.class)
                                .addAnnotatedClass(Writer.class)
                                .buildMetadata()
                                .buildSessionFactory();
            }
            catch (Exception e) {
                // The registry would be destroyed by the SessionFactory, but we
                // had trouble building the SessionFactory so destroy it manually.
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }
        return sessionFactory;
    }
}
