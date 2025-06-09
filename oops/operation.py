class Patient:
    
    def __init__(self , id , name , ssn):  #constructor 
        self.__id = id
        self.__name = name
        self.__ssn = ssn
    

    def setId(self, id):
        self.__id = id
    
    def setName(self , name):
        self.__name = name
    
    def setSsn(self, ssn):
        self.__ssn = ssn

    def getId(self):
        return self.__id

    def getName(self):
        return self.__name
    
    def getSsn(self):
        return self.__ssn


#user input 
patientId = input("enter the patient id : ")
patientName = input("enter the patient name : ")
ssn = int(input("enter the ssn number : "))

p1 = Patient(patientId , patientName , ssn)

print(p1.getId(), p1.getName() , p1.getSsn())  


