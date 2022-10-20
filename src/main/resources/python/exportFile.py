
from tokenize import String
import pandas as pd
import sys
import os
from sklearn.metrics import accuracy_score
from sklearn.feature_extraction.text import CountVectorizer, TfidfVectorizer
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.svm import SVC  # Support Vector Machine
from sklearn.pipeline import Pipeline
from gensim import parsing  # To stem data
from sklearn.model_selection import train_test_split
import pickle
workingdirectory = os.getcwd

url_full_train_data_2c = "C:\\Users\\Thang Nguyen\\Desktop\\jobs\\PTHTDTT_Data_DienThoai.xls"
df = pd.read_excel(url_full_train_data_2c, 'Sheet2')


def parse(s):
    parsing.stem_text(s)
    return s


X_train = df.iloc[:, 1:6].values
y_train = df.iloc[:, 6].values
model = SVC(kernel='rbf', random_state=1, C=1)
model.fit(X_train, y_train)
filename = 'Data(0-1).sav'
pickle.dump(model, open(filename, 'wb'))