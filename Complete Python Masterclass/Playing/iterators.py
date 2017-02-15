string = "1234567890"
# for char in string:
#     print(char)
# my_iterator = iter(string)
# print(my_iterator)
# print(next(my_iterator))
# print(next(my_iterator))
# print(next(my_iterator))
# print(next(my_iterator))
# print(next(my_iterator))
# print(next(my_iterator))
# print(next(my_iterator))
# print(next(my_iterator))
# print(next(my_iterator))
# print(next(my_iterator))

for char in string:
    print(char)

# the same
for char in iter(string):
    print(char)

print()
# challenge
items = "Test_String"
items_iterator = iter(items)
for i in range(0, len(items)):
    print(next(items_iterator))
