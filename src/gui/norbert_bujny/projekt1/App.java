package gui.norbert_bujny.projekt1;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class App {
    private static App instance;

    private StationsGraph stationsMap;
    private CarsCollection carsCollection;
    private TrainsCollection trainsCollection;
    private TrainsDirector trainsDirector;
    private TrainsReportGenerator trainsReportGenerator;
    private Saves saves;
    private Menu menu;
    private Map<String, MenuCommandsReceiver> commandsReceiverMap;

    private App() {
    }

    private void initializeMenus() {
        this.commandsReceiverMap = new HashMap<>();

        this.commandsReceiverMap.put("main", new MenuCommandsReceiver(this));
        this.commandsReceiverMap.put("trains", new TrainsCommandsReceiver(this));
        this.commandsReceiverMap.put("stations", new StationsCommandsReceiver(this));
        this.commandsReceiverMap.put("connections", new ConnectionsCommandsReceiver(this));
        this.commandsReceiverMap.put("cars", new CarsCommandsReceiver(this));
        this.commandsReceiverMap.put("saves", new SavesMenuReceiver(this));

        MenuListsCreator mlc = new MenuListsCreator(this, commandsReceiverMap);
        this.menu = new Menu(mlc.createMenuLists());
    }

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    public void initialize(boolean provideDefaultConfiguration) {
        this.stationsMap = new StationsGraph();
        this.carsCollection = new CarsCollection();
        this.trainsCollection = new TrainsCollection();
        this.saves = new Saves();
        this.trainsDirector = new TrainsDirector(stationsMap, trainsCollection);
        this.trainsReportGenerator = new TrainsReportGenerator(trainsCollection);

        this.initializeMenus();

        if (provideDefaultConfiguration) {
            Initializer.initialize(this);
        }

        this.runScheduledTasks();
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

    public TrainsCollection getTrainsCollection() {
        return this.trainsCollection;
    }

    public TrainsDirector getTrainsDirector() {
        return trainsDirector;
    }

    public TrainCarsMap getTrainCarsMap() {
        return TrainCarsMap.getInstance();
    }

    public Map<String, MenuCommandsReceiver> getCommandsReceiverMap() {
        return commandsReceiverMap;
    }

    private void runScheduledTasks() {
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(10);
        executor.scheduleAtFixedRate(this.trainsReportGenerator, 5, 5, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(this.trainsDirector, 1, 1, TimeUnit.SECONDS);
    }
}
