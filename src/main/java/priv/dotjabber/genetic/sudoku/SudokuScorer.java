package priv.dotjabber.genetic.sudoku;

import priv.dotjabber.genetic.Scorer;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuScorer extends Scorer {
    private static final int BL = 3;
    private static final int PL = BL * BL;

    public double getError(int[] p) {
        double error = 0;

        // each column has to have all digits used (1-9).
        // each row has to have all digits used (1-9).
        for(int i = 0; i < PL; i++) {
            Set<Integer> hs = IntStream.rangeClosed(1, PL).boxed().collect(Collectors.toSet());
            Set<Integer> vs = IntStream.rangeClosed(1, PL).boxed().collect(Collectors.toSet());

            for(int j = 0; j < PL; j++) {

                // if a digit was not removed, that means it was used already - digits repeat
                // and this situation gives +1 to error rate.
                if(!hs.remove(p[i * PL + j])) error++;
                if(!vs.remove(p[j * PL + i])) error++;
            }
        }

        for(int i = 0; i < BL; i += 3) {
            for(int j=0; j < BL; j += 3) {
                Set<Integer> numberSet = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toSet());

                for(int m = 0; m < BL; m++) {
                    for(int n = 0; n < BL; n++) {
                        if(!numberSet.remove(p[(i + m) * PL + (j + n)])) error++;
                    }
                }
            }
        }

        return error;
    }
}
