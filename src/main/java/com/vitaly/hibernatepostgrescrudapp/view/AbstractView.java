package com.vitaly.hibernatepostgrescrudapp.view;
//  13-Jan-24
// gh crazym8nd


import java.util.Scanner;

public abstract class AbstractView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner(){
        return scanner;
    }
    private String TitleMenu;

    public AbstractView(String TitleMenu){
        this.TitleMenu = TitleMenu;
    }
    public void setTitleMenu (String TitleMenu){
        this.TitleMenu = TitleMenu;
    }
    public void show(){
        boolean isExit = false;
        do {
            System.out.println(TitleMenu);
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    create();
                    break;
                case "2":
                    update();
                    break;
                case "3":
                    delete();
                    break;
                case "4":
                    read();
                    break;
                case "5":
                    isExit = true;
                    break;
                default:
                    System.out.println("Choose correct option");
                    break;
            }
        } while (!isExit);
    }

    public abstract void create();
    public  abstract void update();
    public abstract void delete();
    public abstract void read();
}
