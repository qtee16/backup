
with open('quetions.txt', 'r') as f:
    datalist = f.readlines () 
myDict = {}
for s in datalist:
    x = s.strip().split(" ")
    myDict[x[0]] = float(x[1])

tmp = dict(sorted(myDict.items(), key=lambda item: item[1]))

s = 0
length = len(tmp)
for key in tmp:
    s += tmp[key]

listKey = list(tmp.keys())
listVal = list(tmp.values())
print(listKey)
print(listVal)

# newDict = {}

# res = 0

# def find(key, val, LENGTH, l, r):

#     global res

#     if (LENGTH - 50 <= res <= LENGTH):
#         return

#     if (res + val[r] < LENGTH and l <= r):
#         res += val[r]
#         newDict[key[r]] = val[r]
#         find(key, val, LENGTH, l, r - 1)
#         print(val[r])

#     if (res + val[l] < LENGTH and l <= r):
#         res += val[l]
#         newDict[key[l]] = val[l]
#         print(val[l])
#         find(key, val, LENGTH, l + 1, r)

#     if (res < LENGTH - 50):
#         res -= val[l - 1]
#         del newDict[key[l - 1]]
#         if (res + val[l] < LENGTH):
#             res += val[l]
#             newDict[key[l]] = val[l]
#             print(val[l])
#             find(key, val, LENGTH, l + 1, r)

#     print(res)


# find(listKey, listVal, 2852, 0, length - 1)
# print(newDict)

