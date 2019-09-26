/**
* @author tjones329
* @version 0.1.0
*/
public class RogueAI extends AI {
    private int firewallProtection;
    private int alertLevel;
    private final int maxAlert;
    /**
    * @param firewallProtection an int that is the protectionlevel of the shield
    * @param alertLevel an int that is the alertLevel of the ai
    * @param maxAlert an int that is the point at which DoctorCS loses
    * @param cannonTarget the coordinates of the current target of the AI
    * @param secretHQ the coordniates of the secretHQ
    * @return
    */
    public RogueAI(int firewallProtection, int alertLevel, int maxAlert,
                   Coordinates cannonTarget, Coordinates secretHQ) {
        super(cannonTarget, secretHQ);
        this.firewallProtection = firewallProtection;
        this.alertLevel = alertLevel;
        this.maxAlert = maxAlert;

    }
    /**
    * @param firewallProtection an int that is the protectionlevel of the shield
    * @param maxAlert an int that is the point at which DoctorCS loses
    * @param cannonTarget the coordinates of the current target of the AI
    * @param secretHQ the coordniates of the secretHQ
    * @return
    */
    public RogueAI(int firewallProtection, int maxAlert,
                   Coordinates cannonTarget, Coordinates secretHQ) {
        super(cannonTarget, secretHQ);
        this.firewallProtection = firewallProtection;
        this.maxAlert = maxAlert;
        alertLevel = 0;
    }
    /**
    * @param firewallProtection an int that is the protectionlevel of the shield
    * @param cannonTarget the coordinates of the current target of the AI
    * @param secretHQ the coordniates of the secretHQ
    * @return
    */
    public RogueAI(int firewallProtection, Coordinates cannonTarget,
                   Coordinates secretHQ) {
        super(cannonTarget, secretHQ);
        this.firewallProtection = firewallProtection;
        alertLevel = 0;
        maxAlert = 10;
    }
    /**
    * the method that lowers protection of firewall and raises alert level
    */
    public void lowerFirewall() {
        firewallProtection -= 2;
        alertLevel += 1;
    }
    /**
    * @return boolean representing if cannon should switch targets
    */
    @Override
    public boolean shouldSwapCannonTarget() {
        if (firewallProtection <= 0) {
            return true;
        }
        return false;
    }
    /**
    * @return boolean representing if cannon should self destruct
    */
    @Override
    public boolean shouldSelfDestruct() {
        if (alertLevel >= maxAlert) {
            return true;
        }
        return false;
    }
    /**
    * @return String representation that says where cannon is pointed and
    * what the alertLevel and firewall protection is.
    */
    public String toString() {
        String s = "Dr. Chipotle’s guacamole cannon is currently pointed at "
                  + (this.getCannonTarget()) + ", and is at alert level "
                  + (this.alertLevel) + " with firewall protection "
                  + (this.firewallProtection) + ".";
        return s;
    }
    /**
    * @return int that is the current firewallProtection
    */
    public int getFirewallProtection() {
        return this.firewallProtection;
        //collapse
    }
    /**
    * @return int of the current alert level
    */
    public int getAlertLevel() {
        return this.firewallProtection;
        //collapse
    }
    /**
    * @return int of the maximum alert
    */
    public int getMaxAlert() {
        return this.maxAlert;
        //collapse
    }

}
