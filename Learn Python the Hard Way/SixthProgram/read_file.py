from sys import argv

script, filename = argv

print "Reading file..."

source = open(filename, "r")

print source.read()
source.close()
