class InvalidPasswordException(Exception):
    pass


try:
    passward = input("enter the password : ")
    pass_lenght = len(passward)
    if pass_lenght<8:
        raise InvalidPasswordException
    else:
        print("password is valid :", passward)
except InvalidPasswordException:
    print( F"password {passward} must be greater then  or equal to 8 lenght : ")
