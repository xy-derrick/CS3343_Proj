# Gui_version Introduction

## 总体介绍

- 导入GUI时 导入HuiyuBAI 下的所有文件即可，虽然已经实现了和GUI的接口操作，但仍然有一些亟待解决的问题。 说明将先介绍batch的命令类型，然后说明需要协商解决的问题。
- 本次导入的两个项目版本：
   1. consoleFinal：整合所有命令后简单测试可在 console上运行的版本
   2. Gui 1.0： 是针对Gui要求对HuiyuBAI下文件做出微调后的版本，因为做出一些改动应该无法在之前的console上运行，需要和GUI版本合并。


## 命令介绍

##### 流程：

总体来说，批处理要求用户先添加一定数量的图片或命令，然后会生成一张图片的预览，用户可以选择继续export，就会进行批量处理。代码表现为， batchAdd /batchDelete -->batchPreview-->batchExport

##### 代码：

非exception, 共计5个文件：



### 命令介绍与接口样式

#### 总体上5个命令： 

1. batchAdd: 添加图片或命令, batchDelete: 删除图片或命令 

   input 为String, 样式为：img>> index1, index2, index 3 or cmd>>cmdType(index为software存储buffer中imgProcessor的index)

  

   

   > input举例：
   >
   > 输入图片： img>>0,1,3,2
   >可以提供ip的index: int[]
   
   可以提供command-> object[name][args]
   
   public class xxxcoamdn {
   	private name->switch 
   	private arraylist<>
   	
   	
   }
   > 输入命令： cmd>> edit 4, export 1 ImgResult, edit 2, export 2 ImgResult, filter 6 60

   ##### Template:

   `String tempale = ....`

   	`main_software.setCommand(new batchAdd(null,batchReader.getInstance().parseString(parseString));`

2. batchExport：批处理导出

     ##### Template:

     `main_software.setCommand(new batchExport(null);`

3. batchPreview： 对一张图片预览，不属于命令

    ##### Template: 

    `BufferedImg IKUN =new batchPreview().execute(); `

## 问题： 

*1. 读取command 命令时 输入是 cmd>> cmdType cmdNum cmdParameter, 这个应该和GUI 版本的要求不符合 (已经没有 filter 6 60这种输入模式），需要协商一个可能的解决方案是可以通过一个类似字典的结构 将现在的处理方式映射到之前的方式？
*2. 现在的Exception的处理方式是throw到Main, 然后通过一个统一的exception处理掉，不确定GUI版本的解决方案。我是通过System.out.println(e.getMessage()),返回错误类型，之后可以协商调整
