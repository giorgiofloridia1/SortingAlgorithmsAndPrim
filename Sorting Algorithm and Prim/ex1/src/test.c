#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include "mergeSort.h"
#include "mergeSort.c"
#include "quickSort.h"
#include "quickSort.c"

int compare_int(const void *a, const void *b) {
    const int *num_a = (const int *)a;
    const int *num_b = (const int *)b;
    if (*num_a < *num_b) {
        return -1;
    } else if (*num_a > *num_b) {
        return 1;
    } else {
        return 0; //equals
    }
}
//test method for merge sort
void test_merge_sort() {
    //Empty array
    int a[] = {};
    merge_sort(a, 0, sizeof(int), compare_int); 

    //One element array
    int b[] = {42};
    merge_sort(b, 1, sizeof(int), compare_int);
    assert(b[0] == 42);

    //Already sorted array
    int c[] = {1, 2, 3, 4, 5};
    merge_sort(c, 5, sizeof(int), compare_int);
    for (int i = 0; i < 5; i++) assert(c[i] == i + 1);

    //Decreasing array
    int d[] = {5, 4, 3, 2, 1};
    merge_sort(d, 5, sizeof(int), compare_int);
    for (int i = 0; i < 5; i++) assert(d[i] == i + 1);
}

//test method for merge sort
void test_quick_sort() {
    //Empty array
    int a[] = {};
    quick_sort(a, 0, sizeof(int), compare_int); 

    //One element array
    int b[] = {42};
    quick_sort(b, 1, sizeof(int), compare_int);
    assert(b[0] == 42);

    //Already sorted array
    int c[] = {1, 2, 3, 4, 5};
    quick_sort(c, 5, sizeof(int), compare_int);
    for (int i = 0; i < 5; i++) assert(c[i] == i + 1);

    //Decreasing array
    int d[] = {5, 4, 3, 2, 1};
    quick_sort(d, 5, sizeof(int), compare_int);
    for (int i = 0; i < 5; i++) assert(d[i] == i + 1);
}

int main() {
    test_merge_sort();
    printf("Merge Sort:DONE\n");

    test_quick_sort();
    printf("Quick Sort:DONE\n");

    printf("\nAll test passed!\n");

    return 0;
}
