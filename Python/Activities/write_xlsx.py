# Import pandas, ExcelFile and ExcelWriter
import pandas
from pandas import ExcelFile
from pandas import ExcelWriter

# Structure our data as a dictionary
data = {
  "Vehicle Type": ["Car", "Car", "Bike","Pulser"],
  "Manufacturer": ["Maruti", "Toyota", "Royal Enfield", "Bajaj"],
  "Model": ["Swift", "Corolla", "Thunderbird", "N160"]
}

# Create a new DataFrame using the data
dataframe = pandas.DataFrame(data)

# Create an ExcelWriter object
writer = ExcelWriter("vehicles.xlsx")

# Write the DataFrame to the Excel file
dataframe.to_excel(writer, "Sheet1", index=False)

# Close the file
writer.close()