#include <bits/stdc++.h>
using namespace std;

int main() {
    int max = 100;
    int lg2[max];
    lg2[1] = 0;
    for (int i = 2; i < 100; i++) {
        lg2[i] = lg2[i/2] + 1;
    }

    for (int i = 1; i < 100; i++) {
        cout << i << " " << lg2[i] << endl;
    }
}