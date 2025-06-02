element = int(input("enter the value : "))

for i in range(1 , element+1):
    if i<100 and i%10!=0:
        print(i)
    else:
        break
