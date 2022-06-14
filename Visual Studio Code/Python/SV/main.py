import csv

#Name, Salary, address, department
class Employee:

    Name = "xxx"
    Salary = 0.0
    Address = "xxx/xxx/xxx"
    Department = "xxx"
    def __init__(self, Name, Salary, Address, Department) -> None:
        self.Name = Name
        self.Salary = Salary
        self.Address = Address
        self.Department = Department

    def displayE(self):
        print("Name is: " + self.Name)
        print("Salary is: " + self.Salary)
        print("Address is: " + self.Address)
        print("_")




# add employee
def addEmployee(List):

    n = int(input("How many employees do you want to add: "))

    for i in range(n):
        name = input("Get name:")
        salary = input("Get salary: ")
        address = input("Get address: ")
        department = input("Get department: ")
        temp = Employee(name, salary, address, department)
        List.append(temp)

#display the list
def display(List):
    n = len(List)
    print("________________________")
    for i in range(0, n):
        print("ID: " + str(i))
        List[i].displayE()
    print("________________________")


#display a specificc employee
def displayID(List):
    ID = int(input("Get the ID of this employee: "))
    print("ID: " + str(ID))
    List[ID].displayE()

#delete a specific employee
def deleteID(List):
    ID = int(input("Get the ID of employee you want to hire: "))
    List.pop(ID)

#Update a specific employee
def updateID(List):
    ID = int(input("Get the ID of employee you want to update: "))
    name = input("Get name:")
    salary = input("Get salary: ")
    address = input("Get address: ")
    department = input("Get department: ")
    List[ID].Name = name
    List[ID].Salary = salary
    List[ID].Address  = address
    List[ID].Department = department

#Search by name
def SearchbyName(List):
    temp = (input("Get the Name: ")).upper()
    n = len(List)

    for i in range(0, n):
        if(List[i].Name.upper() == temp):
            List[i].displayE()
    
#Search by salary
def SearchbySalary(List):
    temp = input("Get the Salary: ")
    n = len(List)

    for i in range(0, n):
        if(List[i].Salary == temp):
            print("ID: " + str(i))
            List[i].displayE()

def SearchbyDepartment(List):
    temp = (input("Get the department: ")).upper()
    n = len(List)

    for i in range(0, n):
        if(List[i].Department.upper() == temp):
            print("ID: " + str(i))
            List[i].displayE()

#Save to CSV
def SavetoCSV(List):
    n = len(List)
    f = open("data.csv", "w", newline="")
    csv_writer = csv.writer(f)
    csv_writer.writerow(['ID', 'Name', 'Salary', 'Address', 'Department'])
    for i in range(0, n):
        csv_writer.writerow([i, List[i].Name, List[i].Salary, List[i].Address, List[i].Department])
    f.close()




#init List
List = []

checkpoint = 1
while(checkpoint):
    print("Choose the option: ")
    print("1 - add employee")
    print("2 - display by ID")
    print("3 - delete a specific employee")
    print("4 - update the information by ID")
    print("5 - Search information by name")
    print("6 - Search information by Salary")
    print("7 - Search information by Department")
    print("8 - Display all info")
    print("9 - end the process")

    n = int(input("No: "))

    #switch case
    if(n == 1):
        addEmployee(List)
    elif(n == 2):
        displayID(List)
    elif(n == 3):
        deleteID(List)
    elif(n == 4):
        updateID(List)
    elif(n == 5):
       SearchbyName(List)
    elif(n == 6):
       SearchbySalary(List)
    elif(n == 7):
       SearchbyDepartment(List)
    elif(n == 8):
        display(List)

    if(n == 9):
        checkpoint = 0


SavetoCSV(List)





