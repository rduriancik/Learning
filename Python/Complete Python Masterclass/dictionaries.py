__author__="Robert Duriancik"

fruit = {"orae": "a sweetm orange, citrus fruit",
"apple": "good for making cider",
"lemon": "a sour, yellow citrus fruit",
"grape": "a small, sweet fruit frowing in bunches",
"lime": "a sour, green citrus fruit",
"apple": "round and crunchy"} # updated value; duplicate key warning

print(fruit)
print(fruit["lemon"])

fruit["pear"] = "an odd shape apple"
print(fruit["pear"])
fruit["pear"] = "great with tequila"
print(fruit["pear"])

del fruit["pear"]
print(fruit)

# fruit.clear() # clears dictionary
# del fruit # completely removes variable
print(fruit)

while True:
    dict_key = input("Please enter a fruit: ")
    if dict_key == "quit":
        break
    description = fruit.get(dict_key, "We don't have a " + dict_key)
    print(description)
    # if dict_key in fruit:
    #     description = fruit.get(dict_key)
    #     print(description)
    # else:
    #     print("we don't have a {}".format(dict_key))  

print(fruit.keys())
print(fruit.values())
print(fruit.items())

for key in fruit:
    print(fruit[key])
for val in fruit.values():
    print(val)

fruit_keys = fruit.keys()
print(fruit_keys)
fruit["tomato"] = "not nice with ice cream"
print(fruit_keys)