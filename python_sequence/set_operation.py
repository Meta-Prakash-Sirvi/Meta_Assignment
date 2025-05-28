#user input 
listt = input("enter the coutry name").split(" ")
country = set(listt)


#set = {"india", "usa" , "Nepal"}

#Add a country at the end of the set
country.add(input("enter the value you want to add at the end of the set")) 
print(country)


#Remove any country using its index.  not a index in set (covert into list remove by index)
countryName = (input("enter the country name "))
country.remove(countryName)
print(country)

#anotherway 
# sett.discard(coutryName)
# print(sett)

#Add a country in the middle of the set.

country.add(input("enter the country to add in the sett"))
