#-*- encoding:UTF-8 -*-  
import codecs
import re

fOpen = codecs.open('stopwords.txt','r')
stopwords = fOpen.readlines()
fOpen.close()

stopwordlist = []
for r in stopwords:
    s = r.replace('\n','')
    stopwordlist.append(s)
    print s


print '的'  in stopwordlist