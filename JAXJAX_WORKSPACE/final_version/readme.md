# 最终版本

## 1.请更新所有文件至最新版本
修复了单例设计模式的错误，由之前一直返回新数组更正返回原数组，接口未变
为Software文件夹下类修改了部分接口实现，返回值不变
新增ipState和imgState用于统一实现undo redo
## 2.用户输入
样例见Main.java，完全实现了command下common所有指令的映射，步骤参考main
映射操作请在Commnad文件夹下创建自己文件夹并在main里面创建函数switch映射，详情见main
![image](https://user-images.githubusercontent.com/113168400/193542627-5d149cbf-e508-4d39-9263-d38b925bfed5.png)
![image](https://user-images.githubusercontent.com/113168400/193542690-83ebe76e-a622-4f49-a545-2881d6a5929a.png)
![image](https://user-images.githubusercontent.com/113168400/193542718-85a711f2-ab0f-4d96-9c5b-fcac3c9e34cd.png)
![image](https://user-images.githubusercontent.com/113168400/193542766-b5dc7eb7-09e5-404c-9b11-57ae69316185.png)

提供quickCommand函数作为快速映射，用法见common下switch，分为有无提示和有无imgProcessor传入
![image](https://user-images.githubusercontent.com/113168400/193542856-cde9a11b-43c1-4d3e-b259-354f3327c660.png)
![image](https://user-images.githubusercontent.com/113168400/193542925-05cd33fa-9b79-4431-b281-e8b4b691ece0.png)

对应提示请在command文件夹下修改showOpeartionHint的提示信息，以与提示文本映射
## 3.undo实现
不能undo的语句请实现接口CommandNoncancelabe，请在Common下寻找到 showImgInfo existSoftware案例
![image](https://user-images.githubusercontent.com/113168400/193542972-fe6ced55-81c1-4313-94ed-cdcb2b691e73.png)

对于imgprocessor的undo，请用ipState保存当时状态，严格样板见 readImgFromLocal，分别在操作前后保留俩种状态，通过切换状态实现undo redo
![image](https://user-images.githubusercontent.com/113168400/193543046-97eda60e-e36e-4ed4-bba9-a636f410a723.png)
![image](https://user-images.githubusercontent.com/113168400/193543079-af77f9f7-e46f-4f19-8843-4624b0efb313.png)
![image](https://user-images.githubusercontent.com/113168400/193543103-0bac6ceb-b268-41b9-9e09-603d1504e9fa.png)

对应image的undo，对于需要保留额外信息的内容，请在imgState添加并实现类似ipState的undo redo
