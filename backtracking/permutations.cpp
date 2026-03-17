#include <iostream>
using namespace std;


// I want to generate all permutations of [1,2,3]
// which are like (1,2,3) (1,3,2) (2,1,3) (2,3,1) (3,1,2) (3,2,1)

void permutations(int idx,string input,string output){

    if(input.size() == 0){
        cout<< output << endl;
        return;
    }
    for(int i=0; i<input.size(); i++){

        char first = input[i];
        string newStr = input.substr(0,i) + input.substr(i+1);
        permutations(idx+1,newStr,output+first);
    }

    
}

int main(){

    permutations(0,"ABC","");


    return 0;
}