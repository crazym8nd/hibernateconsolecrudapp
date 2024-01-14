package com.vitaly.hibernatepostgrescrudapp.service;

import com.vitaly.hibernatepostgrescrudapp.dao.WriterDao;
import com.vitaly.hibernatepostgrescrudapp.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//  14-Jan-24
// gh crazym8nd

class WriterServiceTest {

    private final WriterDao writerDaoMock = mock(WriterDao.class);
    private final WriterService writerService = new WriterService(writerDaoMock);
    private final List<Label> postLabels = Arrays.asList(new Label (1, "label1", Status.ACTIVE),
            new Label (2, "label2", Status.ACTIVE),
            new Label (3, "label3", Status.ACTIVE));
    private final List<Post> posts1 = List.of(new Post(1, "content", "created", "updated", PostStatus.ACTIVE, postLabels, new Writer()));
    private final List<Post> posts2 = List.of(new Post(2, "content", "created", "updated", PostStatus.ACTIVE, postLabels, new Writer()));

    private  Writer writer1 = new Writer(1, "writer1","testwriter1",posts1, Status.ACTIVE);
    private final Writer writer2 = new Writer(2, "writer2","testwriter2", posts2, Status.ACTIVE);



    private final List<Post> mockPostList = Arrays.asList(new Post(1, "content", "created", "updated", PostStatus.ACTIVE, postLabels, writer1),
            new Post(2, "content", "created", "updated", PostStatus.ACTIVE, postLabels, writer2),
            new Post(3, "content", "created", "updated", PostStatus.ACTIVE, postLabels, writer1));

    private final List<Writer> mockWriterList = Arrays.asList(writer1, writer2);
    private final Post mockPost = mockPostList.get(0);

    //happy scenario
    @Test
    void getWritersSuccess() {
        when(writerDaoMock.getWriters()).thenReturn(mockWriterList);
        assertEquals(mockWriterList, writerService.getWriters());
    }

    @Test
    void getWriterSuccess() {
        when(writerDaoMock.getWriterById(1)).thenReturn(mockWriterList.get(0));
        assertEquals(mockWriterList.get(0), writerService.getWriter(1));
    }

    @Test
    void saveWriterSuccess() {
        writerService.saveWriter(mockWriterList.get(0));
        verify(writerDaoMock,times(1)).saveWriter(mockWriterList.get(0));

    }

    @Test
    void updateSuccess() {
        writerService.update(mockWriterList.get(0));
        verify(writerDaoMock,times(1)).update(mockWriterList.get(0));
    }

    @Test
    void deleteByIdSuccess() {
        writerService.deleteById(1);
        verify(writerDaoMock,times(1)).deleteById(1);
    }

    //negative scenario

    @Test
    void getWritersNeg() {
        when(writerDaoMock.getWriters()).thenReturn(null);
        assertNull(writerService.getWriters());
    }

    @Test
    void getWriterNeg() {
        when(writerDaoMock.getWriterById(999)).thenReturn(null);
        assertNull(writerService.getWriter(999));
    }

    @Test
    void saveWriterNeg() {
        writer1 = null;

        assertNull(writerService.saveWriter(writer1));
    }

    @Test
    void updateNeg() {
        writer1 = null;

        assertNull(writerService.update(writer1));
    }

    @Test
    void deleteByIdNeg() {
        writerService.deleteById(999);
        verify(writerDaoMock,times(1)).deleteById(999);
    }
}