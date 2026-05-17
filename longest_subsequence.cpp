#include <iostream>
#include <set>
#include <string>
using namespace std;

int getMaxLength(set<string> s) {

    int maxLen = 0;

    for (auto str : s) {
        if (str.length() > maxLen) {
            maxLen = str.length();
        }
    }

    return maxLen;
}

set<string> lcsAll(string str1, string str2, int n, int m) {

    set<string> result;

    if (n == 0 || m == 0) {
        result.insert("");
        return result;
    }

    if (str1[n - 1] == str2[m - 1]) {

        set<string> temp = lcsAll(str1, str2, n - 1, m - 1);

        for (auto s : temp) {
            result.insert(s + str1[n - 1]);
        }

    } 
    else {

        set<string> left = lcsAll(str1, str2, n - 1, m);
        set<string> right = lcsAll(str1, str2, n, m - 1);

        int leftLen = getMaxLength(left);
        int rightLen = getMaxLength(right);

        if (leftLen > rightLen) {
            result.insert(left.begin(), left.end());
        }
        else if (rightLen > leftLen) {
            result.insert(right.begin(), right.end());
        }
        else {
            result.insert(left.begin(), left.end());
            result.insert(right.begin(), right.end());
        }
    }

    return result;
}


string takeValidString(string message) {

    string input;

    while (true) {

        cout << message;
        getline(cin, input);

        if (input.empty()) {
            cout << "Invalid input! String cannot be empty.\n";
            continue;
        }

        bool onlySpaces = true;
        bool valid = true;

        for (char ch : input) {

            if (ch != ' ') {
                onlySpaces = false;
            }

            if (!isalpha(ch) && ch != ' ') {
                valid = false;
                break;
            }
        }

        if (onlySpaces) {
            cout << "Invalid input! String cannot contain only spaces.\n";
        }
        else if (!valid) {
            cout << "Invalid input! Only alphabets are allowed.\n";
        }
        else {
            return input;
        }
    }
}
int main() {

    string str1 = takeValidString("Enter first string: ");
    string str2 = takeValidString("Enter second string: ");

    int n = str1.length();
    int m = str2.length();

    set<string> result = lcsAll(str1, str2, n, m);

    int maxLen = getMaxLength(result);

    cout << "\nAll Longest Common Subsequences:\n";

    for (auto s : result) {

        if (s.length() == maxLen) {
            cout << s << endl;
        }
    }

    cout << "Length of LCS: " << maxLen << endl;

    return 0;
}