#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "edit_distance.h"

#define MAX_LENGTH 600
#define MAX_WORDS 500000
#define MAX_WORDS_SUGGESTED 4 

//remove punctuation
void remove_punctuation(char *s) {
    int i = 0, j = 0;
    char punctuation[] = " .,;:!?'\"";
    while (s[i]) {
        int is_punctuation = 0;
        for (int k = 0; punctuation[k]; k++) {
            if (s[i] == punctuation[k]) {
                is_punctuation = 1;
                s[j++] = ' ';
                break;
            }
        }
        if (!is_punctuation) {
            s[j++] = s[i];
        }
        i++;
    }
    s[j] = '\0';
}

//fids the most similar words in the dictionary
void most_similar_words(const char* word, char* dictionary[], int dictionary_size) {
    char* similar_words[MAX_WORDS_SUGGESTED] = {NULL};
    int min_distances[MAX_WORDS_SUGGESTED] = {MAX_LENGTH};
    for (int i = 0; i < dictionary_size; i++) {
        int distance = edit_distance_dyn(word, dictionary[i]); 
        for (int k = 0; k < MAX_WORDS_SUGGESTED; k++) {
            if (similar_words[k] == NULL || distance < min_distances[k]) {
                for (int l = MAX_WORDS_SUGGESTED - 1; l > k; l--) {
                    similar_words[l] = similar_words[l - 1];
                    min_distances[l] = min_distances[l - 1];
                }
                similar_words[k] = dictionary[i];
                min_distances[k] = distance;
                break;
            }
        }
    }
    FILE *output_file = fopen("./data/output.txt", "a");
    if (output_file == NULL) {
        return;
    }
    fprintf(output_file, "******************************************************\n");
    fprintf(output_file, "Word: %s\n", word);
    for (int i = 0; i < MAX_WORDS_SUGGESTED; i++) {
        if (similar_words[i] != NULL) {
            fprintf(output_file, "Suggested word %d: %s (Edit distance: %d)\n", i + 1, similar_words[i], min_distances[i]);
        }
    }
    fclose(output_file);
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        fprintf(stderr, "How to use: %s <dictionary> <sentence to correct>\n", argv[0]);
        return EXIT_FAILURE;
    }
    char *dictionary_filepath = argv[1];
    char *correctme_filepath = argv[2];
    FILE *dictionary_file = fopen(dictionary_filepath, "r");
    FILE *correctme_file = fopen(correctme_filepath, "r");
    FILE *output_file = fopen("./data/output.txt", "w");
    if (!dictionary_file) {
        fprintf(stderr, "Error in opening the dictionary.\n");
        return 1;
    }
    if (correctme_file == NULL) {
        fprintf(stderr, "Error in opening the correctme.\n");
        return 1;
    }

    char* dictionary[MAX_WORDS];
    int dictionary_size = 0;    
    char w[MAX_LENGTH];
    while (fgets(w, MAX_LENGTH, dictionary_file) != NULL && dictionary_size < MAX_WORDS) {
        if (w[strlen(w) - 1] == '\n') {
            w[strlen(w) - 1] = '\0';
        }
        dictionary[dictionary_size] = strdup(w);
        dictionary_size++;
    }    
    clock_t start_clock = clock();
    while (fgets(w, MAX_LENGTH, correctme_file) != NULL) {
        if (w[strlen(w) - 1] == '\n') {
            w[strlen(w) - 1] = '\0';
        }
        remove_punctuation(w);
        char *token = strtok(w, " ");
        while (token != NULL) {
            most_similar_words(token, dictionary, dictionary_size);
            token = strtok(NULL, " ");
        }
    }
    clock_t end_clock = clock();
    double total_time = ((double)(end_clock - start_clock)) / CLOCKS_PER_SEC;
    printf("Reading time: %.4f seconds\n", total_time);
    fclose(correctme_file);
    fclose(dictionary_file);
    for (int i = 0; i < dictionary_size; i++) {
        free(dictionary[i]);
    }
    return EXIT_SUCCESS;
}
