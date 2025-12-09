import pandas as pd

# Reading an XML file into a DataFrame
df = pd.read_xml("properties.xml")

print(df)