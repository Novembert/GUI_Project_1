package gui.norbert_bujny.projekt1;

public final class TrainsMenuCreator extends GenericMenuCreator<TrainsCommandsReceiver> {
    TrainsMenuCreator(TrainsCommandsReceiver receiver) {
        super(receiver);
    }


    public MenuList createMenuList() {
        MenuList mainMenu = new MenuList("Pociągi");

        mainMenu.menuItems.add( new MenuItem(1, "Menu glowne", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("main");
            }
        }));

        mainMenu.menuItems.add( new MenuItem(2, "Dodaj pociag", new Command() {
            @Override
            public void execute() {
//                System.out.println();
            }
        }));

        mainMenu.menuItems.add( new MenuItem(3, "Przypisz wagon", new Command() {
            @Override
            public void execute() {
//                receiver.switchSubmenu("trains");
            }
        }));

        mainMenu.menuItems.add( new MenuItem(4, "Usun pociag", new Command() {
            @Override
            public void execute() {
//                receiver.switchSubmenu("trains");
            }
        }));

        return mainMenu;
    }
}
