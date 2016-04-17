/*
 * Main.cpp
 *
 *  Created on: Sep 27, 2015
 *      Author: Mario_Contreras
 */
#include "Utils.h"
#include <ctime>
#include <cstdlib>
#include <iostream>

using namespace std;

int main() {
	srand(time(NULL));
	int N = 10000;
	int* a1 = createArray(N, -N, N);
	printArray(a1, N);

	int x = 5, y = 7;
	swap(x, &y);
	cout << x << ", " << y << endl;

	cout << isSorted(a1, N) << endl;

	int a2[] = {5, 8, 15, 20};
	cout << isSorted(a2, 4) << endl;

	time_t start = clock();
	selectionSort(a1, N);
	time_t end = clock();
	cout << "Duración: " << difftime(end, start) << endl;

	//printArray(a1, N);
	cout << "Esta arreglado? " << isSorted(a1, N)<< endl;
	return 0;
}
