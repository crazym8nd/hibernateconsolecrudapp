package com.vitaly.hibernatepostgrescrudapp.service;
//  11-Jan-24
// gh crazym8nd


import com.vitaly.hibernatepostgrescrudapp.dao.LabelDao;
import com.vitaly.hibernatepostgrescrudapp.model.Label;

import java.util.List;

public class LabelService {
    private final LabelDao labelDao;

    public LabelService(LabelDao labelDao) {
        this.labelDao = labelDao;
    }

    public LabelService() {
        this.labelDao = new LabelDao();
    }

    public List<Label> getLabels() {
        return labelDao.getLabels();
    }
    public Label getLabel(Integer labelId) {
        return labelDao.getLabelById(labelId);
    }
    public Label saveLabel(Label label) {
        return labelDao.saveLabel(label);
    }
    public Label update (Label label) {
        return labelDao.update(label);
    }
    public void deleteById(Integer integer) {
        labelDao.deleteById(integer);
    }
}
