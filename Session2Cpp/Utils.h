/*
 * Utils.h
 *
 *  Created on: Sep 27, 2015
 *      Author: Mario_Contreras
 */

#ifndef UTILS_H_
#define UTILS_H_

int* createArray(int N, int min, int max);
void printArray(int* array, int N);
void swap(int &a, int *b);
bool isSorted(int* array, int N);
void selectionSort(int* array, int N);

#endif /* UTILS_H_ */
