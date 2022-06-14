#include <iostream>
using namespace std;

void swap(int* a, int* b)
{
	int t = *a;
	*a = *b;
	*b = t;
}

void printArray(int arr[], int size)
{
	int i;
	for (i = 0; i < size; i++)
		cout << arr[i] << " ";
	cout << endl;
}

int main() {
    int arr[] = {10, 3, 7, 4, 8, 9, 1, 5};
	int n = sizeof(arr) / sizeof(arr[0]);
    int pivot = arr[n - 1]; // pivot
	int l = 0;
    int r = n - 2;
    while (l <= r) {
		while (arr[l] < pivot) l++;
		while (arr[r] >= pivot) r--;
        if (arr[l] > arr[r]) {
            swap(&arr[l], &arr[r]);
			l++;
			r--;
        }
    }
    swap(&arr[l], &arr[n - 1]);
    printArray(arr, n);
}

pivot = 4
l = 2
r = 1