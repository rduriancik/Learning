fruit = {"orange": "a sweet, orange, citrus fruit",
         "apple": "good for making cider",
         "lemon": "a sour, yellow citrus fruit",
         "grape": "a small, sweet fruit growing in bunches",
         "lime": "a sour, green citrus fruit"}  # ,
# "apple": "round and crunchy"}  # updated apple value

print(fruit)
print(fruit["lemon"])

fruit["pear"] = "an odd shape apple"
print(fruit)

fruit["lime"] = "great with tequila"
print(fruit)

# print(fruit["tomato"]) # throws error

while True:
    dict_key = input("Please enter a fruit: ")
    if dict_key == "quit":
        break;
    if dict_key in fruit:
        description = fruit.get(dict_key)  # doesn't throw error if key doesn't exist
        print(description)
    else:
        print("We don't have a " + dict_key)

del fruit["lemon"]
print(fruit)
# del fruit -> delete fruit variable
fruit.clear()  # clears content
print(fruit)
