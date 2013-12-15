#encoding=utf-8
import jieba



w= open('result.txt','w') 

s="圣诞消费旺季即将到来，不得不推迟出货"
seglist = list(jieba.cut(s,cut_all=False)) 
print ", ".join(seglist)
for i in seglist:
    w.write(i.encode('utf-8')) #或者 w.write(i.encode('gbk')) 
    w.write("\n")
w.close() 
