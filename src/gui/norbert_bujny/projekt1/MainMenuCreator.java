package gui.norbert_bujny.projekt1;

import java.util.HashMap;
import java.util.Map;

public final class MainMenuCreator {
    private MenuCommandsReceiver receiver;

    MainMenuCreator(MenuCommandsReceiver receiver) {
    this.receiver = receiver;
    }


    public MenuList createMenuList() {
        MenuList mainMenu = new MenuList();

        mainMenu.menuItems.add( new MenuItem(1, "Lokomotywy", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("trains");
            }
        }));

        mainMenu.menuItems.add( new MenuItem(2, "Wagony", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("carriages");
            }
        }));

        mainMenu.menuItems.add( new MenuItem(3, "Stacje", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("stations");
            }
        }));

        mainMenu.menuItems.add( new MenuItem(4, "Polaczenia", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("connections");
            }
        }));

        return mainMenu;
    }
}
