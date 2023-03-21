package gui.norbert_bujny.projekt1;

public class BuffetCar {
    private CuisineType cuisineType;
    private Boolean servesHotDrinks;
    private Boolean hasSnackCart;

    @Override
    public String toString() {
        return super.toString()
                + ",\nKuchnia: " + this.cuisineType
                + ",\nSerwuje gorące napoje: " + Utilities.getPolishTranslationForBooleanValues(this.servesHotDrinks)
                + ",\nPosiada wózek gastronomiczny: " + Utilities.getPolishTranslationForBooleanValues(this.hasSnackCart);
    }
}
