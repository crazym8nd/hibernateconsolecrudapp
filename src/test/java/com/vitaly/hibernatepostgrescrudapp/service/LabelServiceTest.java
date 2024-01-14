package com.vitaly.hibernatepostgrescrudapp.service;

import com.vitaly.hibernatepostgrescrudapp.dao.LabelDao;
import com.vitaly.hibernatepostgrescrudapp.model.Label;
import com.vitaly.hibernatepostgrescrudapp.model.Status;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//  14-Jan-24
// gh crazym8nd

class LabelServiceTest {

    @Test
    public void test_getLabels() {
        LabelDao labelDao = mock(LabelDao.class);
        List<Label> expectedLabels = new ArrayList<>();
        expectedLabels.add(new Label(1, "Label 1", Status.ACTIVE));
        expectedLabels.add(new Label(2, "Label 2", Status.ACTIVE));
        when(labelDao.getLabels()).thenReturn(expectedLabels);
        LabelService labelService = new LabelService(labelDao);

        List<Label> actualLabels = labelService.getLabels();

        assertEquals(expectedLabels, actualLabels);
    }

    @Test
    public void test_getLabelById() {

        LabelDao labelDao = mock(LabelDao.class);
        LabelService labelService = new LabelService(labelDao);
        Integer labelId = 1;
        Label expectedLabel = new Label(labelId, "Test Label", Status.ACTIVE);
        when(labelDao.getLabelById(labelId)).thenReturn(expectedLabel);

        Label actualLabel = labelService.getLabel(labelId);


        assertEquals(expectedLabel, actualLabel);
        Mockito.verify(labelDao, times(1)).getLabelById(labelId);
    }



    @Test
    public void test_saveLabel() {
    }

    @Test
    public void test_updateExistingLabel() {

    }

    @Test
    void deleteById() {
    }
}