#encoding=utf-8
#此模块用于建立词袋子模型

def BagOfWordsConstruction(root,toCalInfoGain):
    if toCalInfoGain==0:
        
        import cPickle as mypickle
        file_dest=file(r'D:\TextCategorization\VITdata\vocabularystatistics.dat','w')
        rawVSMMatrix=TrainingFileProcess(root)
        vocabularystatistics={}
        templist=[]
        for rawVSM in rawVSMMatrix:
            templist=templist+rawVSM
        wordscollection=list(set(templist))
    
        for word in wordscollection:
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
        file_dest.close()
    print 'BagOfWordsConstructionFinish'
       
############################################################################################
#将文章内容变成词集合    
def FilePreProcess(rawtext):
    import re
    listresult=rawtext.split("|")
    finalrawVSM=[]
    stopwordlist=FilterNoiseWord(r'C:\Python26\SVM\stopwords.txt')
    for m in listresult:
         if m!=''and m not in stopwordlist and re.search('\xa3[\xa1-\xfe]',m)==None:
            finalrawVSM.append(m)
                     
    return finalrawVSM

#################################################################################################
#训练集文档预处理
def TrainingFileProcess(root):
    from SVM import DataManager
    import cPickle as mypickle
    import os
   
    rawVSMMatrix=[]#存放整个文档集
    dm=DataManager.DataManager(root)
    subdir=dm.GetSubDir()
    for sub in subdir:
        dm.SetFilePathsFromsubDir(root+os.sep+sub)
        filepaths=dm.GetFilePaths()
        for path in filepaths:
            myfile=file(root+os.sep+sub+os.sep+path)
            rawtext=myfile.read()
            myfile.close()
            rawVSM=FilePreProcess(rawtext)
            rawVSMMatrix.append(rawVSM)
    return rawVSMMatrix


####################################################################################
#生成停用词列表
def FilterNoiseWord(stopword_file_name):
    import re
    f=file(stopword_file_name)
    stopword=f.read()
    f.close()
    stopwordlist=re.split('\n',stopword)
    return stopwordlist
    

   
if __name__=="__main__":
    BagOfWordsConstruction(r'D:\TextCategorization\segmented')
    #fid=file(r'D:\3011.txt')
    #rawtext=fid.read()
    #fid.close()
    #FilePreProcess(rawtext)
    

        
    

    
