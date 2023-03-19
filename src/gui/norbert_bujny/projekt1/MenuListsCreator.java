package gui.norbert_bujny.projekt1;

import java.util.HashMap;
import java.util.Map;

public final class MenuListsCreator {
    MainMenuCreator mmCreator;
    TrainsMenuCreator tmCreator;
    StationsMenuCreator smCreator;
    ConnectionsMenuCreator cmCreator;

    public MenuListsCreator(App appReference) {
        this.mmCreator = new MainMenuCreator(new MenuCommandsReceiver(appReference));
        this.tmCreator = new TrainsMenuCreator(new TrainsCommandsReceiver(appReference));
        this.smCreator = new StationsMenuCreator(new StationsCommandsReceiver(appReference));
        this.cmCreator = new ConnectionsMenuCreator(new ConnectionsCommandsReceiver(appReference));
    }

    public Map<String, MenuList> createMenuLists() {
        Map<String, MenuList> menuListsMap = new HashMap<>();

        menuListsMap.put("main", mmCreator.createMenuList());
        menuListsMap.put("trains", tmCreator.createMenuList());
        menuListsMap.put("stations", smCreator.createMenuList());
        menuListsMap.put("connections", cmCreator.createMenuList());

        return menuListsMap;
    }
}
