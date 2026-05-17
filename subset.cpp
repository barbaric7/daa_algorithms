#include <iostream>
#include <vector>
using namespace std;

class subset_sum{

public:
    vector<vector<int>> result;

    void findSubsets(int arr[], int n, int target, int index, vector<int>& current, int sum) {

    if (sum == target) {

        if (!isDuplicate(current)) {  
            result.push_back(current);
        }
    }

    if (index == n || sum > target) {
        return;
    }

        current.push_back(arr[index]);
        findSubsets(arr, n, target, index + 1, current, sum + arr[index]);

        current.pop_back();

        findSubsets(arr, n, target, index + 1, current, sum);
    }
    int takeValidInput() {
        int value;

        while (true) {
            if (cin >> value) {
                break;
            } else {
                cout << "Invalid input! Enter integer.\n";
                cin.clear();
                cin.ignore(10000, '\n');
            }
        }
        return value;
    }

    bool isDuplicate(vector<int>& current) {
    for (auto& subset : result) {
        if (subset == current)
            return true;
    }
    return false;
    }
};

int main() {

    subset_sum obj;

    while (true) {

        cout << "\n1. Find Subsets with given sum";
        cout << "\n2. Exit";
        cout << "\nEnter your choice: ";

        int choice = obj.takeValidInput();

        switch (choice) {

            case 1: {

                obj.result.clear();

                cout << "Enter size of array: ";
                int n = obj.takeValidInput();

                int arr[n];

                cout << "Enter elements:\n";
                for (int i = 0; i < n; i++) {
                    cout << "Element " << (i + 1) << ": ";
                    arr[i] = obj.takeValidInput();
                }

                cout << "Enter target sum: ";
                int target = obj.takeValidInput();

                vector<int> current;
                obj.findSubsets(arr, n, target, 0, current, 0);

                cout << "\nTotal subsets found: " << obj.result.size() << endl;

                for (auto subset : obj.result) {
                    cout << "[ ";
                    for (int x : subset)
                        cout << x << " ";
                    cout << "]\n";
                }

                break;
            }

            case 2:
                cout << "Exiting...\n";
                return 0;

            default:
                cout << "Invalid choice!\n";
        }
    }
}