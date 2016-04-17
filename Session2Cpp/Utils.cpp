/*
 * Utils.cpp
 *
 *  Created on: Sep 27, 2015
 *      Author: Mario_Contreras
 */
#include "Utils.h"
#include <cstdlib>
#include <iostream>

using namespace std;

int* createArray(int N, int min, int max) {
	int* array = new int[N];

	int bound = max - min + 1;
	for(int i = 0; i < N; i ++) {
//		array[i] = min + (int) ((bound) * (float) rand() / RAND_MAX);
		array[i] = rand() % bound + min;
	}

	return array;
}

void printArray(int* array, int N) {
	for(int i = 0; i < N; i ++) {
		cout << array[i] << ", ";
	}
	cout << endl;
}

void swap(int &a, int *b) {
	int tmp = a;
	a = *b;
	*b = tmp;
}

bool isSorted(int* array, int N) {
	for(int i = 0; i < N - 1; i ++) {
		if(array[i] > array[i + 1]) return false;
	}
	return true;
}

void selectionSort(int* array, int N){
	for (int i = 0; i < N - 1; i++){
		int minIndex = i;
		for (int j = i + 1; j < N; j++){
			if(array[minIndex] > array[j])
			{
				minIndex = j;
			}
		}
		if (minIndex != i)
		{
			swap(array[minIndex], &array[i]);
		}
	}
}
