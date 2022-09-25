# CS3343_Proj 

## 图片处理软件3343

## 架构--JAVA

### Main

#### Main.java
负责创建 软件实例 和 图片处理器实例 (软件包含多个图片处理器,每个处理器只负责一张图片,软件因此可以并行处理多张图片),并负责 输入命令行 执行命令行
![image](https://user-images.githubusercontent.com/113168400/191910413-0e566285-c818-4af7-8aa6-8b2b4be13cd7.png)
software实例即是打开的软件,拥有undo redo,并拥有多个imgprocessor,其中imgprocessor可以被初始化和批量处理对应图片,交付给command的执行,并有software管理所有imgprocessor,类似浏览器和多个窗口的关系,每个窗口拥有一张页面和对应操作,整体的重新打开关闭由浏览器决定

### Code
#### Command
![image](https://user-images.githubusercontent.com/113168400/190560887-b5bddddf-e203-4fe6-87b0-98d16e38ea05.png)
注:文件夹含Base以及other,Base含所有other的父类和其他平级不能被继承的类,other含Base继承的子类
命令行设计模式，通过命令行执行对于功能，可传入浮点 字符串等参数，对应选择文件 滑标调节强度功能(任何图片数据应先在imgProcessor定义,然后通过receiver变量访问)
负责图片处理的功能实现，模块功能可以提供factory设计集成调用，注：滑标调节图片亮度，多次数据输入不应做多次command，否则无法undo取消全部修改，应command内function实现通过浮点设置图片亮度功能，command集成为亮度上升 下降俩个函数，并提供undo一次性取消所有上升或所有下降（记一连串调节为一个command）
#####Base
含所有other的父类和其他平级不能被继承的类,其他文件夹含Base继承的子类
#####Sample
提供 收集不同参数的命令行 模板，接口为 execute undo，execute内可放自定义函数 自定义类，自定义类 函数均放在重载函数后（undo），iProcessor变量为处理器，数据只能通过iProcesoor获取。 （若没有undo则先留空）
#####filter 
包含filter处理语句
#####Common
包含软件关闭 选择文件等基础操作
#####Operation
直接处理图片大小等操作
####other
可以自己添加对于功能文件夹，如导出特定格式等操作文件夹，注：为提供模板，![image](https://user-images.githubusercontent.com/113168400/192139311-83fd2fbe-e3b2-4402-bcb4-ecf400213c4d.png)
暂不收录文件夹，分别调用情况为：弹窗打开图片 从地址读取图片 缩放图片 打印图片信息


#### Exception
自定义错误

#### Software
imgPorcessor类为单个图片处理器，每次生成从接口读入一个图片便被加入到software类的arraylist中,并记录自定义数据 如亮度 模糊度等
Software是软件实例,能够存储多个 图片处理器，为单例模式

案例详解，代码见Main.java
![image](https://user-images.githubusercontent.com/113168400/192139421-a2430328-af36-4080-b544-5b13238c000b.png)
操作为读取 缩放 展示，具体流程如图所示

