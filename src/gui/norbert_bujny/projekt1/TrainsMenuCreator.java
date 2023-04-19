package gui.norbert_bujny.projekt1;

public final class TrainsMenuCreator extends GenericMenuCreator<TrainsCommandsReceiver> {
    TrainsMenuCreator(TrainsCommandsReceiver receiver) {
        super(receiver);
    }


    public MenuList createMenuList() {
        MenuList mainMenu = new MenuList("Pociągi");

        mainMenu.menuItems.add(new MenuItem(1, "Menu główne", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(2, "Lista pociągów", new Command() {
            @Override
            public void execute() {
                receiver.printTrainsList();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(3, "Raport dot. pociągu", new Command() {
            @Override
            public void execute() {
                receiver.initialisePrintTrainReport();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(4, "Uruchom pociąg", new Command() {
            @Override
            public void execute() {
                receiver.initialiseRunTrain();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(5, "Uruchom wszystkie pociągi", new Command() {
            @Override
            public void execute() {
                receiver.runAllTrains();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(6, "Dodaj pociąg", new Command() {
            @Override
            public void execute() {
                receiver.initialiseAddTrain();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(7, "Usuń pociąg", new Command() {
            @Override
            public void execute() {
                receiver.initialiseDeleteTrain();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(8, "Przyczep wagon", new Command() {
            @Override
            public void execute() {
                receiver.initialiseAttachCar();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(9, "Odczep wagon", new Command() {
            @Override
            public void execute() {
                receiver.initialiseDetachCar();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(10, "Załaduj wagon", new Command() {
            @Override
            public void execute() {
                receiver.initialiseLoadCar();
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add(new MenuItem(11, "Wyładuj wagon", new Command() {
            @Override
            public void execute() {
                receiver.initialiseUnloadCar();
                receiver.switchSubmenu("main");
            }
        }));

        return mainMenu;
    }
}
