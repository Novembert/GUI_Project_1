package gui.norbert_bujny.projekt1;

public class ExplosivesCar extends HeavyFreightCar {
    private PotentialExplosionPower potentialExplosionPower;

    public ExplosivesCar() {
        super();
        this.initializeCar();
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
}

