package strategy;
import model.Pacuriman;

public class InvertControlStrategy implements Strategy {
    @Override
    public void apply(Pacuriman pacuriman) {
        pacuriman.setInvertedControlled(true);
    }

    @Override
    public String getName() {
        return "Inverted Controls";
    }
}
