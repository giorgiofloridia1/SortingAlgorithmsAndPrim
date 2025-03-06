#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "mergeSort.h"
#include "quickSort.h"

#define MAX_CHAR_NUMBER 30

typedef struct {
    int id;
    char string_data[MAX_CHAR_NUMBER];
    int int_data;
    double double_data;
} Record;

//method for comparing record identifiers
int compare_id(const void *a, const void *b) {
    const Record *record_a = (const Record *)a;
    const Record *record_b = (const Record *)b;
    return record_a->id - record_b->id;
}

//method for comparing record String
int compare_string_data(const void *a, const void *b) {
    const Record *record_a = (const Record *)a;
    const Record *record_b = (const Record *)b;
    return strcasecmp(record_a->string_data, record_b->string_data);
}


//method for comparing record integer
int compare_int_data(const void *a, const void *b) {
    const Record *record_a = (const Record *)a;
    const Record *record_b = (const Record *)b;
    return record_a->int_data - record_b->int_data;
}

//method for comparing record double
int compare_double_data(const void *a, const void *b) {
    const Record *record_a = (const Record *)a;
    const Record *record_b = (const Record *)b;
    if (record_a->double_data < record_b->double_data) return -1;
    if (record_a->double_data > record_b->double_data) return 1;
    return 0;
}

//Records counter function
int records_counter(FILE *i_file) {
    int count = 0;
    char line[1000];
    while (fgets(line, sizeof(line), i_file) != NULL) {
        count++;
    }
    rewind(i_file);
    return count;
}


//records_sorter method
void records_sorter(FILE *i_file, FILE *o_file, size_t field, size_t algorithm) {
    int total_records = records_counter(i_file);
    
    Record *records = malloc(total_records * sizeof(Record));
    if (records == NULL) {
        exit(EXIT_FAILURE);
    }
    clock_t start_time = clock();
    total_records = 0;
    char line[1000];
    while (fgets(line, sizeof(line), i_file) != NULL) {
        Record record;
        sscanf(line, "%d,%[^,],%d,%lf", &record.id, record.string_data, &record.int_data, &record.double_data);
        records[total_records++] = record;
    }
    clock_t end_time = clock();
    double reading_time = ((double)(end_time - start_time)) / CLOCKS_PER_SEC;
    printf("Reading time: %.2f seconds\n", reading_time);

    //field selection
    int (*compar)(const void *, const void *);
    switch (field) {
        case 1:
            compar = compare_string_data;
            break;
        case 2:
            compar = compare_int_data;
            break;
        case 3:
            compar = compare_double_data;
            break;
        default:
            fprintf(stderr, "The input is invalid, please choose field 1, 2, or 3.\n");
            exit(EXIT_FAILURE);
    }

    //algorithm selection
    void (*sorting_algorithm)(void *, size_t, size_t, int (*)(const void *, const void *));
    switch (algorithm) {
        case 1:
            sorting_algorithm = merge_sort;
            break;
        case 2:
            sorting_algorithm = quick_sort;
            break;
        default:
            fprintf(stderr, "The input is invalid, please choose algorithm 1 (merge sort) or 2 (quick sort)");
            exit(EXIT_FAILURE);
    }

    //calculate the sorting time
    start_time = clock();
    sorting_algorithm(records, total_records, sizeof(Record), compar);
    end_time = clock();
    double sorting_time = ((double)(end_time - start_time)) / CLOCKS_PER_SEC;
    printf("Sorting time: %.2f seconds\n", sorting_time);


    //calculate the writing time and total time
    start_time = clock();
    for (int i = 0; i < total_records; i++) {
        fprintf(o_file, "%d,%s,%d,%.6f\n", records[i].id, records[i].string_data, records[i].int_data, records[i].double_data);
    }
    end_time = clock();
    double writing_time = ((double)(end_time - start_time)) / CLOCKS_PER_SEC;
    printf("Writing time: %.2f seconds\n\n", writing_time);

    printf("Total time: %.2f seconds\n", reading_time + sorting_time + writing_time);

    free(records);
}

int main(int argc, char *argv[]) {
    if (argc != 5) {
        fprintf(stderr, "How to use: %s <input_file> <output_file> <record_to_sort> <sorting_algorithm>\n", argv[0]);
        return EXIT_FAILURE;
    }

    char *input_file = argv[1];
    char *output_file = argv[2];
    size_t field = atoi(argv[3]);
    size_t algorithm = atoi(argv[4]);

    FILE *i_file = fopen(input_file, "r");
    if (!i_file) {
        fprintf(stderr, "Error opening the input file. %s\n", input_file);
        return EXIT_FAILURE;
    }

    FILE *o_file = fopen(output_file, "w");
    if (!o_file) {
        fprintf(stderr, "Error opening the output file. %s\n", output_file);
        fclose(i_file); 
        return EXIT_FAILURE;
    }

    records_sorter(i_file, o_file, field, algorithm);

    fclose(i_file);
    fclose(o_file);

    return EXIT_SUCCESS;
}