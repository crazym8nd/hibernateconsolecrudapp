package com.vitaly.hibernatepostgrescrudapp.dao;
//  13-Jan-24
// gh crazym8nd


import com.vitaly.hibernatepostgrescrudapp.model.Post;
import com.vitaly.hibernatepostgrescrudapp.model.PostStatus;
import com.vitaly.hibernatepostgrescrudapp.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

public class PostDao {

    public List<Post> getPosts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Post p LEFT JOIN FETCH p.postLabels WHERE postStatus = :status", Post.class)
                    .setParameter("status", PostStatus.ACTIVE)
                    .list();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Post getPostById(Integer postId){
        Post post;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            post = (Post) session.createQuery("FROM Post WHERE id = :id")
                    .setParameter("id", postId)
                    .list().get(0);
        }
        if(post != null){
            return post;
        } else {
            throw new IllegalArgumentException("Post not found");
        }
    }

    public Post savePost(Post post) {
        if (post != null) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.persist(post);
                session.getTransaction().commit();
            }
        }
        return post;
    }

    public Post update(Post post){
        if (post != null && post.getPostStatus() == PostStatus.ACTIVE) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.merge(post);
                session.getTransaction().commit();
                return post;
            }
        } else {
            return new Post(-1, "NO SUCH POST","","",null, null, null);
        }
    }

    public void deleteById(Integer postId){
        Post post = getPostById(postId);
        if(post.getPostStatus() != PostStatus.DELETED){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                session.beginTransaction();
                post.setPostStatus(PostStatus.DELETED);
                session.merge(post);
                session.getTransaction().commit();
            }
        } else {
            throw new IllegalArgumentException("Post not found");
        }
    }

}