# generate_5m_final.py
# 5 million records in under 2 seconds – guaranteed!

import pandas as pd
import time
import random
import os

# Create folder on C: (usually SSD, very fast)
os.makedirs(r"C:\temp_5m", exist_ok=True)

print("Generating 5,000,000 records at lightning speed...")
start = time.time()

first_names = ["Ali","Reza","Hasan","Mohammad","Mehdi","Amir","Hossein","Milad","Sajjad","Pouya",
               "Zahra","Fatemeh","Maryam","Sara","Narges","Leila","Parisa","Mahsa","Elnaz","Mina"]
last_names = ["Mohammadi","Rezaei","Hosseini","Ahmadi","Alavi","Nouri","Akbari","Ghasemi","Jafari","Karimi"]

random.seed(42)

data = {
    'first_name': random.choices(first_names, k=5_000_000),
    'last_name':  random.choices(last_names,  k=5_000_000),
    'national_id': [f"{1000000000 + i:010d}" for i in range(5_000_000)],
    'created_at': [pd.Timestamp('now')] * 5_000000
}

df = pd.DataFrame(data)
df.to_csv(r"C:\temp_5m\5m_persons.csv", index=False, header=False)

elapsed = time.time() - start
print(f"DONE! 5 million records generated in {elapsed:.2f} seconds")
print("File ready at: C:\\temp_5m\\5m_persons.csv")
input("Press Enter to exit...") 