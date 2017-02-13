# img-cut
## 介绍
通过过滤器实现图片的等比例压缩

## 示例
原图：http://localhost:8080/imgs/123.jpg
获取50x50：http://localhost:8080/imgs/123.jpg_50x50.jpg
获取100x100：http://localhost:8080/imgs/123.jpg_100x100.jpg

## Quick start
在web.xml中配置过滤器即可

## 配合nginx使用
在Nginx中判断访问的图片是否存在，如果不存在，交给tomcat处理
```shell
location ~.*\.(htm|html|gif|jpg|jpeg|png|ico|rar|css|js|zip|txt|flv|swf|doc|ppt|xls|pdf)$ {
     if (!-e $request_filename) {
         proxy_pass http://127.0.0.1:8080;
     }
     root /home/tomcat/web/tomcat/tomcat/webapps/ROOT;
     access_log off;
     expires 30d;
}
```
