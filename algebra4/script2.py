from prettytable import PrettyTable
import script0
from math import log


def get_derivative_func(x):
    return (4**x) * log(4) - 5


def get_next_x(x):
    return x - script0.get_y(x) / get_derivative_func(x)


eps = 0.0000001
a = script0.l

table = PrettyTable()
table.field_names = ['k', 'xk', 'ek']

curr_x = a
next_x = get_next_x(curr_x)
k = 0
while abs(curr_x - next_x) > eps:
    table.add_row([k, curr_x, abs(curr_x - next_x)])
    k += 1
    curr_x = next_x
    next_x = get_next_x(curr_x)

print('Метод Ньютона:')
print(curr_x)
print(table)