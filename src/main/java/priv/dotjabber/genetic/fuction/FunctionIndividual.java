package priv.dotjabber.genetic.fuction;

import priv.dotjabber.genetic.Individual;

import java.util.Arrays;

public class FunctionIndividual extends Individual {

    // chromosome (5 x 32 bits: a, b, c, d, g, 5 genomes)
    private static final int LENGTH = 5;

    // genome range
    private static final int RANGE = 32;

    public FunctionIndividual(boolean preset) {
        chromosome = new int[LENGTH];

        // set chromosome randomly
        if (preset) {
            for(int i = 0; i < LENGTH; i++) {
                chromosome[i] = RAND.nextInt(RANGE) - RANGE / 2;
            }
        }
    }

    public void mutate() {
        // take random bit
        int bit = RAND.nextInt(LENGTH * Integer.SIZE);
        int hi = bit / Integer.SIZE; // genome index
        int bi = bit % Integer.SIZE; // bit index

        chromosome[hi] ^= 1 << bi; // set t
    }

    public Individual cross(Individual other) {
        FunctionIndividual child = new FunctionIndividual(false);

        // take the random cross point
        // - if we consider a bit below the point, the bit value
        // is taken from this individual.
        // - if we consider a bit above the point, the bit value
        // is taken from other individual.
        int cs = LENGTH * Integer.SIZE; // chromosome size
        int ci = RAND.nextInt(cs); // cross index

        for (int i = 0; i < cs; i++) {
            int hi = i / Integer.SIZE; // chromosome index
            int bi = i % Integer.SIZE; // bit index

            if(i < ci) {
                child.chromosome[hi] |= ((this.getChromosome()[hi] >> bi) & 1) << bi;

            } else {
                child.chromosome[hi] |= ((other.getChromosome()[hi] >> bi) & 1) << bi;
            }
        }

        return child;
    }

    @Override
    public String toString() {
        return Arrays.toString(chromosome) + ", error: " + getError();
    }
}
