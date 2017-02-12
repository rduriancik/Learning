# i = 0
# while i < 10:
#     print("i is now {}".format(i))
#     i += 1

# available_exits = ["east", "north east", "south"]
# chosen_exist = ""
# while chosen_exist not in available_exits:
#     chosen_exist = input("Please choose a direction: ")
#     if chosen_exist == "quit":
#         print("Game over")
#         break
# else:
#     print("Aren't you glad you got out of there!")

import random

highest = 100
answer = random.randint(1, highest)

print("Please guess a number between 1 and {}: ".format(highest))
guess = int(input())
while guess != answer:
    if guess == 0:
        break
    if guess < answer:
        print("Please guess higher")
    else:
        print("Please guess lower")
    guess = int(input())
else:
    print("Well done, you guessed it")
