import sqlite3



con = sqlite3.connect("Measure.db")
c = con.cursor()

aon = sqlite3.connect("Aqi.db")
a = aon.cursor()



#productTable
try:
	sql = "DROP TABLE IF EXISTS productTable"
	c.execute(sql)
	sql= "CREATE TABLE productTable(time text primary key not null, temp text, NO2 text, O3 text, CO text, SO2 text, PM25 text)"
	c.execute(sql)
except:
	print("product Table create error")

# aqiTable
try:
	sql = "DROP TABLE IF EXISTS aqiTable"
	a.execute(sql)
	sql= "CREATE TABLE aqiTable(time text primary key not null, no2c text, no2 int, o3c text, o3 int, coc text, co int, so2c text, so2 int, pm25c text, pm25 int)"
	a.execute(sql)
except:
	print("AQI Table create error")


aon.close()
con.close()