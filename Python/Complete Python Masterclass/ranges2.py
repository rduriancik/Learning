__author__='Robert Duriancik'

r = range(0, 100)
print(r)

for i in r[::-2]:
    print(i)

print("=" * 50)
for i in range(99, 0, -2):
    print(i)

print("=" * 50)
print(range(0, 100)[::-2] == range(99, 0, -2))

for i in range(0, 100, -2): # Not working. Must be decreasing
    print(i)

backString = "egaugnal lufrewop yrev a si nohtyP"
print(backString[::-1])

print()
print("=" * 50)
o = range(0, 100, 4)
print(o)
for i in o:
    print(i)
p = o[::5]
print("=" * 50)
print(p)
for i in p:
    print(i)