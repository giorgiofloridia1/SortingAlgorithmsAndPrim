#include <stdio.h>
#include <assert.h>
#include "edit_distance.h"

void test_edit_distance_empty_strings() {
    const char *s1 = "";
    const char *s2 = "";
    int distance = edit_distance(s1, s2);
    assert(distance == 0);
}

void test_edit_distance_first_string_empty() {
    const char *s1 = "";
    const char *s2 = "empty";
    int distance = edit_distance(s1, s2);
    assert(distance == 5);
}

void test_edit_distance_second_string_empty() {
    const char *s1 = "empty";
    const char *s2 = "";
    int distance = edit_distance(s1, s2);
    assert(distance == 5);
}

void test_edit_distance_equal_strings() {
    const char *s1 = "equal";
    const char *s2 = "equal";
    int distance = edit_distance(s1, s2);
    assert(distance == 0);
}

void test_edit_distance_single_insertion() {
    const char *s1 = "tes";
    const char *s2 = "test";
    int distance = edit_distance(s1, s2);
    assert(distance == 1);
}

void test_edit_distance_single_deletion() {
    const char *s1 = "test";
    const char *s2 = "tes";
    int distance = edit_distance(s1, s2);
    assert(distance == 1);
}

void test_edit_distance_substitution() {
    const char *s1 = "window";
    const char *s2 = "witch";
    int distance = edit_distance(s1, s2);
    assert(distance == 7);
}

void test_edit_distance_dyn_empty_strings() {
    const char *s1 = "";
    const char *s2 = "";
    int distance = edit_distance_dyn(s1, s2);
    assert(distance == 0);
}

void test_edit_distance_dyn_first_string_empty() {
    const char *s1 = "";
    const char *s2 = "empty";
    int distance = edit_distance_dyn(s1, s2);
    assert(distance == 5);
}

void test_edit_distance_dyn_second_string_empty() {
    const char *s1 = "empty";
    const char *s2 = "";
    int distance = edit_distance_dyn(s1, s2);
    assert(distance == 5);
}

void test_edit_distance_dyn_equal_strings() {
    const char *s1 = "equals";
    const char *s2 = "equals";
    int distance = edit_distance_dyn(s1, s2);
    assert(distance == 0);
}

void test_edit_distance_dyn_single_insertion() {
    const char *s1 = "tes";
    const char *s2 = "test";
    int distance = edit_distance_dyn(s1, s2);
    assert(distance == 1);
}

void test_edit_distance_dyn_single_deletion() {
    const char *s1 = "test";
    const char *s2 = "tes";
    int distance = edit_distance_dyn(s1, s2);
    assert(distance == 1);
}

void test_edit_distance_dyn_substitution() {
    const char *s1 = "window";
    const char *s2 = "witch";
    int distance = edit_distance_dyn(s1, s2);
    assert(distance == 7);
}


int main() {
    //tests for edit distance
    test_edit_distance_empty_strings();
    test_edit_distance_first_string_empty();
    test_edit_distance_second_string_empty();
    test_edit_distance_equal_strings();
    test_edit_distance_single_insertion();
    test_edit_distance_single_deletion();
    test_edit_distance_substitution();
    printf("Tests for edit distance: all passed\n");
    //tests for edit distance dyn
    test_edit_distance_dyn_empty_strings();
    test_edit_distance_dyn_first_string_empty();
    test_edit_distance_dyn_second_string_empty();
    test_edit_distance_dyn_equal_strings();
    test_edit_distance_dyn_single_insertion();
    test_edit_distance_dyn_single_deletion();
    test_edit_distance_dyn_substitution();
    printf("Tests for edit distance dyn: all passed");

    printf("\nALL TESTS DONE\n");

    return 0;
}
