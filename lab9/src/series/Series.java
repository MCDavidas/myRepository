package series;

import java.io.*;

public abstract class Series {
    protected double firstElem;
    protected int n;

    public Series(double x, int count) {
        firstElem = x;
        n = count;
    }

    public void setFirstElem(double curr) {
        firstElem = curr;
    }

    public void setN(int curr) {
        n = curr;
    }

    public abstract double getElem(int j);

    public double getSum() {
        double sum = 0.;
        for (int i=0; i<n; i++)
            sum += getElem(i);
        return sum;
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        for (int i=0; i<n; i++)
            str.append(String.format("%.3f", getElem(i))).append(' ');
        return str.toString();
    }

    public void save(PrintWriter out) {
        out.println(this.toString());
    }
}