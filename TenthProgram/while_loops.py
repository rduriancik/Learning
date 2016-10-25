
def loop(till):
    i = 1
    numbers = []

    while i < till + 1:
        print "At the top i is %d" % i
        numbers.append(i)

        i += 1
        print "Numbers now: ", numbers
        print "At the bottom i is %d" % i

    print "The numbers: "

    for num in numbers:
        print num

loop(5)