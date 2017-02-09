for i in range(1, 20):
    print("i is now {}".format(i))

print()
number = "9,223,372,036,854,775,807"
for i in range(0, len(number)):
    print(number[i])

print()
for i in range(0, len(number)):
    if number[i] in '0123456789':
        print(number[i], end='')
