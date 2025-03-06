#ifndef mergeSort_h
#define mergeSort_h

void merge_sort(void *base, size_t nitems, size_t size, int (*compar)(const void *, const void *));

#endif