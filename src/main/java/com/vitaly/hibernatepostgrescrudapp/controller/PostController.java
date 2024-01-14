package com.vitaly.hibernatepostgrescrudapp.controller;
//  14-Jan-24
// gh crazym8nd


import com.vitaly.hibernatepostgrescrudapp.model.Post;
import com.vitaly.hibernatepostgrescrudapp.service.PostService;

import java.util.List;

public class PostController {
    private final PostService postService = new PostService();

    public Post createPost(Post post){
        return postService.savePost(post);
    }

    public List<Post> getPosts(){
        return postService.getPosts();
    }
    public Post getPost(Integer postId){
        return postService.getPost(postId);
    }

    public Post updatePost(Post post){
        return postService.update(post);
    }

    public void deleteById(Integer postId){
        postService.deleteById(postId);
    }
}

