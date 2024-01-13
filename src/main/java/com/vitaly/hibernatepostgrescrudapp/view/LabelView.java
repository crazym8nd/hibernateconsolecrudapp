package com.vitaly.hibernatepostgrescrudapp.view;
//  11-Jan-24
// gh crazym8nd


import com.vitaly.hibernatepostgrescrudapp.controller.LabelController;
import com.vitaly.hibernatepostgrescrudapp.model.Label;
import com.vitaly.hibernatepostgrescrudapp.model.Status;

import java.util.List;
import java.util.Scanner;

public class LabelView extends AbstractView {
    private final LabelController labelController = new LabelController();
    private final Scanner scanner = getScanner();
    private static final String PRINT_LABEL_LIST = "List of labels:\n";
    private static final String CREATE_LABEL_MSG = "Create label.\n" + "Enter label name: ";
    private static final String EDIT_LABEL_MSG = "Enter ID of label to edit:";
    private static final String DELETE_LABEL_MSG = "Enter ID of label to delete: ";

    public LabelView() {
        super("Label menu\n" +
                "1 - Create label\n" +
                "2 - Update label\n" +
                "3 - Delete label\n" +
                "4 - Read labels\n" +
                "5 - Exit label\n" +
                "Enter your choice: ");
    }


    @Override
    public void create() {
        Label createdLabel = new Label();
        System.out.println(CREATE_LABEL_MSG);
        createdLabel.setStatus(Status.ACTIVE);
        String name = scanner.nextLine();
        createdLabel.setName(name);
        try {
            labelController.createlabel(createdLabel);
            System.out.println("Label created: " + createdLabel);
        } catch (Exception e) {
            System.out.println("CANNOT CREATE LABEL " + createdLabel.getName());
        }
    }

    @Override
    public void update() {
        read();
        System.out.println(EDIT_LABEL_MSG);
        try {
            Integer id = Integer.parseInt(scanner.nextLine());
            Label labelToUpdate = labelController.getLabel(id);
            if (labelToUpdate.getStatus() != Status.ACTIVE) {
                System.out.println("No such label with ID " + id);
            } else {
                System.out.println("Enter new name: ");
                String name = scanner.nextLine();
                labelToUpdate.setName(name);
                labelController.updateLabel(labelToUpdate);
                System.out.println("Label updated: " + labelToUpdate);
            }
        } catch (Exception e) {
            e.getMessage();
            System.out.println("Error while updating label with ID");
        }
    }

    @Override
    public void delete() {
        read();
        System.out.println(DELETE_LABEL_MSG);
        try {
            Integer id = Integer.parseInt(scanner.nextLine());
            Label labelToDelete = labelController.getLabel(id);
            if (labelToDelete.getId() == -1) {
                System.out.println("No such label with ID " + id);
            } else {
                labelController.deleteById(id);
                System.out.println("Label deleted: " + labelToDelete);
            }
        } catch (Exception e) {
            e.getMessage();
            System.out.println("Error while deleting label with ID");
        }
    }

    @Override
    public void read() {
        try {
            List<Label> labels = labelController.getLabels();
            System.out.println(PRINT_LABEL_LIST);
            labels.forEach(System.out::println);
        } catch (Exception e) {
            e.getMessage();
            System.out.println("Error while reading labels");
        }
    }
}
