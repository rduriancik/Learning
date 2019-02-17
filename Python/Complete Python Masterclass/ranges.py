__author__="Robert Duriancik"

print(range(100))
my_list = list(range(10))
print(my_list)

even = list(range(0, 10, 2))
odd = list(range(1, 10, 2))
print(even)
print(odd)

my_string = "abcdefghijklmnopqrstuvwxyz"
print(my_string.index("e"))
print(my_string[4])

small_decimals = range(0, 10)
print(small_decimals.index(3))

odd2 = range(1, 10000, 2)
print(odd2)
print(odd2[985])

sevens = range(7, 1000000, 7)
x = int(input('Please enter a positive number less than million: '))
if x in sevens:
    print("{} is divisible by seven".format(x))

my_range = small_decimals[::2]
print(small_decimals)
print(my_range)
print(my_range.index(4))

decimals = range(0, 100)
print(decimals)

print('=' * 40)
my_range2 = decimals[3:40:3]
for i in my_range2:
    print(i)

print('=' * 40)

for i in range(3, 40, 3):
    print(i)

print(my_range2 == range(3, 40, 3))