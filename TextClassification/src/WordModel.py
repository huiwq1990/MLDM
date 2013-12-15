#encoding=utf-8
import codecs


#构造停用词表
#生成停用词列表
def FilterNoiseWord(stopword_file_name):
    import re
    f=file(stopword_file_name)
    stopword=f.read()
    f.close()
    stopwordlist=re.split('\n',stopword)
    return stopwordlist


print FilterNoiseWord('stopwords.txt')