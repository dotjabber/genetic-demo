package priv.dotjabber.genetic;

public class Comparator implements java.util.Comparator<Individual> {
    private final Scorer scorer;

    public Comparator(Scorer scorer) {
        this.scorer = scorer;
    }

    public int compare(Individual i1, Individual i2) {
        double sc1 = i1.getError(scorer);
        double sc2 = i2.getError(scorer);

        if(sc1 < sc2) return -1;
        else if(sc1 > sc2) return 1;
        return 0;
    }
}
