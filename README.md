# Sine via Taylor Series

Java implementation that computes the sine of an angle (in radians) using
the Taylor series expansion, without relying on `java.lang.Math`.

## Problem statement

Given an angle in radians, calculate its sine using the Taylor series:

```
sin(x) = x - x^3/3! + x^5/5! - x^7/7! + ...
```

Terms are accumulated only while they are still significant (their
absolute value is greater than a preset tolerance, `1e-10` by default),
which stops the loop once the series has effectively converged.

## Files

- `SineTaylor.java` — contains the reusable method `sine(double angle)` /
  `sine(double angle, double tolerance)`, plus a `main` method that reads
  an angle from standard input and prints the result.

## How to compile and run

```bash
javac SineTaylor.java
java SineTaylor
```

Example session:

```
Enter an angle in radians: 2
sin(2) = 0.909297426825641
```

## Reusing the method

Any other class in the module's project can reuse the calculation without
rewriting the algorithm:

```java
double result = SineTaylor.sine(1.57);
```

## Notes

- No method from `java.lang.Math` is used, as required by the exercise.
- The Taylor series formula is a standard mathematical technique (public
  domain); the Java implementation itself is original.
