#include <iostream>
#include <fstream>
#include <algorithm>
#include <string>
#include <vector>
#include <sstream>
#include <iterator>
#include <set>

using namespace std;

class Fisherman
{
    public:

        class Fish
        {
        public:
            string Name;
            double mass;

            friend istream& operator >> ( istream &in, Fish &curr )
            {
                in >> curr.Name >> curr.mass;
                return in;
            }

            friend ostream& operator << ( ostream &out, const Fish &curr )
            {
                out << curr.Name << " " << curr.mass;
                return out;
            }
        };

        string Name;
        string Surname;
        int Age;
        vector < Fish > Fishes;
        double Sum_Mass;

        bool Is_18 ()
        {
            return this->Age < 18.;
        }

        bool Is_30 ()
        {
            return ( this->Age >= 18.  &&  this->Age < 30. );
        }

        bool Is_Inf ()
        {
            return this->Age >= 30.;
        }

        static bool cmp_for_18 ( Fisherman &a, Fisherman &b )
        {
            if ( a.Is_18() && !b.Is_18() )
                return false;
            if ( !a.Is_18() && b.Is_18() )
                return true;

            return ( a.Sum_Mass < b.Sum_Mass );
        }

    static bool cmp_for_30 ( Fisherman &a, Fisherman &b )
    {
        if ( a.Is_30() && !b.Is_30() )
            return false;
        if ( !a.Is_30() && b.Is_30() )
            return true;

        return ( a.Sum_Mass < b.Sum_Mass );
    }

    static bool cmp_for_inf ( Fisherman &a, Fisherman &b )
    {
        if ( a.Is_Inf() && !b.Is_Inf() )
            return false;
        if ( !a.Is_Inf() && b.Is_Inf() )
            return true;

        return ( a.Sum_Mass < b.Sum_Mass );
    }

    friend  bool operator < (const Fisherman &a, const Fisherman &b )
    {
        return ( a.Surname + a.Name  <  b.Surname + b.Name );
    }

        Fisherman() = default;
        ~Fisherman() = default;

    friend istream& operator >> ( istream &in, Fisherman &curr )
    {
        in >> curr.Surname >> curr.Name >> curr.Age;

        string s;
        getline( in, s);
        getline( in, s);
        istringstream str(s);

        curr.Fishes = vector<Fish>((istream_iterator<Fish>(str)),
                istream_iterator<Fish>() ) ;

        curr.Sum_Mass = 0.;
        for_each( curr.Fishes.begin(), curr.Fishes.end(),
                [&]( const Fish &x ) { curr.Sum_Mass += x.mass; } );

        return in;
    }

    friend ostream& operator << ( ostream &out, const Fisherman &curr )
    {
        out << curr.Surname << " " << curr.Name << " " << curr.Age << " " << curr.Sum_Mass << endl;
        copy( curr.Fishes.begin(), curr.Fishes.end(), ostream_iterator<Fish>(out, " ") );
        return out;
    }

};

void Reading ( ifstream &in, vector<Fisherman> &input )
{
        vector <Fisherman> File_Input ((istream_iterator<Fisherman>(in)),
                                       istream_iterator<Fisherman>() ) ;

        transform( File_Input.begin(), File_Input.end(), insert_iterator< vector<Fisherman> >(input, input.end()),
                []( const Fisherman &x ) -> Fisherman { return x; } );
}

template <class T>
void Writing ( ofstream &out, vector<T> &v )
{
    copy( v.begin(), v.end(), ostream_iterator<T>(out, "\n") );
}

void Find_Max ( ofstream &out, vector<Fisherman> input )
{
    sort ( input.begin(), input.end(), Fisherman::cmp_for_18 );
    if ( !input.empty() &&  input.back().Is_18() )
        out << input.back().Surname << " " << input.back().Name << " " << input.back().Age << " "
            << input.back().Sum_Mass << endl;
    else
        out << "None" << endl;

    sort ( input.begin(), input.end(), Fisherman::cmp_for_30 );
    if ( !input.empty() &&  input.back().Is_30() )
        out << input.back().Surname << " " << input.back().Name << " " << input.back().Age << " "
            << input.back().Sum_Mass << endl;
    else
        out << "None" << endl;

    sort ( input.begin(), input.end(), Fisherman::cmp_for_inf );
    if ( !input.empty() &&  input.back().Is_Inf() )
        out << input.back().Surname << " " << input.back().Name << " " << input.back().Age << " "
            << input.back().Sum_Mass << endl;
    else
        out << "None" << endl;
}

void New_Table ( ofstream &out, set<Fisherman> &Input_Set)
{
    vector<Fisherman> input;
    input.resize( Input_Set.size() );

    transform( Input_Set.begin(), Input_Set.end(), input.begin(), []( const Fisherman &x ) {return x;} );

    sort ( input.begin(), input.end(), []( const Fisherman &a, const Fisherman &b ) -> bool
    {
        if ( a.Age != b.Age )
            return a.Age < b.Age;

        return ( a.Surname + a.Name < b.Surname + b.Name );
    });

    for_each( input.begin(), input.end(), []( Fisherman &a )
    {
       if ( a.Is_18() )
           a.Sum_Mass *= 1.1;
       if ( a.Is_Inf() )
           a.Sum_Mass *= 0.9;
    });

    for_each( input.begin(), input.end(), [&]( const Fisherman &x )
    {
        out << x.Surname << " " << x.Name << " " << x.Age << " " << x.Sum_Mass << endl;
    } );
}

void Create_Table ( ofstream &out, set<Fisherman> &input )
{
    for_each( input.begin(), input.end(), [&]( const Fisherman &x )
    {
        out << x.Surname << " " << x.Name << " " << x.Sum_Mass << endl;
    } );
}

void Solve ()
{
    vector<Fisherman> input;
    string File_Name = "fishers2016.txt";
    ofstream out ("output.txt");

    for ( int i=2017; i<=2019; i++ )
    {
        input.clear();
        File_Name[10] = (char)( (int)'0' + i%10);
        ifstream in( File_Name );

        Reading( in, input );

        out << "TASK_6:" << endl;
        Writing( out, input );
        out << endl;

        out << "TASK_7:" << endl;
        Find_Max( out, input );
        out << endl;
    }


    input.clear();
    set <Fisherman> Input_Set;
    File_Name = "fishers2016.txt";
    for ( int i=2017; i<=2019; i++ )
    {
        input.clear();
        File_Name[10] = (char)( (int)'0' + i%10);
        ifstream in( File_Name );

        Reading( in, input );

        for ( auto it : input )
        {
            if (Input_Set.count(it))
            {
                auto set_it = Input_Set.find(it);
                it.Sum_Mass += set_it->Sum_Mass;
                Input_Set.erase( set_it );
            }

            Input_Set.insert( it );
        }
    }

    out << "TASK_8:" << endl;
    Create_Table( out, Input_Set );
    out << endl;

    out << "TASK_10:" << endl;
    New_Table( out, Input_Set );
}

int main()
{
    Solve();
    return 0;
}