#include <iostream>
#include <algorithm>
#include <vector>
#include <math.h>
#include <string>

using namespace std;

void OutVector( vector<int> v )
{
    cout << v.size() << endl;
    for_each( v.begin(), v.end(), []( const int &x ) { cout << x << " "; } );
    cout << endl;
}

template <typename T>
int IntSum(T curr) { return (int)curr; }

template <typename T, typename ...P>
int IntSum(T firstArg, P... args)
{
    return (int)firstArg + IntSum(args...);
}

template <typename T>
string ToStr(string &sep, T t) { return to_string(t); }

template <typename T, typename ...P>
string ToStr(string &sep, T firstArg, P... args)
{
    return ToStr(sep, firstArg) + sep + ToStr(sep, args...);
}

int main() {

    int n;
    cin >> n;

    vector<int> v(n);
    for ( int i=0; i<n; i++ )
        cin >> v[i];

    OutVector(v);

    sort ( v.begin(), v.end(), []( const int &x, const int &y ) { return abs(x) < abs(y); } );
    cout << "Sorted:" << endl;
    OutVector(v);

    cout << "FindEven: " << *find_if( v.begin(), v.end(), []( const int &x ) { return !(x&1); } ) << endl;

    int cnt = 0;
    for_each( v.begin(), v.end(), [&]( const int &x ) { cnt += !(x&1); } );
    cout << "CountEven: " << cnt << endl;

    replace_if( v.begin(), v.end(), []( const int &x ) { return x < 0; }, 0 );
    cout << "ReplaceNegative:" << endl;
    OutVector(v);

    short m = 13;
    int n1 = 17, n2 = 23;
    double x = 12.8;
    auto xSum = IntSum('a', n1, 4.7, m, 1.5, 10, n2 );

    cout << "xSum = " << xSum << endl;

    int n3 = 17;
    double x1 = 6.75;
    string sep = "; ";
    cout << ToStr(sep, 25, 3.7, n3, x1) << endl ;

    return 0;
}
