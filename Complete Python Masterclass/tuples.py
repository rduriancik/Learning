__author__ = 'Robert Duriancik'

t = ("a", "b", "c") # Parenthesis not required
print(t)

print("a", "b", "c")
print(("a", "b", "c"))

welcome = "Welcome to my Nightmare", "Alice Cooper", 1975
bad = "Bad Company", "Bad Company", 1974
budgie = "Nightflight", "Budgie", 1981
imelda = "More Mayhem", "Emilda May", 2011
metallica = "Ride the Lightning", "Metallica", 1984

print(metallica)
print(metallica[0])
print(metallica[1])
print(metallica[2])

# metallica[0] = "Master of puppets" # Tuples doesn't support item assignment (IMMUTABLE)
imelda = imelda[0], "Imelda May", imelda[2] # Option: Recreate a new tuple
print(imelda)

print("=" * 50)
title, artist, year = imelda # Unpacking tuple
print(title)
print(artist)
print(year)