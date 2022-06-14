import csv

#Name, Salary, address, department
class Employee:

    Name = "xxx"
    Salary = 0.0
    Address = "xxx"
    Department = "xxx"
    ID = "000000"
    def __init__(self, Name, Salary, Address, Department, ID) -> None:
        self.Name = Name
        self.Salary = Salary
        self.Address = Address
        self.Department = Department
        self.ID = ID

    def displayE(self):
        print("ID is: " + self.ID)
        print("Name is: " + self.Name)
        print("Salary is: " + str(self.Salary))
        print("Address is: " + self.Address)
        print("Department is: " + self.Department)
        print("_")


# kiem tra ki tu co phai so hay khong bang ma ASCII
def checknumber(Str):
    check = True
    for i in range(0, len(Str)):
        if((ord(Str[i]) < ord('0')) or (ord(Str[i]) > ord('9'))):
            check = False
            break
    return check

# Kiem tra chuoi nhap vao co phai chuoi ten nguoi hay khong
def checkName(Str):
    temp = Str.replace(' ','')
    return temp.isalpha()




# add employee
def addEmployee(List):

    n = int(input("How many employees do you want to add: "))

    for i in range(n):
        #input name   
        while(1):
            name = input("Get name:")
            if(checkName(name)):
                break


        #input salary
        while(1):
            salary = input("Get salary: ")
            if(checknumber(salary)):
                salary = float(salary)
                break

        address = input("Get address: ")
        department = input("Get department: ")
        ID = input("Get ID: ")
        temp = Employee(name, salary, address, department, ID)
        List.append(temp)
        print("_")

#display the list
def display(List):
    n = len(List)
    print("________________________")
    for i in range(0, n):
        print("ID: " + str(i))
        List[i].displayE()
    print("________________________")


#display a specific employee
def searchbyID(List):
    temp = input("Get the ID: ")
    n = len(List)

    for i in range(0, n):
        if(List[i].ID == temp):
            print("N: " + str(i))
            List[i].displayE()
            break

def displayN(List):
    N = int(input("Serial number of this employee: "))
    print("Serial number: " + str(N))
    List[N].displayE()

#delete a specific employee
def deleteN(List):
    N = int(input("Serial number of the employee you want to hire: "))
    check = 1
    while(1):
        List[N].displayE()
        temp = input("You want to delete this employee?(Y/N): ")
        if(temp == "Y" or temp == "y"):
            List.pop(N)
            break
        if(temp == "N" or temp == "n"):
            break

    

#Update a specific employee
def updateN(List):
    N = int(input("Serial number of employee you want to update: "))
    #input name   
    while(1):
        name = input("Get name:")
        if(checkName(name)):
            break


    #input salary
    while(1):
        salary = input("Get salary: ")
        if(checknumber(salary)):
            salary = float(salary)
            break
    ID = input("Get ID: ")
    address = input("Get address: ")
    department = input("Get department: ")
    List[N].ID = ID
    List[N].Name = name
    List[N].Salary = salary
    List[N].Address  = address
    List[N].Department = department

#Search by name
def SearchbyName(List):
    temp = (input("Get the Name: ")).upper()
    n = len(List)

    for i in range(0, n):
        if(List[i].Name.upper() == temp):
            print("N: " + str(i))
            List[i].displayE()
    
#Search by salary
def SearchbySalary(List):
    while(1):
            temp_min = input("Get the min Salary: ")
            temp_max = input("Get the max Salary: ")
            if(checknumber(temp_min) and checknumber(temp_max)):
                temp_min = float(temp_min)
                temp_max = float(temp_max)
                break
    n = len(List)


    for i in range(0, n):
        if(List[i].Salary <= temp_max and List[i].Salary >= temp_min):
            print("N: " + str(i))
            List[i].displayE()

def SearchbyDepartment(List):
    temp = (input("Get the department: ")).upper()
    n = len(List)

    for i in range(0, n):
        if(List[i].Department.upper() == temp):
            print("N: " + str(i))
            List[i].displayE()

#Save to CSV
def SavetoCSV(List):
    n = len(List)
    f = open("data.csv", "w", newline="")
    csv_writer = csv.writer(f)
    csv_writer.writerow(['N','ID', 'Name', 'Salary', 'Address', 'Department'])
    for i in range(0, n):
        csv_writer.writerow([i, List[i].ID, List[i].Name, List[i].Salary, List[i].Address, List[i].Department])
    f.close()




#init List
List = []

checkpoint = 1
while(checkpoint):
    print("Choose the option: ")
    print("0 - add employee")
    print("1 - Display by serial number")
    print("2 - display by ID")
    print("3 - delete a specific employee")
    print("4 - update the information by serialNumber")
    print("5 - Search information by name")
    print("6 - Search information by Salary")
    print("7 - Search information by Department")
    print("8 - Display all info")
    print("9 - end the process")

    

    n = input("Option: ")
    if n.isdigit():
        n = int(n)
    else:
        print('Invalid value')
        continue
    #switch case
    if(n == 0):
        addEmployee(List)
    elif(n == 1):
        displayN(List)
    elif(n == 2):
        searchbyID(List)
    elif(n == 3):
        deleteN(List)
    elif(n == 4):
        updateN(List)
    elif(n == 5):
       SearchbyName(List)
    elif(n == 6):
       SearchbySalary(List)
    elif(n == 7):
       SearchbyDepartment(List)
    elif(n == 8):
        display(List)
    elif(n == 9):
        check = input(("Do you want to end the process(Y/N): "))
        if(check == 'Y' or check == 'y'):
            checkpoint = 0
    else:
        print('Invalid value')


SavetoCSV(List)





