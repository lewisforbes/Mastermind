import javax.jnlp.IntegrationService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameUtils {

    public enum Feedback {
        POS_COL("X"), COL("O");
        private String toString;
        private Feedback(String toString) {
            this.toString = toString;
        }

        @Override
        public String toString() {
            return toString;
        }
    }


    public static ArrayList<Integer> mkSolution(int holes, int colours) {
        ArrayList<Integer> output = new ArrayList<>();
        Random rand = new Random();
        for (int i=1; i<=holes; i++) {
            output.add(rand.nextInt(colours));
        }

        return output;
    }

    public static ArrayList<Feedback> getFeedback(ArrayList<Integer> solution, ArrayList<Integer> guess) {
        if (solution.size() != guess.size()) {
            throw new IllegalArgumentException("guess and solution have different sizes.");
        }

        ArrayList<Integer> solCopy = deepCopyArrLst(solution);
        ArrayList<Integer> guessCopy = deepCopyArrLst(guess);

        ArrayList<Feedback> output = new ArrayList<>();

        ArrayList<Integer> toRemove = new ArrayList<>();
        for (int p=0; p<solCopy.size(); p++) {
            if (guessCopy.get(p).equals(solCopy.get(p))) {
                output.add(Feedback.POS_COL);
                toRemove.add(p);
            }
        }

        Collections.reverse(toRemove);
        for (Integer tr : toRemove) {
            guessCopy.remove((int) tr);
            solCopy.remove((int) tr);
        }

        if (solCopy.size() != guessCopy.size()) {
            throw new IllegalArgumentException("guessCopy and solCopy have different sizes.");
        }

        for (int p=0; p<guessCopy.size(); p++) {
            if (solCopy.contains(guessCopy.get(p))) {
                output.add(Feedback.COL);
                solCopy.remove(guessCopy.get(p));
            }
        }

        return output;
    }

    private static ArrayList<Integer> deepCopyArrLst(ArrayList<Integer> input) {
        ArrayList<Integer> output = new ArrayList<>();

        for (Integer i : input) {
            output.add(new Integer(i));
        }

        return output;
    }
}
