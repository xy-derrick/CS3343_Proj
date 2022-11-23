str = "bbbcc"
c = [[0 for i in range(20)] for j in range(20)]
b = [[0 for i in range(20)] for j in range(20)]
m = len(str)
for i in range(1, m+1):
    for j in range(1, m+1):
        if str[i-1] == str[m - j]:
            c[i][j] = c[i - 1][j - 1] + 1
            b[i][j] = 1
        elif c[i - 1][j] >= c[i][j - 1]:
            c[i][j] = c[i - 1][j]
            b[i][j] = 2
        else:
            c[i][j] = c[i][j - 1]
            b[i][j] = 3

i = m
j = m
lcs = ""
index = []
rev_index = []
while i > 0 and j > 0:
    if b[i][j] == 1:
        i = i - 1
        j = j - 1
        lcs += str[i]
        index.insert(0, i)
    elif b[i][j] == 2:
        i = i - 1
    elif b[i][j] == 3:
        j = j - 1


print(index)
print(c[m][m])
print(m-c[m][m])

mid = index[int(c[m][m]/2)]
print(f"mid: {mid}")
if mid < m/2:
    half = str[mid+1:][::-1]
    print(f"half: {half}")
    res = half+str[mid:]
    print("res: "+res)
else:
    half = str[:mid]
    print(f"half: {half}")
    res = str[:mid]+half[::-1]
    print("res: "+res)

