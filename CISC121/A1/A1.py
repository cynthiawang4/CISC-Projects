"""
   CISC-121 2022F

   This program displays the Vignère encrypted form of some
   text given a key word.
"""
# Declares variables
cypher = []
encrypted = ""

# Prompts user to input the plaintext and key
plaintext = list(input("Enter your plaintext: "))
key = list(input("Enter your key: "))

# Creates the first row of the Vignère Cypher table
for i in range(0, 26):
    cypher.append(chr(i + 65))

for i in range(len(plaintext)):
    # Converts each plaintext character to uppercase,
    # then to a value from 0 to 25 based on its ASCII code
    plaintext[i] = ord(plaintext[i].upper()) - 65
    # Replaces non-letters with the ASCII code for spaces
    if plaintext[i] < 0 or plaintext[i] > 25:
        plaintext[i] = 32

# Removes all spaces in the plaintext
plaintext = [i for i in plaintext if i != 32]

# Converts each key character to uppercase,
# then to a value from 0 to 25 based on its ASCII code
for i in range(len(key)):
    key[i] = ord(key[i].upper()) - 65

# Lines the key up with the plaintext
for i in range(len(plaintext) - len(key)):
    key.append(key[i])

for i in range(len(plaintext)):
    # Rearranges the cypher row based on the key
    temp = cypher[key[i]::] + cypher[:key[i]:]
    # Adds an encrypted character based on the plaintext
    encrypted += temp[plaintext[i]]

print(encrypted)
