"""
    This program will print the number of anagrams of
    a given string. The program accepts letter and
    non-letter characters except spaces, which are removed.
"""
# Define imports
import math

# Ask user for string and remove spaces
message = input("Enter your string: ")
message = [a for a in message if a != " "]

# Define variables
anagrams = math.factorial(len(message))
repeats = []

# Count the number of repeats
for i in range(0, len(message)):
    count = 1
    for j in range(i + 1, len(message)):
        if message[i] == message[j] and message[i] != ' ':
            count += 1
            message[j] = ' '
    if count > 1:
        repeats.append(count)

# Calculate the number of anagrams using length! divided
# by the factorial of each repeat
for k in range(0, len(repeats)):
    anagrams //= math.factorial(repeats[k])

print(anagrams)
