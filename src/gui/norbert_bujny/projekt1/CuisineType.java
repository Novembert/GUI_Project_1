package gui.norbert_bujny.projekt1;

public enum CuisineType {
    POLISH("Polska"),
    ITALIAN("Włoska"),
    AMERICAN("Amerykańska"),
    INTERNATIONAL("Międzynarodowa"),
    CHINESE("Chińska"),
    KOREAN("Koreańska"),
    FUSION("Fusion");

    private final String value;
    private CuisineType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return  this.value;
    }
}
