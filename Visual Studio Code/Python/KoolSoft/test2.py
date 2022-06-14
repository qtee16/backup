with open('quetions.txt', 'r') as f:
    datalist = f.readlines () 
myDict = {}
for s in datalist:
    x = s.strip().split(" ")
    myDict[x[0]] = float(x[1])

tmp = dict(sorted(myDict.items(), key=lambda item: item[1]))

length = len(tmp)

listKey = list(tmp.keys())
listVal = list(tmp.values())

MAX_HEIGHT = 2852
MIN_HEIGHT = 2800

n = 90
m = 26

result = {}

mark = [0 for i in range(n)]
page = [0 for i in range(m+1)]

def output():
    for i in range(1, m+1):
        for j in range(0, n):
            if mark[j] == i:
                print('Page ' + i + ': ' + listKey[j] + ' ' + listVal[j]) 

    exit(0)

def filledAll():
    global mark
    for i in mark:
        if i == 0:
            return False
    
    return True

def check():
    if (filledAll()):
        output()
    else:
        return 

def fill1(p):
    global mark
    global page
    if p == m + 1:
        check()
        return
    f = False

    for i in range(n - 1, -1, -1):
        if mark[i] == 0:
            if page[p] + listVal[i] <= MAX_HEIGHT:
                f = True
                page[p] += listVal[i]
                mark[i] = p

                if page[p] < MAX_HEIGHT:
                    fill1(p)
                else:
                    fill1(p+1)
                
                page[p] -= listVal[i]

                if page[p] == 0:
                    print("UNSOLVED")
                    exit(0)

                mark[i] = 0

    if f == False:
        if filledAll() == True:
            output()
        else:
            if page[p] < MIN_HEIGHT:
                return 
            else:
                fill1(p+1)


    for i in range(2):
        fill1(1)