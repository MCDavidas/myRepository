import subprocess
import sys
from prettytable import PrettyTable

subprocess.call('./a.out');

t = PrettyTable()
t.field_names = ['Параметр W', "Количество итераций", "||..||", "максимум-норма погрешности" ]
[ t.add_row(l.split(sep = ' ')) for l in open('results.txt').readlines() ]

print(t)
