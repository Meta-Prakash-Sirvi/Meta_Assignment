# country = ["India" , "USA", "Nepal"]

#user input 
country = input("enter the country name :").split(" ")   #.split() both same

print(country)


#add the country at end of list
country.append(input("enter the country name you want to add the end of list :"))

print(country)


#remove the country by index
del country[int(input("enter the index you want to remove"))]   # del is a statement and it not retrun element ,outofindex show error
print(country)

# another way
# print("remove country name is : ", country.pop(int(input("enter the index you want to remove"))))   # return element , no index provied the remove lat element , out of range -> error
# print(country)


# Add a country in the middle of the list.
middleIndex = len(country)//2
country.insert(middleIndex , input("enter the country to add at middle"))
print(country)