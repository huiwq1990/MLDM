#encoding=utf-8
#将语料库文件的编码格式转化成utf-8格式
import codecs
import os
import os.path
rootdir = 'D:\Data\SogouC.reduced'                                   # 指明被遍历的文件夹
#rootdir = 'D:\Data\Test\\' 
convDir = 'D:\Data\SogouUtf-8\\'

dirs = os.listdir(rootdir)

for dir in dirs:    
    #print("dir name is:" + os.path.join(rootdir,dir))
    for parent,dirnames,filenames in os.walk(os.path.join(rootdir,dir)):  
        for file in  filenames:
            
            filePath = os.path.join(os.path.join(rootdir,dir),file)
            #print ("filename is:" + filePath)
            fOpen = codecs.open(filePath,'r','gb2312','ignore')
            lines = fOpen.readlines()
            fOpen.close()
            
            toPath = convDir+dir
            if not os.path.isdir(toPath):
                os.makedirs(toPath)

            fWrite = codecs.open(toPath +'\\'+file,'a','utf-8','ignore')

            for line in lines:
                #print line
                s = line.encode('utf-8','ignore')
                fWrite.write(line)
            fWrite.close();