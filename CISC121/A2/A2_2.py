"""
   CISC-121 2022F

   This program will count the number of friends each person
   has in the file "friendship.txt". This program assumes that
   the file exists and follows the format of the friendship.txt
   file examples.
"""
# Defines variables
counter = 0
friends_list = []

# Opens the file and puts its contents in a 2D list
my_file = open("friendship.txt", "r")
line = my_file.readline()
while line != "":
    friends_list.append(line.split())
    line = my_file.readline()
my_file.close()

# Counts the number of friends and prints the results
for i in range(0, 2):
    for j in range(len(friends_list)):
        friend = friends_list[j][i]
        if friend != " ":
            for k in range(len(friends_list)):
                # Replaces friend with a space
                # after counting the friend
                if friend in friends_list[k]:
                    counter += 1
                    if friend == friends_list[k][0]:
                        friends_list[k][0] = " "
                    else:
                        friends_list[k][1] = " "

            # Accounts for if friends need to be plural
            if counter == 1:
                plural = "friend."
            else:
                plural = "friends."
            print(f"{friend} has {counter} {plural}")
            counter = 0   # Resets counter
