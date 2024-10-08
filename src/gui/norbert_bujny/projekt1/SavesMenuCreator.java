package gui.norbert_bujny.projekt1;

public class SavesMenuCreator extends GenericMenuCreator<SavesMenuReceiver> {
    SavesMenuCreator(SavesMenuReceiver receiver) {
        super(receiver);
    }

    public MenuList createMenuList() {
        MenuList savesMenu = new MenuList("Wczytaj/Zapisz");

        savesMenu.menuItems.add(new MenuItem(1, "Menu główne", new Command() {
            @Override
            public void execute() {
                receiver.switchSubmenu("main");
            }
        }));

        savesMenu.menuItems.add(new MenuItem(2, "Nowy zapis", new Command() {
            @Override
            public void execute() {
                receiver.initSaveState();
                receiver.switchSubmenu("main");
            }
        }));

        savesMenu.menuItems.add(new MenuItem(3, "Nadpisz zapis", new Command() {
            @Override
            public void execute() {
                receiver.initOverrideState();
                receiver.switchSubmenu("main");
            }
        }));

        savesMenu.menuItems.add(new MenuItem(4, "Wczytaj zapis", new Command() {
            @Override
            public void execute() {
                receiver.initReadState();
                receiver.switchSubmenu("main");
            }
        }));

        return savesMenu;
    }
}
