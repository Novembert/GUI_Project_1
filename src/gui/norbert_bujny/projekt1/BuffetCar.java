package gui.norbert_bujny.projekt1;

import java.util.Objects;

public class BuffetCar extends BaseCar {
    private CuisineType cuisineType;
    private Boolean servesHotDrinks;
    private Boolean hasSnackCart;

    public BuffetCar(BuffetCar otherCar) {
        super(otherCar);
        if (otherCar != null) {
            this.cuisineType = otherCar.cuisineType;
            this.servesHotDrinks = otherCar.servesHotDrinks;
            this.hasSnackCart = otherCar.hasSnackCart;
        }
    }

    public BuffetCar() {
        super(true, CarTypes.BUFFET_CAR);
        this.initializeCar();
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<CuisineType> enumWrapper = new MenuCompatibleEnumWrapper<>(CuisineType.values());

        this.cuisineType = (CuisineType) Utilities.handleUserRequiredEnumInput("Rodzaj kuchni: ", enumWrapper).getChosenOption();
        this.servesHotDrinks = Utilities.handleUserRequiredBooleanInput("Czy serwuje gorące napoje?");
        this.hasSnackCart = Utilities.handleUserRequiredBooleanInput("Czy posiada wózek gastronomiczny?");
    }

    @Override
    public BaseCar clone() {
        return new BuffetCar(this);
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nKuchnia: " + this.cuisineType
                + ",\nSerwuje gorące napoje: " + Utilities.getPolishTranslationForBooleanValues(this.servesHotDrinks)
                + ",\nPosiada wózek gastronomiczny: " + Utilities.getPolishTranslationForBooleanValues(this.hasSnackCart);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BuffetCar buffetCar = (BuffetCar) o;
        return cuisineType == buffetCar.cuisineType && Objects.equals(servesHotDrinks, buffetCar.servesHotDrinks) && Objects.equals(hasSnackCart, buffetCar.hasSnackCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cuisineType, servesHotDrinks, hasSnackCart);
    }
}
