public class Main {

    private final static int holes = 4;
    private final static int colours = 4;

    public static void main(String[] args) {
        System.out.println("Welcome to Mastermind!\n");
        System.out.println("There are " + holes + " holes and colours 0-" + (colours-1) + ".");
        System.out.println(GameUtils.Feedback.POS_COL + " means you've guessed a correct colour in the correct place.");
        System.out.println(GameUtils.Feedback.COL + " means you've guess a correct colour in the wrong place.");
        System.out.println("If you see '" + Game.inputIndicator + "', you should enter your guess.");
        System.out.println("If you see '" + Game.feedbackIndicator + "', your feedback will follow. Your feedback may be blank.");
        Game newGame = new Game(holes, colours);
    }
}
