#include <iostream>
#include <vector>
#include <thread>
using namespace std;

class arr{

    private:

        vector <int> array;
        int max = array[0];
        int min = array[0];


    public:

        void initialize(){
            for(int i=0; i<100; i++){
                array.push_back(rand());
            }
        }

        void print(){
            for(int i=0; i<100; i++){
                cout<<array[i]<<endl;
            }
        }

        void compare(int start,int end){
            for(int i=start; i<end; i++){

                if(array[i]>max){
                    max = array[i];
                }

                if(array[i]<min){
                    min = array[i];
                }
                
            }

            cout<<max<<" "<<min<<endl;
        }

        void result(){
            cout<<"The max element is :"<<max<<endl;
            cout<<"The min element is :"<<max<<endl;
        }
};

int main(){

    // arr a1;

    // a1.initialize();
    // a1.print();
    // thread t1();

    // a1.compare(200, 400);
    // a1.compare(400, 600);
    // a1.compare(600, 800);
    // a1.compare(800, 1000);




    return 0;
}