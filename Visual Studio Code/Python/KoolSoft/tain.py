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
visited = [0 for i in range(length)]
MAX_HEIGHT = 2852
MIN_HEIGHT = 2800
pageHeight = 0
listKeyQues = []

def solve(k):
    global pageHeight

    for i in range(k-1, -1, -1):
        if visited[k] == 0:
            if MIN_HEIGHT <= pageHeight <= MAX_HEIGHT:
                return 

            if pageHeight + listVal[k] <= MAX_HEIGHT:
                pageHeight += listVal[k]
                print(listVal[k])
                listKeyQues.append(listKey[k])
                visited[k] = 1
            
        solve(i)

    if (pageHeight < MIN_HEIGHT and visited[k] == 1):
        pageHeight -= listVal[k]
        listKeyQues.remove(listKey[k])
        visited[k] = 0


def fillPage():
    result = {}
    global pageHeight
    global listKeyQues
    page = 1
    while len(listVal) > 0:
        pageHeight = 0
        listKeyQues = []
        listLen = len(listVal)
        solve(listLen - 1)
        sum = 0
        temp = []
        for i in listKeyQues:
            temp.append(tmp[i])
            sum += tmp[i]
        temp.append(sum)
        result[page] = temp
        for key in listKeyQues:
            index = listKey.index(key)
            listKey.pop(index)
            listVal.pop(index)

        page += 1
        
    return result

solve(length - 1)
print(listKeyQues)
