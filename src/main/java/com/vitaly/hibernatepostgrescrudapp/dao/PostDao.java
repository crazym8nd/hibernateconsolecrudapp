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
            return session.createQuery("FROM Post WHERE post_status = " + PostStatus.ACTIVE, Post.class).list();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Post getPostById(Integer postId){
        Post post;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            post = (Post) session.createQuery("FROM Post WHERE id =" + postId).list().get(0);
        }
        if(post != null){
            return post;
        } else {
            return new Post(-1, "NO SUCH POST","","",null, null, null);
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
        if(getPostById(postId).getPostStatus() != null && getPostById(postId).getPostStatus() != PostStatus.DELETED){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                session.beginTransaction();
                Post post = session.get(Post.class, postId);
                post.setPostStatus(PostStatus.DELETED);
                session.merge(post);
                session.getTransaction().commit();
            }
        } else {
            throw new IllegalArgumentException("Post not found");
        }
    }

}