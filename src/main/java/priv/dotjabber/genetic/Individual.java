package priv.dotjabber.genetic;

import java.util.Arrays;
import java.util.Random;

public abstract class Individual {
    protected static final Random RAND = new Random();

    protected int[] chromosome;
    private double error = -1;

    public abstract Individual cross(Individual other);

    public abstract void mutate();

    public double getError(Scorer scorer) {
        if(error < 0) {
            error = scorer.getError(chromosome);
        }

        return error;
    }

    public double getError() {
        return error;
    }

    public int[] getChromosome() {
        return chromosome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individual that = (Individual) o;
        return Arrays.equals(chromosome, that.chromosome);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(chromosome);
    }
}
