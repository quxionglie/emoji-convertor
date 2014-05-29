emoji-convertor
===============

convert emoji to cn

点击[这里](./data/emoji.md)查看表情对应关系

#1如何使用emoji？

###电脑(max os)
使用表情符号非常简单，大部分程序中,点击菜单栏中的"编辑(edit)"/"特殊字符(Special Characters)"，

在弹出的窗口左侧选择表情符号，就能看到几大常见分类的表情，

现在你可以双击喜欢的表情，就可以将该表情插入到文本编辑器中，要在其他地方使用的话直接复制粘贴就可以了。

#2.IOS4,5,6,7版本存在的兼容问题
IOS版本之间发送的Emoji表情符号不兼容，只看到方块.

不同IOS版本在数据库存数据时，有时会发生系统错误.

原因：

ios 7   在ios6基础上, 会在部分unicode编码中添加U+FE0F

iOS 5/6 Emoji采用Unicode 6标准来统一code points

iOS 4   采用SoftBank Unicode,一种非官方的,采用私有Unicode区域[U+E000-U+F8FF]。


Emoji Code | iOS 2 - iOS 4  | iOS 5 - iOS 6     | iOS 7 - current
---        | ---            | ---               | ---
UTF-16     | 0xE246	        | 0x264F	        | 0x264F, 0xFE0F


#3.问题解决
###(1)mysql存储出现问题

原因：

由于IOS5.X以上采用新的Unicode，其UTF8编码大多为4个字节， 而由于column并没设成utf8mb4，因此mysql发生错误。
解决方法：

将column设成utf8mb4

ALTER TABLE topic MODIFY COLUMN content varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容';

###(2)各客户端兼容问题

每个表情对应相应文字,展示时文字表情替换成相应图片展示。

表情、文字与图片对应关系可参考：http://www.emoji-cheat-sheet.com/


#4.扩展阅读
###(1)unicode
http://zh.wikipedia.org/wiki/Unicode

###(2)Unicode字符平面映射
http://zh.wikipedia.org/wiki/Unicode%E5%AD%97%E7%AC%A6%E5%B9%B3%E9%9D%A2%E6%98%A0%E5%B0%84

###(3)UTF-16
http://zh.wikipedia.org/wiki/UTF-16

想更好的理解emoji编码，请阅读以上文章。

我们知道java以unicode编码存储。一个char是2字节(相当于采用utf16编码)。

而emoji表情大部分是在unicode的扩展平面中定义的，

如RAINBOW

Unicode: U+1F308 (U+D83C U+DF08), UTF-8: F0 9F 8C 88

所以java对于上面emoji是以两个char来存储的。

#资源及参考
###(1).Emoji表情符号兼容方案
http://blog.csdn.net/qdkfriend/article/details/7576524


###(2).哪里可以下载到高清 Emoji 全套图标？修改
http://www.zhihu.com/question/20360838

可以在这里 [1] 找到提取 UIKit 中的 Emoji 图标源码，在这里 [2] 可以下载到我提取好的 Emoji 打包图片（共 472 张，64 x 64）。

[1]：https://github.com/0xced/UIKit-Artwork-Extractor

[2]：http://cl.ly/3i0T2l3s0f3P

github啥都有啊。
tmm1/emoji-extractor 路 GitHub 846个，160x160 的。
另外这里 arvida/emoji-cheat-sheet.com · GitHub 有872个，有没见过的，但尺寸只有 64x64。

(3)Emoji 中文对应表
http://www.iapps.im/wp-content/uploads/2012/02/emoji-pinyin.png?r=010

程序的图片来源
http://apps.timwhitlock.info/emoji/tables/unicode#block-6c-other-missing-symbols

64x64图片转成22x22：

```
$ cd emoji-apple-64
$ ls | grep -v "readme.txt" | awk '{print "convert "$0" -quality 100 -resize 22x22 ../emoji-apple-22/"$0}' | sh
```

#ios7中含有U+FE0F的表情(107个)

注意的问题:

(1)在ios浏览器上, ios7上的表情，也能在ios5浏览器上正常显示 。

(2)在mac os上某些unicode会有U+FE0F。手机上不会出现 U+FE0F 字符

```
U+0023 U+FE0F
U+0030 U+FE0F
U+0031 U+FE0F
U+0032 U+FE0F
U+0033 U+FE0F
U+0034 U+FE0F
U+0035 U+FE0F
U+0036 U+FE0F
U+0037 U+FE0F
U+0038 U+FE0F
U+0039 U+FE0F
U+1F004 U+FE0F
U+1F17F U+FE0F
U+1F21A U+FE0F
U+1F22F U+FE0F
U+203C U+FE0F
U+2049 U+FE0F
U+2139 U+FE0F
U+2194 U+FE0F
U+2195 U+FE0F
U+2196 U+FE0F
U+2197 U+FE0F
U+2198 U+FE0F
U+2199 U+FE0F
U+21A9 U+FE0F
U+21AA U+FE0F
U+231A U+FE0F
U+231B U+FE0F
U+24C2 U+FE0F
U+25AA U+FE0F
U+25AB U+FE0F
U+25B6 U+FE0F
U+25C0 U+FE0F
U+25FB U+FE0F
U+25FC U+FE0F
U+25FD U+FE0F
U+25FE U+FE0F
U+2600 U+FE0F
U+2601 U+FE0F
U+260E U+FE0F
U+2611 U+FE0F
U+2614 U+FE0F
U+2615 U+FE0F
U+261D U+FE0F
U+263A U+FE0F
U+2648 U+FE0F
U+2649 U+FE0F
U+264A U+FE0F
U+264B U+FE0F
U+264C U+FE0F
U+264D U+FE0F
U+264E U+FE0F
U+264F U+FE0F
U+2650 U+FE0F
U+2651 U+FE0F
U+2652 U+FE0F
U+2653 U+FE0F
U+2660 U+FE0F
U+2663 U+FE0F
U+2665 U+FE0F
U+2666 U+FE0F
U+2668 U+FE0F
U+267B U+FE0F
U+267F U+FE0F
U+2693 U+FE0F
U+26A0 U+FE0F
U+26A1 U+FE0F
U+26AA U+FE0F
U+26AB U+FE0F
U+26BD U+FE0F
U+26BE U+FE0F
U+26C4 U+FE0F
U+26C5 U+FE0F
U+26D4 U+FE0F
U+26EA U+FE0F
U+26F2 U+FE0F
U+26F3 U+FE0F
U+26F5 U+FE0F
U+26FA U+FE0F
U+26FD U+FE0F
U+2702 U+FE0F
U+2708 U+FE0F
U+2709 U+FE0F
U+270C U+FE0F
U+270F U+FE0F
U+2712 U+FE0F
U+2714 U+FE0F
U+2716 U+FE0F
U+2733 U+FE0F
U+2734 U+FE0F
U+2744 U+FE0F
U+2747 U+FE0F
U+2757 U+FE0F
U+2764 U+FE0F
U+27A1 U+FE0F
U+2934 U+FE0F
U+2935 U+FE0F
U+2B05 U+FE0F
U+2B06 U+FE0F
U+2B07 U+FE0F
U+2B1B U+FE0F
U+2B1C U+FE0F
U+2B50 U+FE0F
U+2B55 U+FE0F
U+303D U+FE0F
U+3297 U+FE0F
U+3299 U+FE0F
```
