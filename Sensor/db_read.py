import sqlite3

# con = sqlite3.connect("Measure.db")
# c = con.cursor()
# c.execute("SELECT * FROM productTable")
# print("\ntimestamp\t\t temp\t NO2\t O3\t\t CO\t SO2\t PM25")
# print("-----------------------------------------------------------------------------")
# while (True):
	# row = c.fetchone()
	# if row == None: break
	# print "%s\t %s\t %s\t %7s\t %s\t %s\t %s\t" %(row[0], row[1], row[2], row[3], row[4], row[5], row[6])

# con.close()

con = sqlite3.connect("Aqi.db")
c = con.cursor()
c.execute("SELECT * FROM aqiTable")
print("\ntimestamp\t\t NO2c\t NO2\t O3c\t O3\t COc\t CO\t SO2c\t SO2\t PM25c\t PM25\t")
print("-------------------------------------------------------------------------------------------------------")
while (True):
	row = c.fetchone()
	if row == None: break
	print "%s\t %s %d\t %s %d\t %s %d\t %s %d\t %s %d\t" %(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10])

con.close()

