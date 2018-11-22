__author__= 'Robert Duriancik'

# name = input("Please enter your name: ")
# age = int(input("How old are you, {}? ".format(name)))
# print(age)

# if age >= 18:
#     print("You are old enough to vote")
#     print("Please put an X in the box")
# else:
#     print("Please come back in {} years".format(18 - age))

print("Please guess some number between 1 and 10: ")
guess = int(input())
if guess != 5:
    if guess > 5:
        print("Please guess higher")
    else:
        print("Please guess lower")
    
    guess = int(input())
    if guess == 5:
        print("Well done, you guessed it")
    else:
        print("Sorry, you have not guessed correctly")
else:
    print("You go it first time")

