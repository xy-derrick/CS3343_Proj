str = "abb"
c = [[0 for i in range(20)] for j in range(20)]
b = [[0 for i in range(20)] for j in range(20)]
m = len(str)
# for i in range(0, m):
#     c[i][0] = 0
#     c[0][i] = 0
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
print(mid)
length = max(mid, m-mid)
print(length)
half = str[mid+1:][::-1]
print(half)
res = half+str[mid:]
print("res: "+res)
print(len(res))
