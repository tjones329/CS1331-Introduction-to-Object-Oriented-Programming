import java.util.Random;
public class Hello {
    private static final String[] GREETINGS = {"Hello!", "Hi!", "W'sup!"};
    private String greeting;

    private Hello() {
        Random rand = new Random();
        int greetingsIndex = rand.nextInt(GREETINGS.length);
        greeting = GREETINGS[greetingsIndex];
    }
    public Hello(int anIndex) {
        int greetingsIndex = anIndex % GREETINGS.length;
        greeting = GREETINGS[greetingsIndex];
    }
    public String getGreeting() {return greeting;}
    public static void main(String[] args) {
        Hello h = new Hello();
        System.out.println(h.getGreeting());
    }
}
