package strategy;
import model.Pacuriman;

public interface Strategy {
    void apply(Pacuriman p);
    String getName();
}
