import java.util.*;
import java.io.*;
import series.*;

public class Lab9
{
    public static void main(String[] args) throws FileNotFoundException
    {
        PrintWriter out = new PrintWriter(new File("output.txt"));

        Liner a = new Liner(1., 0.5);
        Exponential b = new Exponential(1., 2.);

        a.write(out, 4);
        b.write(out, 4);

        out.close();
    }
}
