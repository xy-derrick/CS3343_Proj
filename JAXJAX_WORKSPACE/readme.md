# 测试样板,具体exception未实现,仅实现ArgsReader逻辑,可正常测试

## 案例

showOperationHint Command类,提示用户输入(方便展示,放入main并排文件)
ArgsReader 实现读入参数,使用案例见main.java
VariousArgsSampleCommand 实现各种参数的command案例

main.java 展示了 ArgsReader VariousArgsSampleCommand 结合传递不定参数的案例,可结合自己command制作对应switch(exception未完成,测试并交互command已足够)
![image](https://user-images.githubusercontent.com/113168400/193459666-1c8af1f0-7955-46f5-9b1a-8df6dc3e8aee.png)
### 仅此3个新增文件
![image](https://user-images.githubusercontent.com/113168400/193459694-c8b82872-4c35-48ba-898b-cba139140b35.png)
### 流程一览
