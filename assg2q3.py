str = "3ckyyynb"
str_rev = "3bnyyykc"
c = [[0] * 10] * 10
b = [[0] * 10] * 10
m = len(str)
for i in range(1, m):
    c[i][0] = 0
for j in range(0, m):
    c[0][j] = 0
for i in range(1, m):
    for j in range(1, m):
        if str[i] == str_rev[j]:
            c[i][j] = c[i - 1][j - 1] + 1
            b[i][j] = 1
        elif c[i - 1][j] >= c[i][j - 1]:
            c[i][j] = c[i - 1][j]
            b[i][j] = 2
        else:
            c[i][j] = c[i][j - 1]
            b[i][j] = 3

print(c[i][j])
print(m - c[i][j] - 1)
print(str[1:] + str[1:m - c[i][j]][::-1])
