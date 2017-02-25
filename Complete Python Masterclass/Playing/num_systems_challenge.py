num = int(input("Enter a number: "))

bin_num = ""
orig_num = num
while num != 0:
    bin_num = str(num % 2) + bin_num
    num //= 2

print("Number {} in binary is {}".format(bin_num, orig_num))
