#-*- encoding:UTF-8 -*-  
import codecs
import jieba
import re
####################################################################################
#生成停用词列表
def FilterNoiseWord(stopword_file_name):
    fOpen = open(stopword_file_name,'r')
    stopwords = fOpen.readlines()
    fOpen.close()

    stopwordlist = []
    for r in stopwords:
        s = r.replace('\n','')
        stopwordlist.append(s)
        #print s

    return stopwordlist
    

   
############################################################################################
#将文章内容变成词集合    
def FilePreProcess(filepath,stopwordlist):

    #对语料库文件进行分词
    fileContent = open(filepath,"r").read()
    fileWords = list(jieba.cut(fileContent))


    
    finalrawVSM=[]
    for m in fileWords:
        #取出中文及英文空格
        s = m.strip().replace('　','')
        #去除停用词
        if s!=''and s not in stopwordlist and re.search('\xa3[\xa1-\xfe]',s)==None:
            finalrawVSM.append(s)
            #print s
                     
    return finalrawVSM
#################################################################################################


#################################################################################################
#训练集文档预处理
def TrainingFileProcess():
    import os
    rootdir = 'D:\\Data\\SogouUtf-8\\'  
    dir = 'test'
    
    stopwordlist=FilterNoiseWord('stopwords.txt')
    rawVSMMatrix=[]#存放整个文档集
    
    for parent,dirnames,filenames in os.walk(os.path.join(rootdir,dir)):  
        for file in  filenames:
            
            filePath = os.path.join(os.path.join(rootdir,dir),file)
           
            rawVSM=FilePreProcess(filePath,stopwordlist)
            rawVSMMatrix.append(rawVSM)
    return rawVSMMatrix

   

#此模块用于建立词袋子模型
def BagOfWordsConstruction(root,toCalInfoGain):
    if toCalInfoGain==0:
        
        import cPickle as mypickle
        file_dest=file(r'D:\Data\vocabularystatistics.dat','w')
        #训练语料库
        rawVSMMatrix=TrainingFileProcess()
        
        #将语料库所有文件中出现的词统计一下
        templist=[]
        for rawVSM in rawVSMMatrix:
            templist=templist+rawVSM
        wordscollection=list(set(templist))#set取出list中重复元素
        
        vocabularystatistics={} #统计结果
        for word in wordscollection:
            print word
            index=0
            for rawVSM in rawVSMMatrix:
                count=rawVSM.count(word)
                if count>0 :
                    if vocabularystatistics.has_key(word)==False:
                        vocabularystatistics[word]=[]
                        vocabularystatistics[word].append((index,count))
                    else:
                        vocabularystatistics[word].append((index,count))
                index=index+1
        mypickle.dump(vocabularystatistics,file_dest)
        print len(vocabularystatistics)
#        for k in vocabularystatistics:
#            print k
        print vocabularystatistics.get('财经'.decode('utf-8'))
        print vocabularystatistics.has_key('财经'.decode('utf-8'))
#        for k in vocabularystatistics.keys():
#            print k
        file_dest.close()
    print 'BagOfWordsConstructionFinish'


if __name__=="__main__":
   
    BagOfWordsConstruction('',0)