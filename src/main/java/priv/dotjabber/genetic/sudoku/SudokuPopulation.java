package priv.dotjabber.genetic.sudoku;

import priv.dotjabber.genetic.Individual;
import priv.dotjabber.genetic.Population;

public class SudokuPopulation extends Population {

    public SudokuPopulation(double cp, double mp, int pc, int[] board) {
        super(cp, mp, pc, new SudokuScorer(), board);
    }

    @Override
    public Individual create(Object param) {
        return new SudokuIndividual((int[]) param);
    }
}
