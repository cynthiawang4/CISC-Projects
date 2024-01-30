"""
    CISC-121 2022F

    This program asks the user to input patient information and
    schedules the patients according to their randomly
    generated priority level. The program assumes the user inputs
    as told.
"""
# Imports
import random
import er_class

# Variables
checker = "Y"
sequence = 0
time_appoint = 0

# Objects
er_sim = er_class.ER()

# Menu allowing user to enter patients
while checker != "N":
    # Collecting patient information
    name = input("Enter patient name: ")
    sequence += 1
    time = input("Enter time of arrival: ")
    er_name = name + str(sequence)
    priority = random.randint(0, 10)

    # Creating patient object to put in patients list
    patient = er_class.Patient(name, sequence, time, er_name, priority)
    er_sim.arrive(patient)

    # Allows user to keep inputting patients or stop
    checker = input("Enter another patient? (Y/N) ")

# For easier readability
print()

# Schedules the patients using methods from imported class
for i in range(len(er_sim.patients)):
    # Accounts for if time goes past 24 hours
    if time_appoint == 24:
        time_appoint = 0
    er_sim.schedule(time_appoint)
    time_appoint += 1
