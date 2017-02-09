name = input("Enter your name: ")
age = int(input("Enter your age, {}: ".format(name)))

if 17 < age < 31:
    print("Welcome to the club 18-30 holidays, {}".format(name))
else:
    print("I'm sorry, you are too young. Come back in {} years".format(18 - age))
