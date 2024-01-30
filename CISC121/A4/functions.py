"""
   CISC-121 2022F

   This program stores the functions that will be used in A4.py.
"""


def clean(string_input):
    """
    This function removes the spaces, digits, and special
    characters from a string, converts all letters to
    uppercase, and strips the string to 10 letters.
    Use: string_output = clean(string_input)

    Parameters:
        string_input (string): a string the user inputs
        that may contain non-letter characters
    Returns:
        string_output (string): a string with non-letters
        removed, all uppercase, and up to 10 letters
    """
    # Converts the string into a list to iterate through
    string_list = list(string_input)

    for a in range(len(string_list)):
        # Converts each character to uppercase, then to ASCII
        string_list[a] = ord(string_list[a].upper())
        # Replaces non-letters with the ASCII code for spaces
        if string_list[a] < 65 or string_list[a] > 90:
            string_list[a] = 32
        # Converts the character back to a letter
        string_list[a] = chr(string_list[a])

    # Removes the spaces
    string_list = [b for b in string_list if b != " "]

    # Keeps the string to 10 letters or under
    if len(string_list) > 10:
        string_list = string_list[:10]

    # Converts the list back into a string
    string_output = "".join(string_list)

    return string_output


def quicksort(string_unsorted):
    """
    This function sorts a string in alphabetical order.
    Use: string_sorted = quicksort(string_unsorted)

    Parameters:
        string_unsorted (string): a string of up to
        10 letters that is not sorted
    Returns:
        string_sorted (string): a string sorted in
        alphabetical order
    """
    # Base case is when the string is up to 1 letter long
    if len(string_unsorted) <= 1:
        string_sorted = string_unsorted
        return string_sorted
    else:
        # Sets the middle element as a tracker
        tracker = string_unsorted[(len(string_unsorted) - 1) // 2]
        # Partitions the string into three substrings
        less, same, more = "", "", ""
        # Puts the elements into the three substrings
        for c in string_unsorted:
            if c < tracker:
                less += c
            elif c > tracker:
                more += c
            else:
                same += c
        # Sorts the string by putting the three substrings in order
        string_sorted = quicksort(less) + same + quicksort(more)
        return string_sorted


def remove_repeat(string_repeat):
    """
    This function removes any repeated letters in a sorted string.
    Use: string_removed = remove_repeat(string_repeat)

    Parameters:
        string_repeat (string): a sorted string with up to
        10 letters
    Returns:
        string_removed (string): a string with repeated
        letters removed
    """
    # Converts the string into a list to iterate through
    repeat_list = list(string_repeat)

    # Replaces repeated letters with spaces
    for d in range(1, len(repeat_list)):
        if repeat_list[d] == repeat_list[d - 1]:
            repeat_list[d - 1] = " "

    # Removes the spaces
    repeat_list = [e for e in repeat_list if e != " "]

    # Converts the list back into a string
    string_removed = "".join(repeat_list)

    return string_removed


def string_permutations(my_word):
    """
    This function finds all permutations of a given string,
    stores them in a list of strings, and returns the list.

    Parameters:
        my_word (string): a sorted string containing at
        most 10 letters
    Returns:
        permutations_list (list): a list of strings of all
        possible permutations
    """
    permutations_list = []

    # Base case is when the word has up to 1 letter left
    if len(my_word) <= 1:
        return [my_word]

    # Calls the function for each letter in the word
    temp_list = string_permutations(my_word[1:])

    # Finds the permutations by adding a letter in each position
    # (front, middle, end) of the previous list entries
    for f in range(len(temp_list)):
        for g in range(len(my_word)):
            temp = temp_list[f][:g] + my_word[0] + temp_list[f][g:]
            permutations_list.append(temp)

    return permutations_list
