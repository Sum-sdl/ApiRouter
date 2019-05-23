# ApiRouter
接口与实现隔离、简洁界面路由

打包
gradle clean assembleDebug


## processor兼容的问题

>. 最好在自定义的Processor库里面添加手动添加注册的APT
方法：在main目录创建一个 'resources' 文件夹，并创建一个META-INF.services 文件夹
在该文件中创建一个文件："javax.annotation.processing.Processor" 并在该文件中添加自定义的APT的全路径