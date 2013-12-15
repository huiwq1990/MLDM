#-*- encoding:UTF-8 -*-  
import codecs

#将停用词文件的编码格式转化成utf-8编码

url = "D:\\Data\\stopwords.txt"

fOpen = codecs.open(url,'r','gbk')
lines = fOpen.readlines()
fOpen.close()


    
fWrite = codecs.open('stopwords.txt','w','utf-8')

for line in lines:
    print line
    fWrite.write(line)

fWrite.close();
