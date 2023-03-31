package gui.norbert_bujny.projekt1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Train implements IdRepresentedItem, Serializable {
    private String ID;
    private String name;
    private int maxCarsCount;
    private double maxWeight;
    private int maxElectricCarsCount;
    private Station homeStation;
    private Station targetStation;
    private Station currentStation;
    private List<BaseCar> cars;

    public Train(Station homeStation, Station targetStation) {
        this.homeStation = homeStation;
        this.targetStation = targetStation;
        this.currentStation = homeStation;
        this.ID = IdGenerator.resolveID(IdFieldsNamesEnum.TRAIN_ID.toString());
        this.cars = new ArrayList<>();
        this.initlializeTrain();
    }

    private void initlializeTrain() {
        this.name = Utilities.handleUserRequiredInput("Nazwa: ");
        this.maxWeight = Double.parseDouble(Utilities.handleUserRequiredInput("Maksymalna waga transportowanego ładunku: "));
        this.maxCarsCount = Integer.parseInt(Utilities.handleUserRequiredInput("Maksymalna liczba wagonów: "));
        this.maxElectricCarsCount = Integer.parseInt(Utilities.handleUserRequiredInput("Maksymalna liczba wagonów wymagających podłączenia do sieci elektrycznej: "));
    }

    public void attachCar(BaseCar car) throws TooManyCarsException, TooManyElectricCarsException {
        if (this.canAddCar(car)) this.cars.add(car);
    }

    public List<BaseCar> getCars() {
        return this.cars;
    }

    private boolean canAddCar(BaseCar car) throws TooManyCarsException, TooManyElectricCarsException {
        if (this.cars.size() >= this.maxCarsCount) throw new TooManyCarsException();
        if (car.getNeedsElectricity() && this.getElectricCarsCount() >= this.maxElectricCarsCount)
            throw new TooManyElectricCarsException();

        return true;
    }

    private int getElectricCarsCount() {
        return this.cars.stream()
                .filter(car -> car.getNeedsElectricity())
                .toList()
                .size();
    }

    @Override
    public String getID() {
        return this.ID;
    }
}
