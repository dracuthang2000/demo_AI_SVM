import sys
import os
import pickle
from tokenize import String
import pandas as pd
workingdirectory = os.getcwd

brand = sys.argv[2]
cpu = sys.argv[3]
ram = sys.argv[4]
ppi = sys.argv[5]
rom = sys.argv[6]
df1 = pd.DataFrame({"brand":[float(brand)],
                    "CPU":[float(cpu)],
                   "RAM":[float(ram)],
                   "ppi":[float(ppi)],
                   "rom":[float(rom)],
                   })
filename = sys.argv[1]
loaded_model = pickle.load(open("D:\\PTIT_MTT\\PTUDTT\\Phone_AI\\src\\main\\resources\\python\\"+filename, 'rb'))
predict = loaded_model.predict(df1)
print(predict[0])