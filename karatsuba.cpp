#include <iostream>
#include <cmath>
using namespace std;

long long multiply(long long x, long long y) {

    if (x < 10 || y < 10) {
        return x * y;
    }

    int n = max(to_string(x).length(), to_string(y).length());
    int m = n / 2;

    long long power = (long long) pow(10, m);

    long long xh = x / power;
    long long xl = x % power;

    long long yh = y / power;
    long long yl = y % power;

    long long z2 = multiply(xh, yh);
    long long z0 = multiply(xl, yl);
    long long z1 = multiply(xh + xl, yh + yl);

    return z2 * (long long) pow(10, 2 * m)
           + (z1 - z2 - z0) * power
           + z0;
}
long long getValidInput(string message) {
    long long value;

    while (true) {
        cout << message;
        cin >> value;

        if (cin.fail() || cin.peek() != '\n') {
            cout << "Invalid input! Enter an integer only.\n";

            cin.clear();
            cin.ignore(10000, '\n');
        } else {
            return value;
        }
    }
}

int main() {

    long long num1 = getValidInput("Enter first number: ");
    long long num2 = getValidInput("Enter second number: ");

    long long result = multiply(num1, num2);

    cout << "Result = " << result << endl;

    return 0;
}