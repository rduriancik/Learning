__author__='Robert Duriancik'

number = "9,223,372,036,854,775,807"
cleanedNumbers =''

for i in range(0, len(number)):
    if number[i] in '0123456789':
        cleanedNumbers += number[i]

newNumber = int(cleanedNumbers)
print("The number is {}".format(newNumber))

x = 23
x += 1
print(x)

x -= 4
print(x)

x*=5
print(x)

x /= 4
print(x)

x **= 2
print(x)

x %= 60
print(x)

greeting = "Good "
greeting += "morning"
print(greeting)

greeting *= 5
print(greeting)