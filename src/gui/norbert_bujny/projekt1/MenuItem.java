package gui.norbert_bujny.projekt1;

public class MenuItem {
    private String text;
    private Integer key;
    private Command action;

    public MenuItem(Integer key, String text, Command action) {
        this.key = key;
        this.text = text;
        this.action = action;
    }

    @Override
    public String toString() {
        return "[" + this.key + "] " + this.text;
    }

    public void executeAction() {
        this.action.execute();
    }
}
