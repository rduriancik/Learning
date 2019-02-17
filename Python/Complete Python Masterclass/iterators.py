__author__='Robert Duriancik'

string = "1234567890"
# for char in string:
#     print(char)
my_terator = iter(string)
print(my_terator)
print(next(my_terator))
print(next(my_terator))

for char in string:
    print(char)

for char in iter(my_terator):
    print(char)

print("\n\nCHALLENGE")
my_list = [1,2,3,4,5,6,7,8,9,0]
list_iter = iter(my_list)
for i in range(0, len(my_list)):
    print(next(list_iter))
