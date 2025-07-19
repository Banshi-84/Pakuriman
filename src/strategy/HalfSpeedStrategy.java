package strategy;
import model.Pacuriman;

public class HalfSpeedStrategy implements Strategy {
    @Override
    public void apply(Pacuriman pacuriman) {
        pacuriman.setSpeed(Math.max(1, pacuriman.getSpeed() / 2));
    }

    @Override
    public String getName() {
        return "Speed ÷2";
    }
}
