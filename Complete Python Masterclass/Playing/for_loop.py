for i in range(1, 20):
    print("i is now {}".format(i))

print()
number = "9,223,372,036,854,775,807"
for i in range(0, len(number)):
    print(number[i])

print()
cleanedNumber = ''
# for i in range(0, len(number)):
#     if number[i] in '0123456789':
#         cleanedNumber += number[i]
#
# newNumber = int(cleanedNumber)
# print("The number is {}".format(newNumber))

for char in number:
    if char in '1234567890':
        cleanedNumber += char

newNumber = int(cleanedNumber)
print("The number is {}".format(newNumber))

print()
for state in ["not pinin'", "no more", "a stiff", "bereft of lift"]:
    print("This parrot is " + state)

print()
for i in range(0, 100, 5):
    print("i is {}".format(i))

print()
for i in range(1, 13):
    for j in range(1, 13):
        print("{1} times {0} is {2}".format(i, j, i * j), end="\t")
    # print("=======================")
    print()
