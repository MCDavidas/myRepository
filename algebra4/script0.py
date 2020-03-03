from prettytable import PrettyTable


def get_y(x):
    return 4**x - 5*x - 2


def get_borders():
    table = PrettyTable()
    table.field_names = ['k', 'ak', 'bk', 'mid', 'f(ak)', 'f(bk)', 'f(mid)', 'bk - ak']

    eps = 0.01
    a = -1.
    b = 0.
    k = 0
    while abs(a - b) > eps:
        mid = (a + b) / 2.
        if get_y(mid) > 0.:
            a = mid
        else:
            b = mid

        table.add_row([k, a, b, mid, get_y(a), get_y(b), get_y(mid), b - a])
        k += 1

    print(table)
    return a, b


l, r = get_borders()
