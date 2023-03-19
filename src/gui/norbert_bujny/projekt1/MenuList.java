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
        System.out.println("*** " + this.menuName + " ***");
    }

    private void drawSubmenu() {
        for (MenuItem menuOption : menuItems) {
            System.out.println(menuOption);
        }
    }

//    TODO maybe switch to utility function?
    private void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        Integer choice = scanner.nextInt();

        if (choice > 0 && choice <= menuItems.size()) {
            menuItems.get(choice - 1).executeAction();
        } else {
            this.runMenuList();
        }
    }
}
