o = range(0, 100, 4)
print(o)
p = o[::5]
print(p)
for i in p:
    print(i)

print('=' * 50)
for i in o:
    print(i)

print()

print('r')
r = range(0, 100, 2)
print(r)
for i in r:
    print(i)

print("\ns")
s = r[::2]
print(s)
for i in s:
    print(i)

print("\nt")
t = s[::-2]
print(t)
for i in t:
    print(i)
