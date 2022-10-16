# 批处理进度介绍
## 17/10/22情况：
完成了批处理的异常处理和内部测试，形成SemiFinal 等待最终合并
### 代码介绍：
1. 自己的代码放置在Command的Huiyu BAI文件夹下
2. 修改了Main.java 和ArgsRead.java 修改的部分除了方法的throws其他均用//--------------------括起来
### 具体使用
1. 批处理添加 **batchAdd**：
  batch 1 回车后输入img>>idx, 或cmd>>cmdType cmdNum cmdParameters,...
2. 批处理删除 **batchDelete**：
  batch 2 回车后与添加操作相同
3. 批处理执行 **batchExecute** （可undo,redo）
  batch 3
4. 批处理导出 **batchExport** (不可undo)
  batch 4
5. 批处理undo（暂定
  batch 5
6. 批处理redo（暂定
  batch 6
### 下一步要解决的问题：
1. batch 的undo和redo，是需要单独提出来（目前为此），还是说在main中设置一个batchFlag 为1。Export或undo时设置为0.第二种方法需要达成共识，批处理的最后一步应该为导出。
2. 确定哪些命令是可以用在批处理上的，把那些写入batchableCommandDict.java
3. 和所有人的代码合并并测试。不出所料应该会有bug，然后解决
