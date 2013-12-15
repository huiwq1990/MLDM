# -*- coding: cp936 -*-
'''
    ��ģ�������γɴʴ���ģ�ͣ����ʼ��㷽ʽ���ö��ز�Ŭ��ģ��
'''
import cPickle as mypickle
from numpy import *
import MySQLdb
import re
def generateFileLabels(begin,end):
    fileLabels=[]
    for i in range(begin,end):
        s='class1_'+str(i)
        fileLabels.append(s)
    for i in range(begin,end):
        s='class2_'+str(i)
        fileLabels.append(s)
    return fileLabels
#############################################
#N���ĵ�����ÿ����ĵ���Ŀ
#vfold ����ʵ��
#count �Ѿ������˵ڼ���ʵ��
#################################################
def BagOfWordsConstruction(N,count,vfold=5):
    timer=0
    step=N/vfold
    begin=0+count*step
    end=step+count*step
    print '��ʼ���±��%s'%begin
    print 'ĩβ���±��%s'%end
    fid=file('vocabularystatistic.dat','w')
    #(term_id,class1No,class2No)�ֵ�ļ�ֵΪ�ʵ��ı�����ϢΪ�ôʵ�id��ţ��ڵ�һ���г��ֵĴ������ڵڶ����г��ֵĴ���)
    db=MySQLdb.connect(user='root',db='nlpprocess',passwd='finally',host='localhost')
    cursor=db.cursor()
    storeOfFileLabels=generateFileLabels(begin,end)
    mydict={}
    select='select term_id,stemroot,file_id_collection from nlpinvert'
    cursor.execute("set NAMES GBK")
    cursor.execute(select)
    rows=cursor.fetchall()
    cursor.close()
    db.close()
    for row in rows:
        classNo1=0
        classNo2=0
        fileLabelCollection=row[2]
        totalFileLabelCount=len(storeOfFileLabels)
        for i in range(0,totalFileLabelCount/2):
            if fileLabelCollection.find(storeOfFileLabels[i])!=-1:
                classNo1=classNo1+1
        for i in range(totalFileLabelCount/2,totalFileLabelCount):
            if fileLabelCollection.find(storeOfFileLabels[i])!=-1:
                classNo2=classNo2+1     
        if classNo1>0 or classNo2>0:
            timer=timer+1
            tmp_tuple=(row[0],classNo1,classNo2)
            mydict[row[1]]=tmp_tuple
            #print 'finish%s,%s,%s,%s'%(row[0],row[1],classNo1,classNo2)
             
            
    print '����%s����ֵ'%len(mydict.keys())
    print timer
    mypickle.dump(mydict,fid)
    fid.close()
    print 'final finish congratulations'
    
if __name__=="__main__":
   
    
    BagOfWordsConstruction(500,3)
    
        
    
