"""
   CISC-121 2022F

   This program will skip count by 5 between two inputted
   numbers and print out the numbers. This program assumes
   that the user will input as they are told.
"""
# Continuous loop until user chooses to stop
while True:
    # Declares checker variables
    checker1 = True
    checker2 = True

    # Puts user input into a list
    nums = input("Enter 2 integers between -20 and 20 "
                 "separated by a space: ").split()

    # Converts inputs into integers
    nums[0] = int(nums[0])
    nums[1] = int(nums[1])

    # Checks if inputs are in range
    for i in range(len(nums)):
        if nums[i] >= 20 or nums[i] <= -20:
            if i == 0:
                checker1 = False
            else:
                checker2 = False

    # Prints a statement based on the validity of the inputs
    if checker1 is False and checker2 is False:
        print("Both numbers are invalid")
    elif checker1 is False and checker2 is True:
        print(f"First number ({nums[0]}) is invalid")
    elif checker1 is True and checker2 is False:
        print(f"Second number ({nums[1]}) is invalid")
    else:
        print("Both numbers are valid")

        # Skip counts by 5 from high to low
        if nums[0] > nums[1]:
            i = nums[0] - 5
            while i > nums[1]:
                print(i)
                i -= 5

        # Skip counts by 5 from low to high
        else:
            i = nums[0] + 5
            while i < nums[1]:
                print(i)
                i += 5

    # Gives the user the option to repeat the session
    restart = input("Do you want to restart? (Y/N): ")
    if restart == "Y":
        continue  # Repeats the loop
    else:
        break  # Stops the loop
