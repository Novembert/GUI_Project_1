package gui.norbert_bujny.projekt1;

public final class StationsMenuCreator extends GenericMenuCreator<StationsCommandsReceiver> {
    StationsMenuCreator(StationsCommandsReceiver receiver) {
        super(receiver);
    }

    public MenuList createMenuList() {
        MenuList menu = new MenuList("Stacje");

        menu.menuItems.add(new MenuItem(1, "Menu główne", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("main");
            }
        }));

        menu.menuItems.add(new MenuItem(2, "Lista stacji", new Command() {
            @Override
            public void execute() {
                receiver.printStationsList();
                receiver.switchSubmenu("main");
            }
        }));

        menu.menuItems.add(new MenuItem(3, "Dodaj stację", new Command() {
            @Override
            public void execute() {
                receiver.initializeCreateStation();
                receiver.switchSubmenu("main");
            }
        }));

        menu.menuItems.add(new MenuItem(4, "Usun stację", new Command() {
            @Override
            public void execute() {
                receiver.printStationsList();
                receiver.initializeDeleteStation();
                receiver.switchSubmenu("main");
            }
        }));

        return menu;
    }
}