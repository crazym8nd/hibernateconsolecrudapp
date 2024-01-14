package com.vitaly.hibernatepostgrescrudapp.view;
//  11-Jan-24
// gh crazym8nd


import java.util.Scanner;

public class MainView {
    private final AbstractView labelView = new LabelView();
    private final AbstractView postView = new PostView();
    private final AbstractView writerView = new WriterView();
    private final Scanner scanner = new Scanner(System.in);


    private static final String MENU = "Main menu. Choose option:\n" +
            "1. Work with labels\n" +
            "2. Work with posts\n" +
            "3. Work with writers\n" +
            "4. Exit\n";
    public void start() {
        try(scanner) {

            do {
                System.out.println(MENU);
                String selectedOption = scanner.nextLine();
                switch (selectedOption) {
                    case "1":
                        labelView.show();
                        break;

                    case "2":
                        postView.show();
                        break;

                    case "3": writerView.show();
                        break;

                    case "4":
                        System.exit(1);
                        break;

                    default:
                        System.out.println("Choose correct option");
                        break;
                }
            } while (true);
        }
    }
}
