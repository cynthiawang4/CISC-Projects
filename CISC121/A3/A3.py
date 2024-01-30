"""
    CISC-121 2022F

    This program will output one zero of a polynomial function
    using the bisection method. This program assumes that the
    user will input as they are told, that functions will
    have a degree of 1 or larger, and that the inputted function
    will have a zero between the inputted values.
"""


def function_list(f_input):
    """
    -------------------------------------------------------
    Puts the coefficients of a polynomial function into a list.
    Use: f_list = function_list(f_input)
    -------------------------------------------------------
    Parameters:
        f_input - a polynomial function (string)
    Return:
        f_list - the coefficients of a polynomial function (list)
    -------------------------------------------------------
    """
    # Variables
    f_list = []
    n = len(f_input) - 1
    degree_list = []
    new_degree = 0

    # Puts the degrees of the polynomial into a list
    for a in range(n):
        degree = ""
        # The degree will be between a ^ and a + or -
        if f_input[a] == "^":
            for b in range(a, n):
                if f_input[b] == "+" or f_input[b] == "-":
                    sign_position = b
                    break
                else:
                    sign_position = n + 1
            # Accounts for if the degree is more than one digit
            for c in range(a + 1, sign_position):
                degree += f_input[c]
            degree_list.append(int(degree))

    # The polynomial is at least degree 1 and may contain a constant
    for d in range(0, 2):
        degree_list.append(1)

    # Makes a list of coefficients containing 0s
    for e in range(degree_list[0] + 1):
        f_list.append(0)

    # Replaces the 0s with the actual coefficients
    sign_position = -1
    for f in range(n):
        temp = ""
        # The coefficient will be between a + or - and an x
        if f_input[f] == "x":
            position = len(f_list) - degree_list[new_degree] - 1
            # Accounts for if the first coefficient is 1
            if f == 0:
                f_list[0] = 1
            # Accounts for if a coefficient in general is 1
            elif f - sign_position == 1:
                temp = f_input[sign_position] + "1"
                f_list[position] = int(temp)
            else:
                if sign_position == -1:
                    sign_position = 0
                # Accounts for if the coefficient is multiple digits
                for g in range(sign_position, f):
                    temp += f_input[g]
                f_list[position] = int(temp)
            new_degree += 1

            # Adds the constant to the list
            if degree_list[new_degree] == 1:
                const = ""
                # Checks if there is a constant
                for h in range(f, n):
                    if f_input[h] == "+" or f_input[h] == "-":
                        sign_position = h
                if sign_position > f:
                    # Accounts for if the constant is multiple digits
                    for y in range(sign_position, n + 1):
                        const += f_input[y]
                    f_list[len(f_list) - 1] = int(const)
        elif f_input[f] == "+" or f_input[f] == "-":
            sign_position = f
    return f_list


def bisection_zero(f_x, a_0, b_0):
    """
    -------------------------------------------------------
    Finds one zero of the polynomial by using the bisection method.
    Use: zero_f = bisection_zero(f_x,a_0,b_0)
    -------------------------------------------------------
    Parameters:
        f_x - a polynomial function (list)
        a_0 - a test point with f(a_0) < 0 (float)
        b_0 - a test point with f(b_0) > 0 (float)
    Returns:
        zero_f - a zero of the polynomial f(zero_f) = 0 (float)
    -------------------------------------------------------
    """
    # Variables
    f_val = 1
    n = len(f_x) - 1

    # Finds a zero with the bisection method
    # Stops when the y value of the midpoint is 0
    while f_val != 0:
        # Finds the midpoint
        m_0 = (a_0 + b_0)/2

        # Calculates the y value of the midpoint
        f_val = f_x[0] * m_0 ** n
        for i in range(1, n + 1):
            f_val += f_x[i] * m_0 ** (n - i)

        # Replaces one of the test points with the midpoint
        # Or finds the final zero value
        if f_val > 0:
            b_0 = m_0
        elif f_val < 0:
            a_0 = m_0
        else:
            zero_f = m_0
    return zero_f


# Main program
# Take inputs
function = input("Enter your function: ")
test_a = float(input("Enter your first test point: "))
test_b = float(input("Enter your second test point: "))

# Call functions
function = function_list(function)
zero = bisection_zero(function, test_a, test_b)

# Final output
print(f"The zero is {zero}.")
