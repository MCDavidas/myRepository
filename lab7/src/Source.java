import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class Source
{
    public static void inversion(double[][] A)
    {
        int n = A.length;
        double[][] E = new double [n][n];

        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++)
            {
                E[i][j] = 0.;
                if (i == j)
                    E[i][j] = 1.;
            }

        forward(A, E, n);
        backward(A, E, n);
    }

    private static void forward(double[][] A, double[][] E, int n)
    {
        double temp;
        for (int k=0; k<n; k++)
        {
            temp = A[k][k];

            if (Math.abs(temp) < 0.00000001)
                throw new ArithmeticException("Determinate is zero.");

            for (int j=0; j<n; j++)
            {
                A[k][j] /= temp;
                E[k][j] /= temp;
            }

            for (int i=k+1; i<n; i++)
            {
                temp = A[i][k];

                for (int j=0; j<n; j++)
                {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }
    }

    private static void backward(double[][] A, double[][] E, int n)
    {
        double temp;
        for (int k=n-1; k>0; k--)
        {
            for (int i=k-1; i>=0; i--)
            {
                temp = A[i][k];

                for (int j=0; j<n; j++)
                {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        for (int i=0; i<n; i++)
            for (int j=0; j<n; j++)
                A[i][j] = E[i][j];
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File("input.txt"));

        int n = 0;
        double[][] A = null;

        try
        {
            n = in.nextInt();
            A = new double [n][n];
            for (int i=0; i<n; i++)
                for (int j=0; j<n; j++)
                    A[i][j] = in.nextDouble();
        }
        catch (InputMismatchException e)
        {
            System.out.println("Incorrect input.");
            System.exit(-1);
        }

        inversion(A);

        for (int i=0; i<n; i++)
        {
            for (int j=0; j<n; j++)
                System.out.printf("% .3f ", A[i][j]);
            System.out.println();
        }
    }
}