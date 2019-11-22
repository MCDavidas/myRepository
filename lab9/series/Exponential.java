package series;

import java.io.*;

public class Exponential extends Series
{
    private final double q;

    public Exponential(double x, double y)
    {
        super(x);
        q = y;
    }

    public double getElem(int j)
    {
        return firstElem * Math.pow(q, j);
    }

    public void write(PrintWriter out, int n)
    {
        out.println("Exponential progression: ");
        out.println(" firstElem = " + String.valueOf(firstElem));
        out.println(" q = " + String.valueOf(q));
        out.println(" sum = " + this.getSum(n));
        out.println(' ' + this.toString(n));
    }
}
