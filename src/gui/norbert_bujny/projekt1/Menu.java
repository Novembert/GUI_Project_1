package gui.norbert_bujny.projekt1;

import java.util.Map;

public class Menu {
    private MenuList currentMenuList;
    private Map<String, MenuList> availableMenus;

    public Menu(Map<String, MenuList> availableMenus) {
        this.availableMenus = availableMenus;
        this.currentMenuList = this.availableMenus.get("main");
    }

    public void run() {
        this.runSubmenu();
    }

    private void runSubmenu() {
        this.currentMenuList.runMenuList();
    }

    public void switchSubmenu(String key) {
        this.currentMenuList = this.availableMenus.get(key);
        this.runSubmenu();
    }
}
