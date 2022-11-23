org=input("Please input the original string: ")
m=len(org)

org_revserse=org[::-1]

c=[[0]*(m+1) for _ in range(m+1)]
b=[[0]*(m+1) for _ in range(m+1)]

for i in range(1,m+1):
    for j in range(1,m+1):
        if org[i-1]==org_revserse[j-1]:
            c[i][j]=c[i-1][j-1]+1
            b[i][j]=1
        else:
            if c[i-1][j]>=c[i][j-1]:
                c[i][j]=c[i-1][j]
                b[i][j]=2
            else:
                c[i][j]=c[i][j-1]
                b[i][j]=3

count=m-c[m][m]
insertNum=0
org=list(org)
# backtracking to find the string
for i in range(1,m+1):
    if not b[i][i]==1:
        org.insert(i-1+insertNum,org_revserse[i-1])
        insertNum+=1
print("The min insert: ",count,"\n","The result string: ","".join(org))