#include <bits/stdc++.h>

using namespace std;

const int N = 10;
const double Eps = 0.00001;
const double W[7] = {0.2, 0.5, 0.8, 1, 1.3, 1.5, 1.8};

double GenNumb()
{
    return (double) ((rand() % 2000) - 1000) / 100.;
}

void OutMatrix( double A[N][N+1] )
{
    int i, j;

    for ( i=0; i<N; i++ ){
        cout.precision( 2 );
        for ( j=0; j<N; j++ )
            cout << A[i][j] << " ";

        cout.precision( 4 );
        cout << "| " << A[i][N] << endl;
    }
}

void OutVector( double V[N] )
{
    int i;

    cout.precision( 9 );
    for ( i=0; i<N; i++ )
        cout << V[i] << endl;
}

void Gen( double A[N][N+1], double X[N] )
{
    srand(time(NULL));

    double B[N][N+1];

    int i, j;
    for (i=0; i<N; i++){
        for (j = 0; j<N; j++){
            B[i][j] = GenNumb() / (double)N;
            A[i][j] = 0.;
        }

        X[i] = GenNumb();
    }

    for (i=0; i<N; i++)
        for (j=0; j<N; j++)
            for (int k=0; k<N; k++)
                A[i][j] += B[i][k] * B[j][k];

    for (i=0; i<N; i++){
        A[i][N] = 0.;
        for ( j=0; j<N; j++ )
            A[i][N] += A[i][j] * X[j];
    }
}

double MaxNorm( double X[N], double Y[N] )
{
    int i;

    double ans = 0.;
    for ( i=0; i<N; i++ )
        ans = max( ans, fabs(X[i] - Y[i]) );

    return ans;
}

void Solve(double A[N][N+1], double X[N], double Y[N], double w, ofstream& out)
{
    int i;
    int j;
    double Yprev[N];

    for (i=0; i<N; i++)
        Y[i] = Yprev[i] = 1.;

    int cnt = 0;
    do
    {
        for (i=0; i<N; i++)
            Yprev[i] = Y[i];

        for (i=0; i<N; i++){
            Y[i] = (1. - w) * Yprev[i];

            double sum = A[i][N];
            for (j=0; j<i; j++)
                sum -= A[i][j] * Y[j];
            for (j=i+1; j<N; j++)
                sum -= A[i][j] * Yprev[j];

            Y[i] += sum * (w / A[i][i]);
        }

        cnt ++;
    }
    while (MaxNorm(Y, Yprev) > Eps);

    out.setf(ios::fixed);
    out.precision(1);
    out << w << " ";
    out << cnt << " ";
    out.precision(9);
    out << MaxNorm(Y, Yprev) << " ";
    out << MaxNorm(Y, X) << endl;

    return;
}

int main(){
    cout << "e = " << Eps << endl;

    double A[N][N+1];
    double X[N];
    double Y[N];

    cout.setf(ios::fixed | ios::showpos);

    Gen(A, X);
    cout << "A:" << endl;
    OutMatrix(A);
    cout << "X:" << endl;
    OutVector(X);

    ofstream out("results.txt");
    for (int i=0; i<7; i++)
        Solve(A, X, Y, W[i], out);
    out.close();

    cout << "Y:" << endl;
    OutVector(Y);

    return 0;
}
