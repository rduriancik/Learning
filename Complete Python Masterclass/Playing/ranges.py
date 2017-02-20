my_list = list(range(10))
print(my_list)

print()

even = list(range(0, 10, 2))
odd = list(range(1, 10, 2))
print(even)
print(odd)

print()

# my_string = "abcdefghijklmnopqrstuvwxyz"
# print(my_string.index('e'))
# print(my_string[4])

small_decimals = range(0, 10)
print(small_decimals)

print(small_decimals.index(3))

print()

odd = range(1, 10000, 2)
print(odd)
print(odd[985])

print()

sevens = range(7, 1000000, 7)
# x = int(input("Please enter a positive number less than one million: "))
# if x in sevens:
#     print("{} is divisible by seven".format(x))

print()

print(small_decimals)
my_range = small_decimals[::2]
print(my_range)
print(my_range.index(4))

print()

decimals = range(0, 100)
print(decimals)

my_range = decimals[3:40:3]
print(my_range)

for i in my_range:
    print(i)

print('=' * 40)

for i in range(3, 40, 3):
    print(i)

print(my_range == range(3, 40, 3))  # True

print()

print(range(0, 5, 2) == range(0, 6, 2))  # True
print(list(range(0, 5, 2)))
print(list(range(0, 6, 2)))

print()

r = range(0, 100)
print(r)

for i in r[::-2]:
    print(i)

print('=' * 50)
for i in range(99, 0, -2):
    print(i)

print('=' * 50)
print(range(0, 100)[::-2] == range(99, 0, -2))  # True

for i in range(0, 100, -2):  # Doesn't work 0 - 2 = -2 - 2 = -4
    print(i)

print()

backString = "egaugnal lufrewop yrev a si nohtyP"
print(backString[::-1])

r = range(0, 10)
for i in r[::-1]:
    print(i)
