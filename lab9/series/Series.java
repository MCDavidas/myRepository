package series;

import java.io.*;

public abstract class Series
{
    protected final double firstElem;

    public Series(double x)
    {
        firstElem = x;
    }

    public abstract double getElem(int j);

    public double getSum(int n)
    {
        double sum = 0.;
        for (int i=0; i<n; i++)
            sum += getElem(i);
        return sum;
    }

    public String toString(int n)
    {
        StringBuffer str = new StringBuffer();
        for (int i=0; i<n; i++)
            str.append(getElem(i)).append(' ');
        return str.toString();
    }

    public void save(PrintWriter out, int n)
    {
        out.println(this.toString(n));
    }
}
