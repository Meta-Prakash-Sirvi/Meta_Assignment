#find the number is Prime or not

def checkPrimeNumber(element):
    if element<2 :
        return False   #not prime number
    
    el = 2 
    while el<=element//2:
        if element%el==0:
            return False
        el+=1
    
    return True




#user input
value = int(input("enter the number to check prime ot not : "))


if checkPrimeNumber(value):
    print(f"Yes {value} is prime number ")
else :
    print(f"No {value} is not prime number ")



