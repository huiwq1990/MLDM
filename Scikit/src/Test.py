'''
Created on 2013-1-19

@author: hg
'''

import numpy as np
from sklearn import datasets

from sklearn import svm
from sklearn import linear_model
#iris = datasets.load_iris()
digits = datasets.load_digits()

#print(digits.data)
#print(digits.target)
print(digits.data.shape)
#print(digits.DESCR)
clf = svm.SVC(gamma=0.001, C=100.)
clf.fit(digits.data[:-1], digits.target[:-1])
print(clf.predict(digits.data[-1]))


X = np.c_[ .5, 1].T
y = [.5, 1]
test = np.c_[ 0, 2].T
regr = linear_model.LinearRegression()
