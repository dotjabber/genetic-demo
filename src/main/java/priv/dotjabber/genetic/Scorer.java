package priv.dotjabber.genetic;

public abstract class Scorer {
    public abstract double getError(int[] genotype);
}