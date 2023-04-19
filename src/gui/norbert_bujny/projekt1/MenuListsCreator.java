package gui.norbert_bujny.projekt1;

import java.util.HashMap;
import java.util.Map;

public final class MenuListsCreator {
    MainMenuCreator mmCreator;
    TrainsMenuCreator tmCreator;
    StationsMenuCreator smCreator;
    ConnectionsMenuCreator cmCreator;
    CarsMenuCreator carsMenuCreator;
    SavesMenuCreator savesMenuCreator;

    public MenuListsCreator(App appReference, Map<String, MenuCommandsReceiver> menuCommandsReceiverMap) {
        this.mmCreator = new MainMenuCreator(menuCommandsReceiverMap.get("main"));
        this.tmCreator = new TrainsMenuCreator((TrainsCommandsReceiver) menuCommandsReceiverMap.get("trains"));
        this.smCreator = new StationsMenuCreator((StationsCommandsReceiver) menuCommandsReceiverMap.get("stations"));
        this.cmCreator = new ConnectionsMenuCreator((ConnectionsCommandsReceiver) menuCommandsReceiverMap.get("connections"));
        this.carsMenuCreator = new CarsMenuCreator((CarsCommandsReceiver) menuCommandsReceiverMap.get("cars"));
        this.savesMenuCreator = new SavesMenuCreator((SavesMenuReceiver) menuCommandsReceiverMap.get("saves"));
    }

    public Map<String, MenuList> createMenuLists() {
        Map<String, MenuList> menuListsMap = new HashMap<>();

        menuListsMap.put("main", mmCreator.createMenuList());
        menuListsMap.put("trains", tmCreator.createMenuList());
        menuListsMap.put("stations", smCreator.createMenuList());
        menuListsMap.put("connections", cmCreator.createMenuList());
        menuListsMap.put("cars", carsMenuCreator.createMenuList());
        menuListsMap.put("saves", savesMenuCreator.createMenuList());

        return menuListsMap;
    }
}
