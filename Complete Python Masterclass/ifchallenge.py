author='Robert Duriancik'

name = input('Enter your name: ')
age = int(input('Enter you age: '))

if 17 < age < 31:
    print('Dear {} welcome to the holiday'.format(name))
else:
    print('Dear {} you must be between 18 and 30 to attend the holiday')