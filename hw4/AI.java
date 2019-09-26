/**
* @author tjones329
* @version 0.1.0
*/
public abstract class AI {
    private boolean destructed = false;
    private Coordinates cannonTarget;
    private Coordinates secretHQ;
    /**
    * @param cannonTarget the coordinates of the cannon's current target
    * @param secretHQ the coordinates of the secretHQ
    */
    public AI(Coordinates cannonTarget, Coordinates secretHQ) {
        this.cannonTarget = cannonTarget;
        this.secretHQ = secretHQ;
    }
    /**
    * @param newCoords the coordinates of the cannon's new target
    * @return boolean representing if cannon swap targets
    */
    public boolean swapCannonTarget(Coordinates newCoords) {
        if ((!destructed) && (newCoords != cannonTarget)) {
            if (shouldSwapCannonTarget()) {
                cannonTarget = newCoords;
                return true;
            } else if (shouldSelfDestruct()) {
                selfDestruct();
                return false;
            }
        }
        return false;
    }
    /**
    * @return boolean representing if cannon should switch targets
    */
    public abstract boolean shouldSwapCannonTarget();
    /**
    * a method that when called destroys the cannon
    * by setting destructed variable to true
    */
    public void selfDestruct() {
        destructed = true;
        //collapse
    }
    /**
    * @return boolean representing if a cannon should self destruct or not
    */
    public abstract boolean shouldSelfDestruct();
    /**
    * @return String representation of where the cannon's target currently is
    */
    public String toString() {
        String s = "Dr. Chipoltle's guacamole cannon is currently pointed at ";
        s = s + cannonTarget.toString() + ".";
        return s;
    }
    /**
    * @return boolean that checks if a cannon is destroyed or not.
    */
    public boolean getDestructed() {
        return this.destructed;
    }
    /**
    * @return Coordinates to the current cannon target
    */
    public Coordinates getCannonTarget() {
        return this.cannonTarget;
    }
    /**
    * @return coordinates to the AI secretHQ
    */
    public Coordinates getSecretHQ() {
        return this.secretHQ;
    }
}
