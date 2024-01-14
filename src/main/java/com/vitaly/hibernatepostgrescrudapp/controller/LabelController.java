package com.vitaly.hibernatepostgrescrudapp.controller;
//  11-Jan-24
// gh crazym8nd


import com.vitaly.hibernatepostgrescrudapp.model.Label;
import com.vitaly.hibernatepostgrescrudapp.service.LabelService;

import java.util.List;

public class LabelController {
    private final LabelService labelService = new LabelService();

    public Label createlabel(Label label) {
        return labelService.saveLabel(label);
    }
    public List<Label> getLabels() {
        return labelService.getLabels();
    }
    public Label getLabel(Integer labelId) {
        return labelService.getLabel(labelId);
    }
    public Label updateLabel(Label label) {
        return labelService.update(label);
    }
    public void deleteById(Integer integer) {
        labelService.deleteById(integer);
    }
}
