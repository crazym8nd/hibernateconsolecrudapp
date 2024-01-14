package com.vitaly.hibernatepostgrescrudapp.service;
//  14-Jan-24
// gh crazym8nd


import com.vitaly.hibernatepostgrescrudapp.dao.PostDao;
import com.vitaly.hibernatepostgrescrudapp.model.Post;

import java.util.List;

public class PostService {
    private final PostDao postDao;

    public PostService(PostDao postDao){
        this.postDao = postDao;
    }
    public PostService(){this.postDao = new PostDao();}

    public List<Post> getPosts(){
        return postDao.getPosts();
    }

    public Post getPost(Integer postId){
        return postDao.getPostById(postId);
    }

    public Post savePost(Post post){
        return postDao.savePost(post);
    }
    public Post update(Post post){
        return postDao.update(post);
    }

    public void deleteById(Integer integer){
        postDao.deleteById(integer);
    }
}
