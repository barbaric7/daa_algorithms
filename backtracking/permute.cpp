#include <iostream>
using namespace std;

int count = 0;

void permute(int idx,string s,string o){

    if(s.size() == 0){
            cout<< o << endl;
            cout<< ++count << endl;
        }

    for(int i=0; i<s.size(); i++){

        

        char firstChar = s[i];
        string new_s = s.substr(0,i)+s.substr(i+1);
        permute(idx+1,new_s,o+firstChar);
    }


};


void arrangements(int idx,string s,string o){

    if(o.size() == 3){

        cout<< o << endl;
        cout<< ++count << endl;

        return;
    }

    for(int i=0; i<s.size(); i++){

        char firstChar = s[i];
        arrangements(idx+1,s,o+firstChar);
    }
}

int main(){


    // permute(0,"ABCD","");

    arrangements(0,"ABC","");

    return 0;
}