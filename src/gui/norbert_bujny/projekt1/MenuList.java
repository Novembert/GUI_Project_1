package gui.norbert_bujny.projekt1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuList {
    public List<MenuItem> menuItems = new ArrayList<>();
    private String menuName;

    public MenuList(String menuName) {
        this.menuName = menuName;
    }

    public void runMenuList() {
        this.printMenuName();
        this.drawSubmenu();
        this.handleUserInput();
    }

    private void printMenuName(){
        System.out.println("\n*** " + this.menuName + " ***");
    }

    private void drawSubmenu() {
        for (MenuItem menuOption : menuItems) {
            System.out.println(menuOption);
        }
    }

    private void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            Integer choice = scanner.nextInt();
            menuItems.get(choice - 1).executeAction();
        } catch (Exception e) {
            System.out.println("Nie ma takiej opcji w menu.");
            this.runMenuList();
        }
    }
}
