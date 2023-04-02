package gui.norbert_bujny.projekt1;

public final class CarsMenuCreator extends GenericMenuCreator<CarsCommandsReceiver> {
    CarsMenuCreator(CarsCommandsReceiver receiver) {
        super(receiver);
    }


    public MenuList createMenuList() {
        MenuList mainMenu = new MenuList("Wagony");

        mainMenu.menuItems.add(new MenuItem(1, "Menu główne", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(2, "Lista wagonów", new Command() {
            @Override
            public void execute() {
                receiver.printCarsList();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(3, "Dodaj wagon", new Command() {
            @Override
            public void execute() {
                receiver.initializeCarsCreator();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(4, "Kopiuj wagon", new Command() {
            @Override
            public void execute() {
                receiver.printCarsList();
                receiver.initializeCarCopier();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(5, "Usuń wagon", new Command() {
            @Override
            public void execute() {
                receiver.initializeCarDelete();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(6, "Przypisz do pociągu", new Command() {
            @Override
            public void execute() {
//                TODO
//                receiver.switchSubmenu("trains");
            }
        }));


        return mainMenu;
    }
}
