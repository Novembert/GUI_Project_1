package gui.norbert_bujny.projekt1;

public class MenuCommandsReceiver {
    protected App app;

    public MenuCommandsReceiver(App appReference) {
        this.app = appReference;
    }

    public void switchSubmenu(String key) {
        this.app.getMenu().switchSubmenu(key);
    }
}
