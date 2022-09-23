# CS3343_Proj 

## 图片处理软件3343

## 架构--JAVA

### Main
注:文件夹含Base以及other,Base含所有other的父类和其他平级不能被继承的类,other含Base继承的子类
#### Main.java
负责创建软件实例并输入命令行执行

### Code
#### Command
![image](https://user-images.githubusercontent.com/113168400/190560887-b5bddddf-e203-4fe6-87b0-98d16e38ea05.png)

命令行设计模式，通过命令行执行对于功能，可传入浮点 字符串等参数，对应选择文件 滑标调节强度功能(任何图片数据应先在imgProcessor定义,然后通过receiver变量访问)
负责图片处理的功能实现，模块功能可以提供factory设计集成调用，注：滑标调节图片亮度，多次数据输入不应做多次command，否则无法undo取消全部修改，应command内function实现通过浮点设置图片亮度功能，command集成为亮度上升 下降俩个函数，并提供undo一次性取消所有上升或所有下降（记一连串调节为一个command）
#### Exception
自定义错误
#### Software
imgPorcessor类为单个图片处理器，每次生成从接口读入一个图片便被加入到software类的arraylist中
Software是软件实例,能够存储多个 图片处理器，为单例模式

![image](https://user-images.githubusercontent.com/113168400/191910413-0e566285-c818-4af7-8aa6-8b2b4be13cd7.png)
software实例即是打开的软件,拥有undo redo,并拥有多个imgprocessor,其中imgprocessor可以被初始化和批量处理对应图片,交付给command的执行,并有software管理所有imgprocessor,类似浏览器和多个窗口的关系,每个窗口拥有一张页面和对应操作,整体的重新打开关闭由浏览器决定
