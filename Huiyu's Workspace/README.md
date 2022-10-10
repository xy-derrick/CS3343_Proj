# 批处理进度介绍
## 11/10/22情况：
上次会议后进一步与蒋哥沟通，已经实现了对整体框架的兼容，下一步进行exception handling
计划实现的exception 处理有：
1. BatchDelete： 不存在于batchProcessor的cmd或img
2. BatchAdd 不位于software维护列表的img，或已经在列表中的img 或不存在的命令,或不适合批处理的命令（比如不可undo的）
3. BatchExecute:
4. BatchExport:
5. 优化input部分
## 要确认的问题：
1. 哪些命令不适合批处理
2. switchCommand的不适宜使用
3. 
