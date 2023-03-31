package gui.norbert_bujny.projekt1;

public final class TrainsMenuCreator extends GenericMenuCreator<TrainsCommandsReceiver> {
    TrainsMenuCreator(TrainsCommandsReceiver receiver) {
        super(receiver);
    }


    public MenuList createMenuList() {
        MenuList mainMenu = new MenuList("Pociągi");

        mainMenu.menuItems.add(new MenuItem(1, "Menu glowne", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(2, "List pociągów", new Command() {
            @Override
            public void execute() {
                receiver.printTrainsList();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(3, "Dodaj pociag", new Command() {
            @Override
            public void execute() {
                receiver.addTrain();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(4, "Przypisz wagon", new Command() {
            @Override
            public void execute() {
                receiver.attachCar();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(5, "Usuń pociąg", new Command() {
            @Override
            public void execute() {
                receiver.deleteTrain();
                receiver.switchSubmenu("main");
            }
        }));

        return mainMenu;
    }
}
