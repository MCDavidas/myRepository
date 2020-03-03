from prettytable import PrettyTable
import script0


def get_next_x(x):
    return (4**x - 2) / 5


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

print('Простые итерации:')
print(curr_x)
print(table)
