__author__='Robert Duriancik'

import random

highest = 10
answer = random.randint(1, highest)
guess = 0
while guess != answer:
    guess = int(input('Enter the number between 1 and 10: '))
    if guess == 0:
        print("Game over")
        break
    elif guess == answer:
        print("Congratulations! You won!")
        break
    elif guess < answer:
        print('Please guess higher.')
    else:
        print('Please guess lower')
    
