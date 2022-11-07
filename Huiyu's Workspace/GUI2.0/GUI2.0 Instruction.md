# GUI2.0 Instruction 

## 如何合并

1. 导入HuiyuBAI下所有文件

2. Software.java execute method if语句做添加

   ![image-20221107133114514](C:\Users\huiyu\AppData\Roaming\Typora\typora-user-images\image-20221107133114514.png)

3. Command.java 添加setIP()命令

   ![image-20221107133220888](C:\Users\huiyu\AppData\Roaming\Typora\typora-user-images\image-20221107133220888.png)

*相关代码可参照GUI2.0 中内容

## 如何执行

1. HuiyuBAI folder 有test.java有演示代码

2. 接口样式：

   batchAdd:

   ![image-20221107133435735](C:\Users\huiyu\AppData\Roaming\Typora\typora-user-images\image-20221107133435735.png)

​	batchDelete

![](C:\Users\huiyu\AppData\Roaming\Typora\typora-user-images\image-20221107133448198.png)

batchPreview![image-20221107133510040](C:\Users\huiyu\AppData\Roaming\Typora\typora-user-images\image-20221107133510040.png)

batchExecute![image-20221107133521329](C:\Users\huiyu\AppData\Roaming\Typora\typora-user-images\image-20221107133521329.png)

除此额外提供接口：

![image-20221107133757233](C:\Users\huiyu\AppData\Roaming\Typora\typora-user-images\image-20221107133757233.png)

返回处理过的所有img的List,若有用可使用

## 可能的问题

批量执行下，console会基于每个命令跳出大量意义甚微的string，但基于目前情况似乎难以解决，也并不影响功能，可解决可不解决：

如图为test.java的console：

![image-20221107134050514](C:\Users\huiyu\AppData\Roaming\Typora\typora-user-images\image-20221107134050514.png)
