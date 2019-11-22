import java.util.*;

public class Lab5
{
    public static double fun(double x, double eps)
    {
        double curr = x*x;
        double sum = 0.;
        int k = 1;
        while (Math.abs(curr) > eps)
        {
            sum += curr;
            curr *= k;
            k ++;
            curr *= x / (3 * k) ;
        }

        return sum;
    }

    public static void main(String[] args)
    {
        if (args.length != 2)
            throw new ArithmeticException("Two parametrs expected");

        double x;
        double eps;

        try
        {
            x = Double.parseDouble(args[0]);
            eps = Double.parseDouble(args[1]);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Format exception" + e.getMessage());
            x = eps = 0.;
        }

        if (x > 3.)
            throw new ArithmeticException("Diverge");

        System.out.printf("%8.6f\n", fun(x, eps));
    }
}
