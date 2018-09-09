package priv.dotjabber.genetic.fuction;

import priv.dotjabber.genetic.Individual;
import priv.dotjabber.genetic.Population;

public class FunctionPopulation extends Population {

    public FunctionPopulation(double cp, double mp, int pc) {
        super(cp, mp, pc, new FunctionScorer(), null);
    }

    @Override
    public Individual create(Object param) {
        return new FunctionIndividual(true);
    }
}
