#!/usr/bin/env python
# coding: utf-8

# In[390]:


import pandas as pd
import sys
import os
from sklearn.metrics import accuracy_score
from sklearn.feature_extraction.text import CountVectorizer, TfidfVectorizer  # convert text comment into a numeric vector
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.svm import SVC  # Support Vector Machine
from sklearn.pipeline import Pipeline
from gensim import parsing  # To stem data
from sklearn.model_selection import train_test_split
workingdirectory = os.getcwd


# In[391]:


url_full_train_data_2c = "C:\\Users\\Thang Nguyen\\Desktop\\jobs\\PTHTDTT_Data_DienThoai.xls"
# Read csv into a dataframe
df = pd.read_excel(url_full_train_data_2c, sys.argv[1])
# In[392]:
def parse(s):
    parsing.stem_text(s)
    return s
# In[393]:
X_train = df.iloc[:,1:6].values
y_train = df.iloc[:,6].values
# In[394]:
# In[395]:
text_clf = SVC(kernel='rbf',random_state=1)
text_clf.fit(X_train, y_train)
# predict class form test data


# In[396]:


# data = [{10 ,  94,    4,  460,  256}]
df1 = pd.DataFrame({"brand":[sys.argv[2]],
                    "CPU":[sys.argv[3]],
                   "RAM":[sys.argv[4]],
                   "ppi":[sys.argv[5]],
                   "rom":[sys.argv[6]],
                   })




predict = text_clf.predict(df1)
print(predict[0])


# In[ ]:




