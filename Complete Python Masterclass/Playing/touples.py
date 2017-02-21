t = 'a', 'b', 'c'  # ('a', 'b', 'c')
print(t)

print('a', 'b', 'c')
print(('a', 'b', 'c'))

print()

welcome = "Welcome to my Nightmare", "Alice Cooper", 1975
bad = "Bad Company", "Bad Company", 1974
budgie = "Nightflight", "Budgie", 1981
imelda = "More Mayhem", "Emilda May", 2011
metallica = "Ride the Lightning", "Metallica", 1984

print(metallica)
print(metallica[0])
print(metallica[1])
print(metallica[2])

# metallica[0] = "Master of Puppets" # Cannot change existing content
imelda = imelda[0], "Imelda May", imelda[2]
print(imelda)

print()

metallica2 = ["Ride the Lightning", "Metallica", 1984]
print(metallica2)
metallica2[0] = "Master of Puppets"  # Can change field, because it's a list
print(metallica2)
