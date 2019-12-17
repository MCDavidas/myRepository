#include <bits/stdc++.h>

using namespace std;

const int N = 6;
const double B[6][6] = {{1.342, 0.432, -0.599, 0.202, 0.603, -0.202},
                        {0.432, 1.342, 0.256, -0.599, 0.204, 0.304},
                        {-0.599, 0.256, 1.342, 0.532, 0.101, 0.506},
                        {0.202, -0.599, 0.532, 1.342, 0.106, -0.311},
                        {0.603, 0.204, 0.101, 0.106, 1.342, 0.102},
                        {-0.202, 0.304, 0.506, -0.311, 0.102, 1.342}};
const double C[6] = {0.05, 0.03, 0.02, 0.04, 0.06, 0.07};
const int K = 7;
const double Eps = 0.000001;

void OutMatrix( double A[N][N] )
{
    int i, j;

    cout.precision( 3 );
    for ( i=0; i<N; i++ ) {
        for ( j=0; j<N; j++ )
            cout << A[i][j] << " ";
        cout << endl;
    }
}

void OutVector( double V[N] )
{
    int i;

    for ( i=0; i<N; i++ )
        cout << V[i] << endl;
}

void Multiply(double A[N][N], double B[N], double C[N])
{
    for (int i=0; i<N; i++)
      C[i] = 0.;

    for (int i=0; i<N; i++)
        for(int j=0; j<N; j++)
            C[i] += A[i][j] * B[j];

    return;
}

double MaxNorm( double X[N], double Y[N] )
{
    int i;

    double ans = 0.;
    for ( i=0; i<N; i++ )
        ans = max( ans, fabs(X[i] - Y[i]) );

    return ans;
}

double MaxNorm( double X[N] )
{
    int i;

    double ans = 0.;
    for ( i=0; i<N; i++ )
        ans = max( ans, fabs(X[i]) );

    return ans;
}

void Solve1(double A[N][N])
{
    int it = 0;
    double lambd2[N] = {1, 1, 1, 1, 1, 1};
    double u2[N] = {1, 1, 1, 1, 1, 1};
    double v1[N];
    double u1[N];
    double lambd1[N];

    Multiply(A, u2, v1);

    double maxValue = MaxNorm(v1);
    for(int i = 0; i < N; ++i)
        u1[i] = v1[i] / maxValue;
    for(int i = 0; i < N; ++i)
        lambd1[i] = v1[i] / u2[i];

    while (MaxNorm(lambd1, lambd2) > Eps) {
        it++;
        for(int i = 0; i < N; ++i)
          u2[i] = u1[i];
        for(int i = 0; i < N; ++i)
          lambd2[i] = lambd1[i];

        Multiply(A, u2, v1);

        maxValue = MaxNorm(v1);
        for(int i = 0; i < N; ++i)
            u1[i] = v1[i] / maxValue;
        for(int i = 0; i < N; ++i)
            lambd1[i] = v1[i] / u2[i];
    }

    cout << lambd1[0] << endl;
    cout << "Vector: " << endl;
    OutVector(u1);
    cout << "Iterations: " << it << "\n";

    return;
}

double GetLambda( double A[N], double B[N])
{
    double curr1 = 0.;
    double curr2 = 0.;

    for (int i=0; i<N; i++)
      curr1 += A[i] * B[i];

    for (int i=0; i<N; i++)
      curr2 += B[i] * B[i];

    return curr1 / curr2;
}

void Solve2(double A[N][N])
{
    int it = 0;
    double vallambd1;
    double vallambd2 = 0;
    double u2[N] = {1, 1, 1, 1, 1, 1};
    double v1[N];
    double u1[N];

    Multiply(A, u2, v1);

    double maxValue = MaxNorm(v1);
    for(int i = 0; i < N; ++i)
        u1[i] = v1[i] / maxValue;

    vallambd1 = GetLambda(v1, u2);

    while (abs(vallambd1 - vallambd2) > Eps)
    {
        it++;
        for(int i = 0; i < N; ++i)
          u2[i] = u1[i];
        vallambd2 = vallambd1;

        Multiply(A, u2, v1);

        maxValue = MaxNorm(v1);
        for(int i = 0; i < N; ++i)
            u1[i] = v1[i] / maxValue;
        vallambd1 = GetLambda(v1, u2);
    }

    cout << vallambd1 << endl;
    cout << "Vector: " << endl;
    OutVector(u1);
    cout << "Iterations: " << it << "\n";

    return;
}

int main(){
    cout.setf(ios::fixed | ios::showpos);

    double A[6][6];
    for (int i=0; i<6; i++){
      for (int j=0; j<6; j++)
        A[i][j] = B[i][j];
      A[i][i] += K*C[i];
    }

    cout << "A:" << endl;
    OutMatrix(A);

    cout.precision( 9 );
    Solve1(A);
    Solve2(A);

    return 0;
}
