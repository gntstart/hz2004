CAB包测试步骤
1.安装证书ImageCtl.cer 到受信任的根目录
2.把CAB包放到服务器上面，html 里面添加上 codebase="ImageCtl.CAB#version=1,0,0,1"  这个，这里要求CAB包放到html页面所在的目录，当然也可以放到不同的目录，但是要修改codebase="ImageCtl.CAB#version=1,0,0,1"  这个才行
3.访问html会自动下载安装CAB包
注意下载安装CAB包最好包测试的那个OCX   regsvr32 /u ImageActiveX.ocx   反注册掉，不然没法判断到底运行的是哪个程序

另外WIDTH默认是130  HEIGHT默认是160     13/16  这个是二代身份证照的宽高比
<OBJECT ID="ImageCtrl" style="position:fixed;left:800px" WIDTH=130 HEIGHT=160 classid="CLSID:A4E862CA-1750-453B-A2FE-97006D5246D4" codebase="ImageCtl.CAB#version=1,0,0,1">