package gui.norbert_bujny.projekt1;

import java.util.HashMap;
import java.util.Map;

public final class MenuListsCreator {
    MainMenuCreator mmCreator;
    TrainsMenuCreator tmCreator;

    public MenuListsCreator(App appReference) {
        this.mmCreator = new MainMenuCreator(new MenuCommandsReceiver(appReference));
        this.tmCreator = new TrainsMenuCreator(new TrainsCommandsReceiver(appReference));
    }

    public Map<String, MenuList> createMenuLists() {
        Map<String, MenuList> menuListsMap = new HashMap<>();

        menuListsMap.put("main", mmCreator.createMenuList());
        menuListsMap.put("trains", tmCreator.createMenuList());

        return menuListsMap;
    }
}
