package gui.norbert_bujny.projekt1;

public final class MainMenuCreator extends GenericMenuCreator<MenuCommandsReceiver> {
    MainMenuCreator(MenuCommandsReceiver receiver) {
        super(receiver);
    }

    public MenuList createMenuList() {
        MenuList mainMenu = new MenuList("Menu główne");

        mainMenu.menuItems.add(new MenuItem(1, "Pociągi", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("trains");
            }
        }));

        mainMenu.menuItems.add( new MenuItem(2, "Wagony", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("cars");
            }
        }));

        mainMenu.menuItems.add( new MenuItem(3, "Stacje", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("stations");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(4, "Połączenia", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("connections");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(5, "Wczytaj/Zapisz", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("saves");
            }
        }));

        return mainMenu;
    }
}
