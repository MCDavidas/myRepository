package series;

import java.io.*;

public class Liner extends Series
{
    private final double p;

    public Liner(double x, double y)
    {
        super(x);
        p = y;
    }

    public double getElem(int j)
    {
        return firstElem + p*j;
    }

    public void write(PrintWriter out, int n)
    {
        out.println("Liner progression: ");
        out.println(" firstElem = " + String.valueOf(firstElem));
        out.println(" p = " + String.valueOf(p));
        out.println(" sum = " + this.getSum(n));
        out.println(' ' + this.toString(n));
    }
}
