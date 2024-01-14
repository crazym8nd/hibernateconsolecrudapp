package com.vitaly.hibernatepostgrescrudapp.view;
//  14-Jan-24
// gh crazym8nd


import com.vitaly.hibernatepostgrescrudapp.controller.WriterController;
import com.vitaly.hibernatepostgrescrudapp.model.Post;
import com.vitaly.hibernatepostgrescrudapp.model.Status;
import com.vitaly.hibernatepostgrescrudapp.model.Writer;


import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class WriterView extends AbstractView{


    public WriterView() {
        super("Writer menu\n" +
                "1 - Create writer\n" +
                "2 - Update writer\n" +
                "3 - Delete writer\n" +
                "4 - Read writers\n" +
                "5 - Exit writer menu\n" +
                "Enter your choice: ");
    }
    private final WriterController writerController = new WriterController();
    private final Scanner scanner = getScanner();

    private static final String PRINT_WRITER_LIST = "List of writers:\n";
    private static final String CREATE_WRITER_MSG = "Creating writer.\n" + "Enter first name: ";
    private static final String EDIT_WRITER_MSG = "Enter ID of writer to edit:";
    private static final String DELETE_WRITER_MSG = "Enter ID of writer to delete: ";

    @Override
    public void create() {
        Writer createdWriter = new Writer();
        System.out.println(CREATE_WRITER_MSG);
        String firstName = scanner.nextLine();
        System.out.println("Enter second name: ");
        String lastName = scanner.nextLine();
        createdWriter.setFirstName(firstName);
        createdWriter.setLastName(lastName);
        createdWriter.setStatus(Status.ACTIVE);

        try{ writerController.createWriter(createdWriter);
            System.out.println("Writer created");
        } catch(Exception e){
            System.out.println("Error while creating writer");
        }
    }

    @Override
    public void update() {
        read();
        System.out.println(EDIT_WRITER_MSG);
        Integer id = Integer.parseInt(scanner.nextLine());

        try{
            Writer writer = writerController.getWriter(id);
            System.out.println("Enter new name: ");
            String firstName = scanner.nextLine();
            System.out.println("Enter new second name: ");
            String lastName = scanner.nextLine();
            writer.setFirstName(firstName);
            writer.setLastName(lastName);

            writerController.updateWriter(writer);
            System.out.println("Writer updated");
        } catch (Exception e){
            System.out.println("Error while updating writer");
        }
    }

    @Override
    public void delete() {
        read();
        System.out.println(DELETE_WRITER_MSG);
        Integer id = Integer.parseInt(scanner.nextLine());
        try {
            writerController.deleteById(id);
            System.out.println("Writer deleted");
        } catch (Exception e){
            System.out.println("Error while deleting writer");
        }
    }

    @Override
    public void read() {
        List<Writer> writers = null;
        try {
            writers = writerController.getWriters();
        }
        catch (Exception e){
            System.out.println("Error loading writers");
        }
        System.out.println(PRINT_WRITER_LIST);
        if(writers != null){
            writers.sort(Comparator.comparing(Writer::getId));
            writers.forEach(System.out::println);
        }

    }
}
