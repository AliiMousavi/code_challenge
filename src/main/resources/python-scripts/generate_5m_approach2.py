import pandas as pd
import time
import random
import os

# ساخت پوشه موقت روی درایو C (معمولاً SSD)
os.makedirs(r"C:\temp_5m", exist_ok=True)

print("تولید ۵ میلیون رکورد روی SSD (سرعت واقعی)...")
start = time.time()

first_names = ["علی","رضا","حسن","محمد","مهدی","امیر","حسین","میلاد","سجاد","پویا",
               "زهرا","فاطمه","مریم","سارا","نرگس","لیلا","پریسا","مهسا","الناز","مینا"]
last_names = ["محمدی","رضایی","حسینی","احمدی","علوی","نوری","اکبری","قاسمی","جعفری","کریمی"]

random.seed(42)

data = {
    'first_name': random.choices(first_names, k=5_000_000),
    'last_name':  random.choices(last_names,  k=5_000_000),
    'national_id': [f"{1000000000 + i:010d}" for i in range(5_000_000)],
    'created_at': [pd.Timestamp('now')] * 5_000_000
}

df = pd.DataFrame(data)
df.to_csv(r"C:\temp_5m\5m_persons.csv", index=False, header=False)

elapsed = time.time() - start
print(f"Done! 5 million records generated in {elapsed:.2f} seconds")
print("File saved at: C:\\temp_5m\\5m_persons.csv")
input("Enter...")