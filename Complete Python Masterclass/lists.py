__author__='Robert Duriancik'

# ipAddress = input('Please enter an IP address: ')
# print(ipAddress.count("."))
parrot_list = ['non pinin', 'no more', 'a stiff', 'bereft of live']
parrot_list.append('A Norwegian Blue')
for state in parrot_list:
    print("This parrot is " + state)

even = [2, 4, 6, 8]
odd = [1, 3, 5, 7, 9]
numbers = even + odd
# numbers.sort()
print(sorted(numbers))

list_1 = []
list_2 = list()
print("List 1: {}".format(list_1))
print("List 2: {}".format(list_2))

print(list("The lists are equal"))

another_even = even
another_even2 = list(even)
another_even.sort(reverse=True)
another_even3 = sorted(even, reverse=True)
print(even)
print(another_even2)
print(another_even is even)
print(another_even2 is even)
print(another_even == even) # compares content
print(another_even == another_even2)

numbers2 = [even, odd]
print(numbers2)
for number_set in numbers2:
    for value in number_set:
        print(value, end='')
    print()