#-*- encoding:UTF-8 -*-  
import jieba
import codecs

#对文件进行分词
url = 'D:/Data/SogouUtf-8/C000008/10.txt'
content = open(url,"r").read()

words = list(jieba.cut(content))



segFileWriter = codecs.open('aa','w','utf-8')
for w in words:
	line = w+'/'
	print line
	segFileWriter.write(line.encode('utf-8'))

segFileWriter.close()
