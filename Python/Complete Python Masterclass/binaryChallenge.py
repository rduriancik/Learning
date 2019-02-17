__author__="Robert Duriancik"

powers = []
for i in range(15, -1, -1):
    powers.append(2**i)
print(powers)
number = int(input("Enter number: "))

printing = False
for pow in powers:
    bit = number // pow
    if bit != 0 or pow == 1:
        printing = True
    if printing:
        print(bit, end='')
    number %= pow

print()