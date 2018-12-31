__author__='Robert Duriancik'

i = 0
while i < 10:
    print("Value {}".format(i))
    i += 1

available_texts = ['east', 'north east', 'south']
chosen_exit = ''
while chosen_exit not in available_texts:
    chosen_exit = input('Please choose a direction: ')
    if chosen_exit == 'quit':
        print("Game over")
        break
else:
    print("aren't you gald you got out of there!")

