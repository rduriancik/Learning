__author__= 'Robert Duriancik'
a = 12
b = 3
print(a + b)
print(a - b)
print(a * b)
print(a / b)
print(a // b)
print(a % b)
print(a + b / 3 - 4 * 12)
print((((a+b) / 3) -4) * 12)
for i in range(1, a // b):
    print(i)

c = a + b
d = c / 3
e = d - 4
print(e * 12)

parrot = "Norwegian Blue"
print(parrot)
print(parrot[3])
print(parrot[-1])
print(parrot[0:6])
print(parrot[:6])
print(parrot[6:])
print(parrot[-4:-2])
print(parrot[0:6:2])
print(parrot[0:6:3])

number = "9,223,372,036,854,775,807"
print(number[1::4])
numbers = "1, 2, 3, 4, 5, 6, 7, 8, 9"
print(numbers[0::3])

print("he's " "probably " "pining")
print("Hello " * 5)
# print("Hello " * 5 + 4) Won't run
print("Hello " * (5 * 4))
today = "Friday"
print("day" in today)
print("fri" in today)
print("thur" in today)
