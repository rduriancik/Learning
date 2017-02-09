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

print()
shopping_list = ["milk", "pasta", "eggs", "spam", "bread", "rice"]
for item in shopping_list:
    if item == 'spam':
        continue
    print("Buy " + item)

print()
meal = ["egg", "bacon", "spam", "sausages"]
nasty_food_item = ''
for item in meal:
    if item == 'spam':
        nasty_food_item = item
        break
else:  # if the loop comes to the end, this statement is be executed
    print("I'll have a plate of that, then, please")

if nasty_food_item:
    print("Can't I have anything without spam in it")
