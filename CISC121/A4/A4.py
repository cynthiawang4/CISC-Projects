"""
   CISC-121 2022F

   This program finds the permutations of a string of up to
   length 10. The program assumes that the user will input
   a valid string.
"""
import functions

# Prompts user to input a string
message = input("Enter your string: ")

# Cleans the string
message = functions.clean(message)

# Sorts the string in alphabetical order
message = functions.quicksort(message)

# Removes repeated letters
message = functions.remove_repeat(message)

# Finds all permutations of the string
message = functions.string_permutations(message)

print(message)
