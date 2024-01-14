package com.vitaly.hibernatepostgrescrudapp.service;

import com.vitaly.hibernatepostgrescrudapp.dao.PostDao;
import com.vitaly.hibernatepostgrescrudapp.model.*;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//  14-Jan-24
// gh crazym8nd

class PostServiceTest {

    private final PostDao postDaoMock = mock(PostDao.class);
    private final PostService postService = new PostService(postDaoMock);
    private final List<Label> postLabels = Arrays.asList(new Label (1, "label1", Status.ACTIVE),
                                                         new Label (2, "label2", Status.ACTIVE),
                                                         new Label (3, "label3", Status.ACTIVE));
    private final List<Post> posts1 = List.of(new Post(1, "content", "created", "updated", PostStatus.ACTIVE, postLabels, new Writer()));
    private final List<Post> posts2 = List.of(new Post(2, "content", "created", "updated", PostStatus.ACTIVE, postLabels, new Writer()));

    private final Writer writer1 = new Writer(1, "writer1","testwriter1",posts1, Status.ACTIVE);
    private final Writer writer2 = new Writer(2, "writer2","testwriter2", posts2, Status.ACTIVE);



    private final List<Post> mockPostList = Arrays.asList(new Post(1, "content", "created", "updated", PostStatus.ACTIVE, postLabels, writer1),
                                                     new Post(2, "content", "created", "updated", PostStatus.ACTIVE, postLabels, writer2),
                                                     new Post(3, "content", "created", "updated", PostStatus.ACTIVE, postLabels, writer1));

    private final List<Writer> mockWriterList = Arrays.asList(writer1, writer2);
    private Post mockPost = mockPostList.get(0);





    //Happy scenario
    @Test
    void getPostsSuccess() {
        when(postDaoMock.getPostById(1)).thenReturn(mockPostList.get(0));
        assertEquals(mockPost, postService.getPost(1));
    }

    @Test
    void getPostSuccess() {
        when(postDaoMock.getPostById(1)).thenReturn(mockPostList.get(0));
        assertEquals(mockPost, postService.getPost(1));
    }

    @Test
    void savePostSuccess() {
        postService.savePost(mockPost);
        verify(postDaoMock,times(1)).savePost(mockPost);
    }

    @Test
    void updateSuccess() {
        postService.update(mockPost);
        verify(postDaoMock,times(1)).update(mockPost);
    }

    @Test
    void deleteByIdSuccess() {
        postService.deleteById(1);
        verify(postDaoMock,times(1)).deleteById(1);
    }

    //negative scenario
    @Test
    void getPostsNeg() {
        when(postDaoMock.getPostById(999)).thenReturn(null);
        assertNull(postService.getPost(999));
    }

    @Test
    void getPostNeg() {
        when(postDaoMock.getPostById(999)).thenReturn(null);
        assertNull(postService.getPost(999));
    }

    @Test
    void savePostNeg() {
        mockPost = null;
        assertNull(postService.savePost(mockPost));
    }

    @Test
    void updateNeg() {
        mockPost = null;
        assertNull(postService.update(mockPost));
    }


    @Test
    void deleteByIdNeg() {
        postService.deleteById(999);
        verify(postDaoMock,times(1)).deleteById(999);
    }
}