package series;

import java.io.*;

public class Exponential extends Series {
    public Exponential(double x, double y, int n) {
        super(x, y, n);
    }

    public double getElem(int j) {
        return firstElem * Math.pow(q, j);
    }

    public void save(PrintWriter out) {
        out.println("Exponential progression: ");
        out.println(" firstElem = " + String.valueOf(firstElem));
        out.println(" q = " + String.valueOf(q));
        out.println(" sum = " + this.getSum());
        out.print(' ');
        super.save(out);
    }
}