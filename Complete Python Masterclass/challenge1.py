__author__='Robert Duriancik'

ip_address = input('Enter an  IP address: ')

num_of_segments = 1
length = 0
for i in range(0, len(ip_address)):
    if (ip_address[i] == '.'):
        print("Length of the segment {} is {}.".format(num_of_segments, length))
        num_of_segments += 1
        length = 0
    else:
        length += 1
else:
    print("Length of the segment {} is {}.".format(num_of_segments, length))

print("There are {} segments".format(num_of_segments))
