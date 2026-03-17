#include <iostream>
#include <vector>
using namespace std;

vector<int> curr = {1,2,3};

void solve(int index, vector<int>& temp)
{
    if(index == 3)
    {
        for(int x : temp) cout << x << " ";
        cout << endl;
        return;
    }

    // take
    temp.push_back(curr[index]);
    solve(index+1, temp);

    // undo
    temp.pop_back();

    // skip
    solve(index+1, temp);
}

int main()
{
    vector<int> temp;
    solve(0, temp);
}