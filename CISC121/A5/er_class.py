"""
    CISC-121 2022F

    This program contains the classes that will be used in A5.

    Classes:
        Patient: a class to represent a patient.
        ER: a class to represent a system of patients.
"""


class Patient:
    """
    A class to represent a patient.

    Attributes:
        name(str): name of the patient
        sequence(int): sequence number of patient
        time(str): arrival time of patient
        er_name(str): patient name concatenated with sequence number
        priority(int): priority level of patient
    """
    def __init__(self, name, sequence, time, er_name, priority):
        """
        Constructs the attributes for the patient object.

        Parameters:
            name(str): name of the patient
            sequence(int): sequence number of patient
            time(str): arrival time of patient
            er_name(str): patient name concatenated with sequence number
            priority(int): priority level of patient
        """
        self.name = name
        self.sequence = sequence
        self.time = time
        self.er_name = er_name
        self.priority = priority


class ER:
    """
    A class to represent a system of patients.

    Attributes:
        patients(list): list of patients arrived in the ER

    Methods:
        arrive(patient): adds a new patient object to the patients list
        announce(): finds the patient with the current highest priority
        schedule(time_appoint): schedules the highest priority patient
    """
    def __init__(self):
        """
        Constructs the attributes for the patient object.
        """
        self.patients = []

    def arrive(self, patient):
        """
        Adds a new patient object to the patients list.

        Parameters:
            patient(object): an object from the Patient class
        Returns:
            None
        """
        self.patients.append(patient)

    def announce(self):
        """
        Finds the patient with the current highest priority and
        prints the information.

        Returns:
            patient_priority(object): the patient with the highest priority
        """
        current_min = self.patients[0].priority
        patient_priority = self.patients[0]
        for patient in self.patients:
            if patient.priority < current_min:
                current_min = patient.priority
                patient_priority = patient
        print(f"The highest priority patient is currently {patient_priority.er_name}"
              f" with priority {patient_priority.priority}.")
        return patient_priority

    def schedule(self, time_appoint):
        """
        Schedules the highest priority patient by first finding
        the highest priority patient using the announce() method and
        then printing out the appointment time and information. The
        scheduled patient is then removed from the patients list.

        Parameters:
            time_appoint(int): the hour of the appointment
        Returns:
            None
        """
        patient_priority = self.announce()
        print(f"{patient_priority.er_name} is scheduled for {time_appoint}:00.")
        self.patients.remove(patient_priority)
