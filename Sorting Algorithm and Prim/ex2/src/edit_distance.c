#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stddef.h>
#include <limits.h>

//finds the minum number
int min(int a, int b){
    int min = a;
    if(b < min)
        min = b;
    return min;
}

//entry point for edit distance
int edit_distance(const char *s1, const char* s2){
    if(strlen(s1) == 0)
        return strlen(s2);
    else if(strlen(s2) == 0)
        return strlen(s1);

    int dist_no_change = 1000000;
    if(*s1 == *s2) 
        dist_no_change = edit_distance(s1 + 1, s2 + 1);

    int dist_canc = 1 + edit_distance(s1, s2 + 1);
    int dist_ins = 1 + edit_distance(s1 + 1, s2);
    
    return min(dist_no_change, min(dist_canc, dist_ins));
}

//supporter method
int edit_distance_dyn_supporter(const char *s1, const char *s2, int **buffer, int len1, int len2){
    if(len1 == 0)
        return len2;
    else if(len2 == 0)
        return len1;

    if (buffer[len1][len2] != -1) 
        return buffer[len1][len2];  
    
    if (s1[0] == s2[0]) 
        buffer[len1][len2] = edit_distance_dyn_supporter(s1 + 1, s2 + 1, buffer, len1 - 1, len2 - 1);
    else{
        int d_no_change = 1000000;
        int d_canc = 1 + edit_distance_dyn_supporter(s1, s2 + 1, buffer, len1, len2 - 1);
        int d_ins = 1 + edit_distance_dyn_supporter(s1 + 1, s2, buffer, len1 - 1, len2);
        
        buffer[len1][len2] = min(d_no_change, min(d_canc, d_ins));
    }

    return buffer[len1][len2];
 
}

//entry point for edit distance dyn
int edit_distance_dyn(const char *s1, const char *s2) {
    int len1 = strlen(s1);
    int len2 = strlen(s2);

    int **buffer = (int **)malloc((len1 + 1) * sizeof(int *));

    for (int i = 0; i <= len1; i++) {
        buffer[i] = (int *)malloc((len2 + 1) * sizeof(int));
        for (int j = 0; j <= len2; j++) {
          buffer[i][j] = -1;
        }
    }

    //helper method
    int distance = edit_distance_dyn_supporter(s1, s2, buffer, len1, len2);

    for (int i = 0; i <= len1; i++) {
        free(buffer[i]);
    }
    free(buffer);

    return distance;
}