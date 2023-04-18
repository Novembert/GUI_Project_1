package gui.norbert_bujny.projekt1;

public class Presentation {
    public static void main(String[] args) {
        App appInstance = App.getInstance();
        appInstance.initialize(true);
        appInstance.run();
    }
}
