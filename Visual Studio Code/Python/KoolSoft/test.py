arr = [1, 4, 5, 7, 9]

res = []

s = 0

visited = [0 for i in range(5)]

def solve(k):
    global s
    for i in range(k-1, -1, -1):
        if visited[k] == 0:
            if 18 <= s <= 19:
                return 

            if s + arr[k] <= 19:
                s += arr[k]
                print(arr[k])
                res.append(arr[k])
                visited[k] = 1
            
        solve(i)

    if (s < 18 and visited[k] == 1):
        s -= arr[k]
        res.remove(arr[k])
        visited[k] = 0

solve(4)
print(res)