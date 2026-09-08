import java.util.Scanner;

/**
 * Solution for "Seno de angulo" (Scenario 2, proposed problem 6).
 *
 * Computes the sine of an angle (given in radians) using the Taylor
 * series expansion of sin(x), without using java.lang.Math.
 *
 * Taylor series:
 *   sin(x) = x - x^3/3! + x^5/5! - x^7/7! + ...
 *          = sum_{n=0}^{infinity} (-1)^n * x^(2n+1) / (2n+1)!
 *
 * Terms are accumulated only while they are still significant
 * (larger in absolute value than a preset tolerance), which avoids
 * unnecessary iterations once the series has converged.
 */
public class SineTaylor {

    // Smallest term magnitude we still consider significant.
    private static final double DEFAULT_TOLERANCE = 0.0000000001; // 1e-10

    /**
     * Calculates sin(angle) using the Taylor series, stopping once the
     * magnitude of the next term is below the given tolerance.
     *
     * Each new term is obtained from the previous one with the
     * relation:
     *   term(n+1) = term(n) * (-angle * angle) / ((2n+2) * (2n+3))
     * This is cheaper than recomputing powers and factorials from
     * scratch on every iteration.
     *
     * @param angle     angle in radians
     * @param tolerance minimum absolute term value to keep iterating
     * @return an approximation of sin(angle)
     */
    public static double sine(double angle, double tolerance) {
        double term = angle;   // first term of the series: x
        double sum = term;     // running sum, starts with the first term
        int n = 0;             // term index, starts at n = 0

        while (abs(term) > tolerance) {
            // Ratio between consecutive terms of the series.
            double denominator = (2 * n + 2) * (2 * n + 3);
            term = term * (-angle * angle) / denominator;
            sum = sum + term;
            n++;
        }

        return sum;
    }

    /**
     * Overload that uses the module's default tolerance (1e-10).
     *
     * @param angle angle in radians
     * @return an approximation of sin(angle)
     */
    public static double sine(double angle) {
        return sine(angle, DEFAULT_TOLERANCE);
    }

    // Small helper so this class does not depend on Math.abs from
    // outside; keeps the "no Math class" restriction explicit.
    private static double abs(double value) {
        return value < 0 ? -value : value;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an angle in radians: ");
        double angle = Double.parseDouble(scanner.nextLine().trim());

        double result = sine(angle);

        System.out.println("sin(" + angle + ") = " + result);

        scanner.close();
    }
}
