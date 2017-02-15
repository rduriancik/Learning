parrot_list = ["non pinin'", "no more", "a stiff", "bereft of live"]

parrot_list.append("A Norwegian Blue")

for state in parrot_list:
    print("This parrot is " + state)

print()  ##################

even = [2, 4, 6, 8]
odd = [1, 3, 5, 7, 9]
numbers = even + odd
# numbers.sort() # works on the existing variable
numbers_in_order = sorted(numbers)  # sorted() returns new object
print(numbers_in_order)

if numbers == numbers_in_order:
    print("The lists are equal")
else:
    print("The lists are not equal")

if numbers_in_order == sorted(numbers):
    print("The lists are equal")
else:
    print("The lists are not equal")

print()  ##################

list_1 = []
list_2 = list()

print("List 1: {}".format(list_1))
print("List 2: {}".format(list_2))

if list_1 == list_2:
    print("The lists are equal")

print()  ##################

print(list("The lists are equal"))

print()  ##################

even = [2, 4, 6, 8]
another_even = even

print(another_even is even)
print(another_even == even)  # == -> content is equal

another_even.sort(reverse=True)
print(even)

print()
another_even = list(even)  # returns new list
print(another_even is even)
print(another_even == even)
another_even.sort(reverse=True)
print(even)

print()
another_even = sorted(even, reverse=True)
print(another_even is even)
print(another_even == even)

print()  ##################

even = [2, 4, 6, 8]
odd = [1, 3, 5, 7, 9]
numbers = [even, odd]
print(numbers)

for number_set in numbers:
    print(number_set)

    for val in number_set:
        print(val)

print()  ##################

menu = []
menu.append(["egg", "spam", "bacon"])
menu.append(["egg", "sausage", "bacon"])
menu.append(["egg", "spam"])
menu.append(["egg", "bacon", "spam"])
menu.append(["egg", "bacon", "sausage", "spam"])
menu.append(["spam", "bacon", "sausage", "spam"])
menu.append(["spam", "egg", "spam", "spam", "bacon", "spam"])
menu.append(["spam", "egg", "sausage", "spam"])

for meal in menu:
    if "spam" not in meal:
        print(meal)
