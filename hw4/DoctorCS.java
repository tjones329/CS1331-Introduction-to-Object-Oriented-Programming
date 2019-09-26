/**
* @author tjones329
* @version 0.1.0
*/
public class DoctorCS {
    private AI ai;
    private final String secretIdentity;
    private int jlaid;
    private boolean safe;
    /**
    * @param ai an AI object
    * @param secretIdentity A string that is DoctorCS's secret identity
    * @param jlaid an id number thingy
    */
    public DoctorCS(AI ai, String secretIdentity, int jlaid) {
        this.ai = ai;
        this.secretIdentity = secretIdentity;
        this.jlaid = jlaid;
        safe = false;
    }
    /**
    * execute the save the day which will determine who won the battle
    */
    public void saveTheDay() {
        if (ai instanceof RogueAI) {
            while (((RogueAI) ai).getFirewallProtection() > 0) {
                ((RogueAI) ai).lowerFirewall();
            }
            safe = ai.swapCannonTarget(ai.getSecretHQ());
        }
        if (ai instanceof RandomAI) {
            safe = ai.swapCannonTarget(ai.getSecretHQ());
        }
    }
    /**
    * @return String that sates who won the battle
    */
    public String getStatus() {
        if (safe) {
            return "Doctor CS has saved the day!";
        }
        if (!safe) {
            return "Dr. Chipotle has succeeded in his plan...";
        }
        return "Georgia Tech is still in danger!";
    }
    /**
    * @return String representation of who DoctorCS is and what his jlaid is
    */
    public String toString() {
        String s = this.secretIdentity + " aka Doctor CS with "
                 + "JLAID: " + this.jlaid;
        return s;
    }
    /**
    * @param
    * @return AI that is being used
    */
    public AI getAI() {
        return this.ai;
    }
    /**
    * @param
    * @return the jlaid of DoctorCS
    */
    public int getJlaid() {
        return this.jlaid;
    }
}
