## 书本管理系统

逻辑实现没问题，现在讲解一些问题和学习方向

- 分层  
现在很流行MVC模式，M 为 模型Model, V 为 视图View, C 为 控制器Controller。
在这里，图书M，菜单的显示可以理解为V，串联菜单操作和图书的管理可以理解为C   
在实际的体现中，分层主要体现在对于源码文件的归类，进行分包

- 对象设计   
在这个系统中，很明显，书籍的相关数据可以封装为一个对象来进行操作，在java中，有万物接对象的概念，需要自己搜索一些资料扩展一下

- 接口设计   
对菜单/图书的储存方式，我们就可以定义一个接口，然后通过不同的实现，来完成不同的效果

- 代码规范   
养成一个好的编码习惯非常重要，代码写出来不仅仅是能运行，更重要的还是需要人能看的懂。   
目前比较主流的Java代码规范为阿里巴巴的[Java开发手册](https://github.com/alibaba/p3c/blob/master/%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4Java%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C%EF%BC%88%E5%8D%8E%E5%B1%B1%E7%89%88%EF%BC%89.pdf)，这个需要熟读一下，除了编码规范，还有很多小技巧可以在今后的编码过程中少踩一些坑

- 用户体验
比如本系统，对于菜单的输入，可以改成直接输入数字，就可以对应指定的菜单最好

看到提交记录，还有删除`.class`文件的commit, 可以了解一下 `.gitignore`文件的作用


## 本次任务
1. 完成所有带有 `// TODO` 注释内未完成的方法
2. 总结一篇文章，思考一下我这样写和你之前的写法有哪些优劣势
3. 完成 TODO 方法后，思考如何使用将图书数据持久化
4. 自行了解一下工厂设计模式