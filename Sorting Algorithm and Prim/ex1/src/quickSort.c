#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stddef.h>

/*QUICK SORT*/
//this method split the array for quicksort algorithm and moving pivot element to its correct position
static size_t pivotDivide(void *base, size_t low, size_t high, size_t size, int (*compar)(const void *, const void *)) {
    void *pivot = (char *)base + high * size;
    size_t i = low - 1;

    for (size_t j = low; j <= high - 1; j++) {
        if (compar((char *)base + j * size, pivot) < 0) {
            i++;
            void *temp = malloc(size);
            if (!temp) {
                fprintf(stderr, "error during memory allocation\n");
                exit(EXIT_FAILURE);
            }
            memcpy(temp, (char *)base + i * size, size);
            memcpy((char *)base + i * size, (char *)base + j * size, size);
            memcpy((char *)base + j * size, temp, size);
            free(temp);
        }
    }
    void *temp = malloc(size);
    if (!temp) {
        fprintf(stderr, "error during memory allocation\n");
        exit(EXIT_FAILURE);
    }
    memcpy(temp, (char *)base + (i + 1) * size, size);
    memcpy((char *)base + (i + 1) * size, (char *)base + high * size, size);
    memcpy((char *)base + high * size, temp, size);
    free(temp);

    return i + 1;
}

//recursive method for quicksort
static void quick_sort_recursive(void *base, size_t low, size_t high, size_t size, int (*compar)(const void *, const void *)) {
    if (low < high) {
        size_t pi = pivotDivide(base, low, high, size, compar);
        if (pi > 0)
            quick_sort_recursive(base, low, pi - 1, size, compar);
        if (pi < high)
            quick_sort_recursive(base, pi + 1, high, size, compar);
    }
}

//landing method for the quicksort method
void quick_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *)) {
    if (!base || !compar) {
        exit(EXIT_FAILURE);
    }
    if (nitems > 1)
        quick_sort_recursive(base, 0, nitems - 1, size, compar);
}