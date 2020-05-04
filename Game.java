import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private int holes;
    private int colours;
    private ArrayList<Integer> solution;

    public static final String inputIndicator = ">";
    public static final String feedbackIndicator = "-";

    public Game(int holes, int colours) {
        this.holes = holes;
        this.colours = colours;
        this.solution = GameUtils.mkSolution(holes, colours);
        go();
    }

    private void go() {
        int guesses = 0;
        while (true) {
            ArrayList<Integer> currentGuess = getGuess();
            guesses++;
            if (currentGuess.equals(solution)) {
                break;
            }
            ArrayList<GameUtils.Feedback> currentFeedback = GameUtils.getFeedback(this.solution, currentGuess);
            printFeedback(currentFeedback);
        }
        System.out.println("Congrats! " + solution + " was the solution.");
        System.out.println("It took you " + guesses + " moves.");

        System.out.println("\nThanks for playing.");
    }

    private ArrayList<Integer> getGuess() {
        Scanner input = new Scanner(System.in);

        String rawInput;
        String[] splitInput;
        ArrayList<Integer> output;
        boolean valid;
        while (true) {
            valid = true;
            output = new ArrayList<>();
            System.out.print("\n" + inputIndicator);
            rawInput = input.nextLine().trim();
            try {
                splitInput = rawInput.split(" ");
                for (String str : splitInput) {
                    output.add(Integer.parseInt(str));
                }
            } catch (Exception e) {
                valid = false;
            }


            if (output.size() != this.holes) {
                valid = false;
            }

            for (Integer hole : output) {
                if (hole > (this.colours-1)) {
                    valid = false;
                }
            }

            if (!valid) {
                System.err.println("Invalid input. Enter each number (0-" + colours + ") followed by a space.");
                continue;
            }
            return output;
        }
    }

    private void printFeedback(ArrayList<GameUtils.Feedback> feedback) {
        System.out.print(feedbackIndicator);
        for (GameUtils.Feedback f : feedback) {
            System.out.print(f + " ");
        }
        System.out.println();
    }
}
