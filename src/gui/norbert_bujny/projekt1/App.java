package gui.norbert_bujny.projekt1;

public final class App {
    private static App instance;
    private Menu menu;

    private App() {
        this.initlizeMenus();
    }

    private void initlizeMenus() {
        MenuListsCreator mlc = new MenuListsCreator(this);
        this.menu = new Menu(mlc.createMenuLists());
    }

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    public void run() {
        this.menu.run();
    }

    public Menu getMenu() {
        return  this.menu;
    }
}
