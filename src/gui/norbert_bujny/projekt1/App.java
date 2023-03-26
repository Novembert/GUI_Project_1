package gui.norbert_bujny.projekt1;

import java.util.List;
import java.util.Map;

public final class App {
    private static App instance;

    private StationsGraph stationsMap;
    private CarsCollection carsCollection;
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
        ModuleConfig carsConfig = this.appConfig.findModuleConfig("cars");

        if (mapConfig != null && mapConfig.getConfigRows().size() > 0) this.stationsMap = new StationsGraph(mapConfig);
        if (carsConfig != null && carsConfig.getConfigRows().size() > 0)
            this.carsCollection = new CarsCollection(carsConfig);
    }

    public void initialize() {
        if (this.stationsMap == null) this.stationsMap = new StationsGraph();
        if (this.carsCollection == null) this.carsCollection = new CarsCollection();

        this.initializeMenus();
    }

    public void run() {
        this.menu.run();
    }

    public Menu getMenu() {
        return  this.menu;
    }

    public StationsGraph getStationsMap() {
        return this.stationsMap;
    }

    public CarsCollection getCarsCollection() {
        return this.carsCollection;
    }
}
