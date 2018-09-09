package priv.dotjabber.genetic;

import java.util.*;

public abstract class Population {
    private static final Random RAND = new Random();

    private List<Individual> currentContainer;
    private List<Individual> nextContainer;

    private double crossParam;
    private double mutateParam;
    private int populationCount;

    private java.util.Comparator comparator;
    private Object initParam;

    public Population(double cp, double mp, int pc, Scorer sc, Object ip) {
        crossParam = cp;
        mutateParam = mp;
        populationCount = pc;
        initParam = ip;
        comparator = new Comparator(sc);

        currentContainer = new ArrayList<>();
        nextContainer = new ArrayList<>();

        for(int i = 0; i < populationCount; i++) {
            currentContainer.add(create(initParam));
        }

        currentContainer.sort(comparator);
    }

    public void next() {
        nextContainer.clear();

        for(Individual p1 : currentContainer) {
            for(Individual p2 : currentContainer) {
                if(p1 == p2) break;

                // can they cross?
                if(RAND.nextDouble() < crossParam) {
                    Individual c = p1.cross(p2);

                    // can the child mutate?
                    if(RAND.nextDouble() < mutateParam) {
                        c.mutate();
                    }

                    // add it to the next generation (if it does't exists already
                    // there might be the same individuals...)
                    if(!nextContainer.contains(c)) {
                        nextContainer.add(c);
                    }
                }
            }

            // add parent to the next generation
            if(!nextContainer.contains(p1)) {
                nextContainer.add(p1);
            }
        }

        // take best individuals into the next generation
        currentContainer.clear();

        // sort next generation
        nextContainer.sort(comparator);

        // take only the best (50% of the population stays within new generation)
        Iterator<Individual> it = nextContainer.iterator();
        while(it.hasNext() && currentContainer.size() < populationCount / 2) {
            currentContainer.add(it.next());
        }

        // if there is not enough individuals, generate them up to populationCount param.
        // fresh blood? :)
        while(currentContainer.size() < populationCount) {
            currentContainer.add(append(initParam));
        }
    }

    public Individual best() {
        return currentContainer.get(0);
    }

    public Individual append(Object param) {
        return create(param);
    }

    public abstract Individual create(Object param);
}
