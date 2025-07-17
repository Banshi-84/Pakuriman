public class DoubleSpeedStrategy implements Strategy {
    @Override
    public void apply(Pacuriman pacuriman) {
        pacuriman.setSpeed(pacuriman.getSpeed() * 2);
    }

    @Override
    public String getName() {
        return "Speed x2";
    }
}
