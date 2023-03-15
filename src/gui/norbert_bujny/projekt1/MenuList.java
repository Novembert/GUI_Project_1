package gui.norbert_bujny.projekt1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuList {
    public List<MenuItem> menuItems = new ArrayList<>();

    public void runMenuList() {
        this.drawSubmenu();
        this.handleUserInput();
    }
    private void drawSubmenu() {
        for (MenuItem menuOption : menuItems) {
            System.out.println(menuOption);
        }
    }

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
