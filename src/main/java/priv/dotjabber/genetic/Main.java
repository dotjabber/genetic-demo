package priv.dotjabber.genetic;

import priv.dotjabber.genetic.sudoku.SudokuPopulation;

public class Main {
    private static final double CROSS_PARAM = 0.9;
    private static final double MUTATE_PARAM = 0.9;
    private static final int POPULATION_COUNT = 100;
    private static final double ERROR_THRESHOLD = 0.001;

    public static void main(String[] args) {
//        Population population = new FunctionPopulation(CROSS_PARAM, MUTATE_PARAM, POPULATION_COUNT);

        // solved
//        Population population = new SudokuPopulation(CROSS_PARAM, MUTATE_PARAM, POPULATION_COUNT, new int[] {
//                7, 3, 5,  6, 1, 4,  8, 9, 2,
//                8, 4, 2,  9, 7, 3,  5, 6, 1,
//                9, 6, 1,  2, 8, 5,  3, 7, 4,
//
//                2, 8, 6,  3, 4, 9,  1, 5, 7,
//                4, 1, 3,  8, 5, 7,  9, 2, 6,
//                5, 7, 9,  1, 2, 6,  4, 3, 8,
//
//                1, 5, 7,  4, 9, 2,  6, 8, 3,
//                6, 9, 4,  7, 3, 8,  2, 1, 5,
//                3, 2, 8,  5, 6, 1,  7, 4, 9
//        });

        // great cross
//       Population population = new SudokuPopulation(CROSS_PARAM, MUTATE_PARAM, POPULATION_COUNT, new int[] {
//                7, 3, 5,  0, 0, 0,  8, 9, 2,
//                8, 4, 2,  0, 0, 0,  5, 6, 1,
//                9, 6, 1,  0, 0, 0,  3, 7, 4,
//
//                0, 0, 0,  0, 0, 0,  0, 0, 0,
//                0, 0, 0,  0, 0, 0,  0 ,0, 0,
//                0, 0, 0,  0, 0, 0,  0, 0, 0,
//
//                1, 5, 7,  0, 0, 0,  6, 8, 3,
//                6, 9, 4,  0, 0, 0,  2, 1, 5,
//                3, 2, 8,  0, 0, 0,  7, 4, 9
//        });


        // corner case - empty
//        Population population = new SudokuPopulation(CROSS_PARAM, MUTATE_PARAM, POPULATION_COUNT, new int[] {
//                0, 0, 0,  0, 0, 0,  0, 0, 0,
//                0, 0, 0,  0, 0, 0,  0, 0, 0,
//                0, 0, 0,  0, 0, 0,  0, 0, 0,
//
//                0, 0, 0,  0, 0, 0,  0, 0, 0,
//                0, 0, 0,  0, 0, 0,  0, 0, 0,
//                0, 0, 0,  0, 0, 0,  0, 0, 0,
//
//                0, 0, 0,  0, 0, 0,  0, 0, 0,
//                0, 0, 0,  0, 0, 0,  0, 0, 0,
//                0, 0, 0,  0, 0, 0,  0, 0, 0
//        });

        // hard
        Population population = new SudokuPopulation(CROSS_PARAM, MUTATE_PARAM, POPULATION_COUNT, new int[] {
                0, 0, 2,  0, 0, 0,  1, 0, 0,
                6, 0, 0,  7, 0, 0,  8, 0, 4,
                0, 9, 7,  0, 0, 4,  0, 0, 3,

                4, 0, 0,  1, 0, 0,  0, 0, 0,
                0, 7, 0,  3, 0, 2,  0, 6, 0,
                0, 0, 0,  0, 0, 8,  0, 0, 2,

                2, 0, 0,  9, 0, 0,  6, 3, 0,
                7, 0, 1,  0, 0, 3,  0, 0, 9,
                0, 0, 9,  0, 0, 0,  2, 0, 0
        });

        // middle
//        Population population = new SudokuPopulation(CROSS_PARAM, MUTATE_PARAM, POPULATION_COUNT, new int[] {
//                4, 0, 0,  0, 0, 0,  6, 0, 0,
//                0, 8, 0,  9, 6, 0,  1, 0, 3,
//                0, 3, 9,  0, 0, 7,  0, 0, 0,
//
//                0, 0, 4,  0, 0, 6,  0, 3, 1,
//                0, 1, 0,  5, 0, 8,  0, 4, 0,
//                8, 6, 0,  1, 0, 0,  5, 0, 0,
//
//                0, 0, 0,  3, 0, 0,  9, 2, 0,
//                3, 0, 8,  0, 5, 9,  0, 1, 0,
//                0, 0, 7,  0, 0, 0,  0, 0, 5
//        });

        // easy
//        Population population = new SudokuPopulation(CROSS_PARAM, MUTATE_PARAM, POPULATION_COUNT, new int[] {
//                1, 4, 0,  0, 9, 0,  0, 3, 5,
//                0, 0, 9,  0, 1, 0,  8, 0, 0,
//                5, 0, 0,  8, 0, 4,  0, 0, 6,
//
//                2, 9, 0,  0, 0, 0,  0, 8, 3,
//                0, 0, 0,  3, 0, 5,  0, 0, 0,
//                3, 8, 0,  0, 0, 0,  0, 6, 1,
//
//                8, 0, 0,  9, 0, 1,  0, 0, 4,
//                0, 0, 7,  0, 2, 0,  1, 0, 0,
//                9, 6, 0,  0, 7, 0,  0, 5, 2
//        });

        // unsolvable?
        // http://theconversation.com/good-at-sudoku-heres-some-youll-never-complete-5234
//        Population population = new SudokuPopulation(CROSS_PARAM, MUTATE_PARAM, POPULATION_COUNT, new int[] {
//                0, 0, 0,  7, 0, 0,  0, 0, 0,
//                1, 0, 0,  0, 0, 0,  0, 0, 0,
//                0, 0, 0,  4, 3, 0,  2, 0, 0,
//
//                0, 0, 0,  0, 0, 0,  0, 0, 6,
//                0, 0, 0,  5, 0, 9,  0, 0, 0,
//                0, 0, 0,  0, 0, 0,  4, 1, 8,
//
//                0, 0, 0,  0, 8, 1,  0, 0, 0,
//                0, 0, 2,  0, 0, 0,  0, 5, 0,
//                0, 4, 0,  0, 0, 0,  3, 0, 0
//        });

        int generation = 0;
        while (population.best().getError() > ERROR_THRESHOLD) {
            population.next();

            System.out.println("generation: " + ++generation + ", error: " + population.best().getError());
        }

        System.out.println("final: " +  population.best());
    }
}
