package gui.norbert_bujny.projekt1;

public class ConnectionsMenuCreator  extends GenericMenuCreator<ConnectionsCommandsReceiver> {

    ConnectionsMenuCreator(ConnectionsCommandsReceiver receiver) {
        super(receiver);
    }

    public MenuList createMenuList() {
        MenuList menu = new MenuList("Połączenia");

        menu.menuItems.add( new MenuItem(1, "Menu główne", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("main");
            }
        }));

        menu.menuItems.add( new MenuItem(2, "Dodaj połączenie", new Command() {
            @Override
            public void execute() {
                receiver.initializeConnectionCreator();
                receiver.switchSubmenu("main");
            }
        }));

        menu.menuItems.add( new MenuItem(3, "Dodaj połączenia", new Command() {
            @Override
            public void execute() {
                receiver.initializeMultipleConnectionsCreator();
                receiver.switchSubmenu("main");
            }
        }));

        menu.menuItems.add( new MenuItem(4, "Dodaj stację jako docelową dla wielu stacji startowych", new Command() {
            @Override
            public void execute() {
                receiver.initializeConnectingToMultipleSourceStations();
                receiver.switchSubmenu("main");
            }
        }));

        menu.menuItems.add( new MenuItem(5, "Wyszukaj trasę", new Command() {
            @Override
            public void execute() {
                receiver.printPath();
                receiver.switchSubmenu("main");
            }
        }));

        return  menu;
    }
}
