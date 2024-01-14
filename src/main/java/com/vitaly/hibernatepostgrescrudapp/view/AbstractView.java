package com.vitaly.hibernatepostgrescrudapp.view;
//  13-Jan-24
// gh crazym8nd


import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Setter
public abstract class AbstractView {
    @Getter
    private static final Scanner scanner = new Scanner(System.in);

    private String TitleMenu;

    protected AbstractView(String TitleMenu){
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
