__author__='Robert Duriancik'

metallica = "Ride the Lightning", "Metallica", 1984
metallica2 = ["Ride the Lightning", "Metallica", 1984]
metallica2.append("Rock")

# title, artist, year = metallica2 # Not working. Too many items to unpack
# print(title)
# print(artist)
# print(year)

imelda = "More Mayhem", "Imelda May", 2011, (
    (1, "Pulling the Rug"), (2, "Psycho"), (3, "Mayhem"), (4, "Kentish Town Waltz")
)

print(imelda)
title, artist, year, tracks = imelda
print(title)
print(artist)
print(year)
print(tracks)

print("=" * 50)
imelda2 = "More Mayhem", "Imelda May", 2011, (1, "Pulling the Rug"), (2, "Psycho"), (3, "Mayhem"), (4, "Kentish Town Waltz")
title, artist, year, track1, track2, track3, track4 = imelda2
print(title)
print(artist)
print(year)
print(track1)
print(track2)
print(track3)
print(track4)

print("=" * 50)

title, artist, year, tracks = imelda
print(title)
print(artist)
print(year)
for song in tracks:
    track, title = song
    print("\t{}. {}".format(track, title))
