str = "ckyyynbbbbb"
c = [[0] * 30] * 30
b = [[0] * 30] * 30
m = len(str)
for i in range(1, m):
    c[i][0] = 0
for j in range(0, m):
    c[0][j] = 0
for i in range(0, m):
    for j in range(0, m):
        if str[i] == str[m - j - 1]:
            c[i][j] = c[i - 1][j - 1] + 1
            b[i][j] = 1
        elif c[i - 1][j] >= c[i][j - 1]:
            c[i][j] = c[i - 1][j]
            b[i][j] = 2
        else:
            c[i][j] = c[i][j - 1]
            b[i][j] = 3

i = m - 1
j = m - 1
while i > 0 or j > 0:
    if i == 0:
        break
    if b[i][j] == 1:
        i = i - 1
        j = j - 1
        print(str[i])
    elif b[i][j] == 2:
        i = i - 1
    elif b[i][j] == 3:
        j = j - 1

# print(c[i][j])
# print(m - c[i][j])
# print(str + str[:m - c[i][j]][::-1])
