package gui.norbert_bujny.projekt1;

public class BuffetCar extends BaseCar {
    private CuisineType cuisineType;
    private Boolean servesHotDrinks;
    private Boolean hasSnackCart;

    public BuffetCar() {
        super(true);
        this.initializeCar();
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<CuisineType> enumWrapper = new MenuCompatibleEnumWrapper<>(CuisineType.values());

        this.cuisineType = (CuisineType) Utilities.handleUserRequiredEnumInput("Rodzaj kuchni: ", enumWrapper).getChosenOption();
        this.servesHotDrinks = Utilities.handleUserRequiredBooleanInput("Czy serwuje gorące napoje?");
        this.hasSnackCart = Utilities.handleUserRequiredBooleanInput("Czy posiada wózek gastronomiczny?");
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nKuchnia: " + this.cuisineType
                + ",\nSerwuje gorące napoje: " + Utilities.getPolishTranslationForBooleanValues(this.servesHotDrinks)
                + ",\nPosiada wózek gastronomiczny: " + Utilities.getPolishTranslationForBooleanValues(this.hasSnackCart);
    }
}
