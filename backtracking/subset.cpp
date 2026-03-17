#include <iostream>
#include <vector>
#include <string>

using namespace std;

int factorial(int n){

        if(n==0 || n==1){
            return 1;
        }

        return n*factorial(n-1);
    };

void print(int n){
    if(n<1){
        return;
    }

    printf("%d\n",n);
    n--;
    print(n);
}

void printRev(int n){

    if(n<1){
        return;
    }

    printRev(n-1);
    printf("%d\n",n);
}

void printBinary(int idx, string current){

    if(idx == 3){

        cout << current << endl;
        return;
    }

    printBinary(idx+1,current+"0");
    printBinary(idx+1,current +"1");

}


int main(){


    // int result = factorial(5);
    // printf("%d",result);

    // print(5);

    // printRev(5);

    printBinary(0,"");



    return 0;
}