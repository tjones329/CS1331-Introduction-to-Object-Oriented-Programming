
import java.util.Random;
/**
* @author tjones329
* @version 0.1.0
*/
public class RandomAI extends AI {
    private static final Random randomizer = new Random();
    /**
    * @param cannonTarget coordinates of the cannon's current target
    * @param secretHQ coordinates of the secretHQ
    */
    public RandomAI(Coordinates cannonTarget, Coordinates secretHQ) {
        super(cannonTarget, secretHQ);
        //collapse
    }
    /**
    * @return boolean representing if cannon should swap targets
    */
    @Override
    public boolean shouldSwapCannonTarget() {
        if (randomizer.nextBoolean()) {
            return true;
        }
        return false;
    }
    /**
    * @return boolean that determines if object should self destruct
    */
    @Override
    public boolean shouldSelfDestruct() {
        if (randomizer.nextBoolean()) {
            return true;
        }
        return false;
    }
}
