ip_address = input("Enter an IP address: ")

segment = 1
segment_len = 0
ch = ''
for ch in ip_address:
    if ch == '.':
        print("Segment {} contains {} characters".format(segment, segment_len))
        segment += 1
        segment_len = 0
    else:
        segment_len += 1

if ch != '.':
    print("Segment {} contains {} characters".format(segment, segment_len))
