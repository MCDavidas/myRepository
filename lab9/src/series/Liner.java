package series;

import java.io.*;

public class Liner extends Series {
    public Liner(double x, double y, int n) {
        super(x, y, n);
    }

    public double getElem(int j) {
        return firstElem + q*j;
    }

    public void save(PrintWriter out) {
        out.println("Liner progression: ");
        out.println(" firstElem = " + String.valueOf(firstElem));
        out.println(" p = " + String.valueOf(q));
        out.println(" sum = " + this.getSum());
        out.print(' ');
        super.save(out);
    }
}