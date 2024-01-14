package com.vitaly.hibernatepostgrescrudapp.service;

import com.vitaly.hibernatepostgrescrudapp.dao.LabelDao;
import com.vitaly.hibernatepostgrescrudapp.model.Label;
import com.vitaly.hibernatepostgrescrudapp.model.Status;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//  14-Jan-24
// gh crazym8nd

class LabelServiceTest {
    private final LabelDao labelDaoMock = mock(LabelDao.class);

    private final LabelService labelService = new LabelService(labelDaoMock);
    private final List<Label> mockList = Arrays.asList(new Label(1, "label1", Status.ACTIVE),
                new Label(2, "label2", Status.ACTIVE),
                new Label(3, "label3", Status.ACTIVE));
    private Label mockLabel = mockList.get(0);

    //happy scenario
    @Test
    void test_getLabelsSuccess() {
        when(labelDaoMock.getLabels()).thenReturn(mockList);
        assertEquals(mockList, labelService.getLabels());
    }

    @Test
    void getByIdTestSuccess() {
        when(labelDaoMock.getLabelById(1)).thenReturn(mockList.get(0));
        assertEquals(mockLabel, labelService.getLabel(1));
    }



    @Test
    void test_saveLabelSuccess() {
        when(labelDaoMock.saveLabel(mockLabel)).thenReturn(mockLabel);
        assertEquals(mockLabel, labelService.saveLabel(mockLabel));
        verify(labelDaoMock, times(1)).saveLabel(mockLabel);
    }


    @Test
    void test_updateExistingLabelSuccess() {
        labelService.update(mockLabel);
        verify(labelDaoMock, times(1)).update(mockLabel);
    }

    @Test
    void deleteByIdSuccess() {
        labelService.deleteById(1);
        verify(labelDaoMock, times(1)).deleteById(1);
    }

    // negative scenario
    @Test
    void test_getLabelsNeg() {
        when(labelDaoMock.getLabels()).thenReturn(new ArrayList<>());
        List<Label> labels = labelService.getLabels();
        assertTrue(labels.isEmpty());
    }

    @Test
    void getByIdTestNeg() {
        int invalidId = 99;
        when(labelDaoMock.getLabelById(invalidId)).thenReturn(null);
        Label result = labelService.getLabel(invalidId);
        assertNull(result);
    }



    @Test
    void test_saveLabelNeg() {
        mockLabel = null;
        Label result = labelService.saveLabel(mockLabel);
        assertNull(result);
    }


    @Test
    void test_updateExistingLabelNeg() {
        mockLabel = null;
        Label result = labelService.update(mockLabel);
        assertNull(result);

    }

    @Test
    void deleteByIdNeg() {
        int invalidId = 99;
        labelService.deleteById(invalidId);
        verify(labelDaoMock, times(1)).deleteById(invalidId);
    }
}