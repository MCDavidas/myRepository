package series;

import java.io.*;

public class Liner extends Series {
    private double p;

    public Liner(double x, double y, int n) {
        super(x, n);
        p = y;
    }

    public void setP(double curr) {
        p = curr;
    }

    public double getElem(int j) {
        return firstElem + p*j;
    }

    public void save(PrintWriter out) {
        out.println("Liner progression: ");
        out.println(" firstElem = " + String.valueOf(firstElem));
        out.println(" p = " + String.valueOf(p));
        out.println(" sum = " + this.getSum());
        out.print(' ');
        super.save(out);
    }
}