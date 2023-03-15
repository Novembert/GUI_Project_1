package gui.norbert_bujny.projekt1;

public class StationsCommandsReceiver extends MenuCommandsReceiver {
    public StationsCommandsReceiver(App appReference) {
        super(appReference);
    }

    public void initializeStationBuilder(String key) {
        this.app.getMenu().switchSubmenu(key);
    }
}
