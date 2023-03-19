package gui.norbert_bujny.projekt1;

import java.util.List;

public final class App {
    private static App instance;

    private StationsGraph stationsMap;
    private AppConfig appConfig;
    private Menu menu;

    private App() {
        this.appConfig = new AppConfig();
    }

    private void initializeMenus() {
        MenuListsCreator mlc = new MenuListsCreator(this);
        this.menu = new Menu(mlc.createMenuLists());
    }

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    public void setup() {
        this.appConfig.readConfig();

        ModuleConfig mapConfig = this.appConfig.findModuleConfig("map");

        if (mapConfig.getConfigRows().size() > 0) this.stationsMap = new StationsGraph(mapConfig);
    }

    public void initialize() {
        if (this.stationsMap == null) this.stationsMap = new StationsGraph();

        this.initializeMenus();
    }

    public void run() {
        this.menu.run();
    }

    public Menu getMenu() {
        return  this.menu;
    }

    public StationsGraph getStationsMap () {
        return this.stationsMap;
    }
}
