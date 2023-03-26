package gui.norbert_bujny.projekt1;

import java.util.Objects;

public class ExplosivesCar extends HeavyFreightCar {
    private PotentialExplosionPower potentialExplosionPower;

    public ExplosivesCar() {
        super(CarTypes.EXPLOSIVES_CAR);
        this.initializeCar();
    }

    public ExplosivesCar(ExplosivesCar otherCar) {
        super(otherCar);
        if (otherCar != null) {
            this.potentialExplosionPower = otherCar.potentialExplosionPower;
        }
    }

    private void initializeCar() {
        MenuCompatibleEnumWrapper<PotentialExplosionPower> enumWrapper = new MenuCompatibleEnumWrapper<>(PotentialExplosionPower.values());

        this.potentialExplosionPower = (PotentialExplosionPower) Utilities.handleUserRequiredEnumInput("Potencjalna siła wybuchu: ", enumWrapper).getChosenOption();
    }

    @Override
    public String toString() {
        return super.toString()
                + ",\nPotencjalna siła wybuchu: " + this.potentialExplosionPower;
    }

    @Override
    public BaseCar clone() {
        return new ExplosivesCar(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExplosivesCar that = (ExplosivesCar) o;
        return potentialExplosionPower == that.potentialExplosionPower;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), potentialExplosionPower);
    }
}

