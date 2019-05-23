# ApiRouter
接口与实现隔离依赖，根据路由字符串查找目标Class

打包
gradle clean assembleDebug



## APT版本兼容的问题

> @AutoService(Processor.class)无效，需要在自定义的Processor库里面需要 手动注册自定义的APT类！
[参考地址](https://github.com/Sum-sdl/ApiRouter/tree/master/router-complier/src/main/resources/META-INF/services)

```
手动注册方法：
1.在main目录创建一个 'resources' 文件夹
2.创建一个'META-INF.services' 文件夹
3.创建一个文件："javax.annotation.processing.Processor" 
4.在文件中添加自定义的APT类的全路径
```
