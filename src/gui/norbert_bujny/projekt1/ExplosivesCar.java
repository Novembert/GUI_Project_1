package gui.norbert_bujny.projekt1;

public class ExplosivesCar extends HeavyFreightCar {
    private PotentialExplosionPower potentialExplosionPower;

    @Override
    public String toString() {
        return super.toString()
                + ",\nPotencjalna siła wybuchu: " + this.potentialExplosionPower;
    }
}

