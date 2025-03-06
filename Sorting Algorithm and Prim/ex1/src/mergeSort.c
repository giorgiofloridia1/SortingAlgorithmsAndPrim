#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stddef.h>

/*MERGE SORT*/
//merge sort inner method
static void merge(void *base, size_t left, size_t middle, size_t right, size_t size, int (*compar)(const void *, const void *)) {
    size_t i, j, k;
    size_t n1 = middle - left + 1;
    size_t n2 = right - middle;

    void *L = malloc(n1 * size);
    void *R = malloc(n2 * size);

    if (!L || !R) {
        fprintf(stderr, "error during memory allocation\n");
        exit(EXIT_FAILURE);
    }

    char *left_ptr = (char *)base + left * size;
    char *right_ptr = (char *)base + (middle + 1) * size;
    for (i = 0; i < n1; i++)
        memcpy((char *)L + i * size, left_ptr + i * size, size);
    for (j = 0; j < n2; j++)
        memcpy((char *)R + j * size, right_ptr + j * size, size);

    i = 0;
    j = 0;
    k = left;
    while (i < n1 && j < n2) {
        if (compar((char *)L + i * size, (char *)R + j * size) <= 0) {
            memcpy((char *)base + k * size, (char *)L + i * size, size);
            i++;
        } else {
            memcpy((char *)base + k * size, (char *)R + j * size, size);
            j++;
        }
        k++;
    }

    while (i < n1) {
        memcpy((char *)base + k * size, (char *)L + i * size, size);
        i++;
        k++;
    }
    
    while (j < n2) {
        memcpy((char *)base + k * size, (char *)R + j * size, size);
        j++;
        k++;
    }

    free(L);
    free(R);
}

//Recursive Method for merge sort
static void merge_sort_recursive(void *base, size_t left, size_t right, size_t size, int (*compar)(const void *, const void *)) {
    if (left < right) {
        size_t middle = left + (right - left) / 2;
        merge_sort_recursive(base, left, middle, size, compar);
        merge_sort_recursive(base, middle + 1, right, size, compar);
        merge(base, left, middle, right, size, compar);
    }
}

//Landing method for the merge sort 
void merge_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *)) {
    if (!base || !compar) {
        exit(EXIT_FAILURE);
    }
    if (nitems > 1)
        merge_sort_recursive(base, 0, nitems - 1, size, compar);
}