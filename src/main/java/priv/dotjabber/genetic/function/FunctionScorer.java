package priv.dotjabber.genetic.function;

import priv.dotjabber.genetic.Scorer;

public class FunctionScorer extends Scorer {
    private double[] x = { 0.05, 0.10, 0.15, 0.20, 0.25, 0.30, 0.35, 0.40, 0.45, 0.50, 0.55, 0.60, 0.65, 0.70, 0.75,
            0.80, 0.85, 0.90, 0.95, 1.00, 1.05, 1.10, 1.15, 1.20, 1.25, 1.30, 1.35  };


    private double[] v = { 12.9698, 17.3094, 16.1691, 11.4140, 5.5832, 0.9017, -1.3499, -1.0008, 1.2434, 4.2531, 6.9466,
            8.6238, 9.0913, 8.5930, 7.6187, 6.6837, 6.1571, 6.1815, 6.6864, 7.4657, 8.2788, 8.9368, 9.3492,
            9.5277, 9.5559, 9.5436, 9.5854 };

    private double f(double x, double a, double b, double c, double d, double g) {
        return a * Math.exp(-b * x) * c * Math.sin(d * x) + g * x + 3;
    }

    public double getError(int[] p) {
        double error = 0;

        // mean square error
        for(int i = 0; i < x.length; i++) {
            error += Math.pow(f(x[i], p[0], p[1], p[2], p[3], p[4]) - v[i], 2);
        }

        error /= v.length;
        return Double.isFinite(error) ? error : Double.MAX_VALUE;
    }
}
