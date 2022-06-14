#include <iostream>

using namespace std;

int main() {
    int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    int len = sizeof(arr)/sizeof(arr[0]);
    int check = 0;
    int row = len/3;
    for (int i = 0; i < len; i++) {
        cout << arr[i];
        if (i % 3 == 2 && check < row - 1) {
            cout << endl;
            check++;
        }
    }
}