parrot = "Norwegian Blue"
print(parrot)
print(parrot[0])
print(parrot[-1])
print(parrot[0:6])
print(parrot[:6])
print(parrot[6:])
print(parrot[-4:-2])
print(parrot[0:6:2])
print(parrot[0:6:3])

number = "9,223,377,036,854,775,808"
print(number[1::4])

numbers = "1, 2, 3, 4, 5, 6, 7, 8, 9"
print(numbers[::3])

string1 = "He's "
string2 = "probably "
print(string1 + string2)

print("Hello " * 5)
print("Hello " * (5 + 4))
print("Hello " * 5 + "4")

today = "friday"
print("day" in today)
print("fri" in today)
print("thur" in today)
print("parrot" in "fjord")
