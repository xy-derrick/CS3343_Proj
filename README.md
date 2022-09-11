# CS3343_Proj 

## 图片处理软件3343

## 架构--JAVA

### Main
注:文件夹含Base以及other,Base含所有other的父类和其他平级不能被继承的类,other含Base继承的子类
#### Main.java
负责创建软件实例并输入命令行执行

### Code
#### Command
命令行设计模式，通过命令行执行对于功能，可传入浮点 字符串等参数，对应选择文件 滑标调节强度功能(任何图片数据应先在imgProcessor定义,然后通过receiver变量访问)
负责图片处理的功能实现，模块功能可以提供factory设计集成调用，注：滑标调节图片亮度，多次数据输入不应做多次command，否则无法undo取消全部修改，应command内function实现通过浮点设置图片亮度功能，command集成为亮度上升 下降俩个函数，并提供undo一次性取消所有上升或所有下降（记一连串调节为一个command）
#### Exception
自定义错误
#### Software
软件类，采取单例设计，存储图片 图片大小等信息，命令行应通过reciver(软件实例)访问数据


