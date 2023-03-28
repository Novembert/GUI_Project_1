package gui.norbert_bujny.projekt1;

import java.util.List;
import java.util.Map;

public final class App {
    private static App instance;

    private StationsGraph stationsMap;
    private CarsCollection carsCollection;
    private Saves saves;
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

    public void initialize() {
        this.stationsMap = new StationsGraph();
        this.carsCollection = new CarsCollection();
        this.saves = new Saves();

        this.initializeMenus();
    }

    public void run() {
        this.menu.run();
    }

    public Menu getMenu() {
        return this.menu;
    }

    public StationsGraph getStationsMap() {
        return this.stationsMap;
    }

    public Saves getSaves() {
        return this.saves;
    }

    public CarsCollection getCarsCollection() {
        return this.carsCollection;
    }
}
