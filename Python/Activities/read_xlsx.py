import pandas
dataframe = pandas.read_excel("SanjuPujeri.xlsx", sheet_name="Sheet1")
print (dataframe["Usernames"][1])