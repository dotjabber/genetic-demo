package priv.dotjabber.genetic.sudoku;

import priv.dotjabber.genetic.Individual;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuIndividual extends Individual {
    private static final Random RAND = new Random();

    // genotype (9 X 9 genomes, integers filling whole sudoku board)
    private static final int BLOCK = 3;
    private static final int LENGTH = BLOCK * BLOCK;
    private int[] template;

    public SudokuIndividual() {
    }

    public SudokuIndividual(int[] board) {
        template = board;
        genotype = new int[board.length];

        for(int i = 0; i < LENGTH; i += BLOCK) {
            for (int j = 0; j < LENGTH; j += BLOCK) {
                List<Integer> numbers = IntStream.rangeClosed(1, LENGTH).boxed().collect(Collectors.toList());

                // remove existing numbers (we assume that input sudoku is correct,
                // so there are no duplications per block)
                for (int m = 0; m < BLOCK; m++) {
                    for (int n = 0; n < BLOCK; n++) {
                        if (template[(i + m) * LENGTH + (j + n)] > 0) {
                            genotype[(i + m) * LENGTH + (j + n)] = template[(i + m) * LENGTH + (j + n)];
                            numbers.remove(Integer.valueOf(template[(i + m) * LENGTH + (j + n)]));
                        }
                    }
                }

                // add remaining
                Collections.shuffle(numbers);
                for (int m = 0; m < BLOCK; m++) {
                    for (int n = 0; n < BLOCK; n++) {
                        if (template[(i + m) * LENGTH + (j + n)] == 0) {
                            genotype[(i + m) * LENGTH + (j + n)] = numbers.remove(0);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Individual cross(Individual other) {
        SudokuIndividual child = new SudokuIndividual();
        child.genotype = new int[LENGTH * LENGTH];
        child.template = template;

        // take the random cross point
        // - if we consider an integer below the point, the value
        // is taken from this individual.
        // - if we consider a integer above the point, the value
        // is taken from other individual.
        int x = RAND.nextInt(LENGTH);
        int ln = LENGTH * LENGTH; // genotype size

        for (int i = 0; i < ln; i++) {
            if(i < x) {
                child.genotype[i] = this.getGenotype()[i];

            } else {
                child.genotype[i] = other.getGenotype()[i];
            }
        }

        return child;
    }

    public void mutate() {
        // select board block
        int i = RAND.nextInt(BLOCK);
        int j = RAND.nextInt(BLOCK);

        // select two values on that block to switch
        int srcx = BLOCK * i + RAND.nextInt(BLOCK);
        int srcy = BLOCK * j + RAND.nextInt(BLOCK);

        int dstx = BLOCK * i + RAND.nextInt(BLOCK);
        int dsty = BLOCK * j + RAND.nextInt(BLOCK);

        // and swap them
        if(template[dstx * LENGTH + dsty] == 0 && template[srcx * LENGTH + srcy] == 0) {
            int tmp = genotype[dstx * LENGTH + dsty];
            genotype[dstx * LENGTH + dsty] = genotype[srcx * LENGTH + srcy];
            genotype[srcx * LENGTH + srcy] = tmp;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < LENGTH; i++) {
            if(i % 3 == 0) sb.append("\n");

            for(int j = 0; j < LENGTH; j++) {
                sb.append((j % 3 == 0) ? "  " : " ");
                sb.append(genotype[i * LENGTH + j]);
            }
            sb.append("\n");
        }

        return sb + System.lineSeparator() + "error: " + getError();
    }
}
