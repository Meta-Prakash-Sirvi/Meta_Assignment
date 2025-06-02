
math = int(input("enter the math marks "))
physics = int(input("enter the physics marks "))
chemistry  = int(input("enter the chemistry marks "))

if math>=35 and physics>=35 and chemistry>=35:
    avg_marks = (math + physics + chemistry)/3
    if avg_marks <=59:
        print("Student got \"c\" grade")
    elif avg_marks <=69:
        print("student got \"B\" grade")
    else :
        print("student got \"A\" grade")

else:
    print("student is failed ")




