import mux
import udoo_pin
import sqlite3
from datetime import datetime
import time
import json
import aqi



def measure():
	aqi_dict = {-1:"#FFFFFF", 0:"#1CC88A", 1:"#FFD700", 2:"#FFA500", 3:"#FF0000", 4:"#800080", 5:"#800000", 6:"#800000"}
	con = sqlite3.connect("Measure.db")
	c = con.cursor()
	
	aon = sqlite3.connect("Aqi.db")
	a = aon.cursor()
	
	"""
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
	"""


	for i in range(24, 29):
		pin_direction = open("/gpio/pin" + str(i) + "/direction", 'w')
		pin_direction.write("out")
		pin_direction.close()

	avg = 24

	ms = mux.mux(16, 28, 0, [24, 25, 26, 27])
	ms.enable()

	ms.select_channel(udoo_pin.p_TMP())
	t = ms.oversampling(avg)
	temp = round(mux.temperature(t),1)

	ms.select_channel(udoo_pin.p_NO2_WE())
	WE = ms.oversampling(avg)
	ms.select_channel(udoo_pin.p_NO2_AE())
	AE = ms.oversampling(avg)
	NO2 = round(mux.NO2(WE, AE, temp),-1)

	ms.select_channel(udoo_pin.p_O3_WE())
	WE = ms.oversampling(avg)
	ms.select_channel(udoo_pin.p_O3_AE())
	AE = ms.oversampling(avg)
	O3 = round(mux.O3(WE, AE, temp),3)

	ms.select_channel(udoo_pin.p_CO_WE())
	WE = ms.oversampling(avg)
	ms.select_channel(udoo_pin.p_CO_AE())
	AE = ms.oversampling(avg)
	CO = round(mux.CO(WE, AE, temp),1)

	ms.select_channel(udoo_pin.p_SO2_WE())
	WE = ms.oversampling(avg)
	ms.select_channel(udoo_pin.p_SO2_AE())
	AE = ms.oversampling(avg)
	SO2 = round(mux.SO2(WE, AE, temp),-1)

	ms.select_channel(udoo_pin.p_PM25())
	V = ms.oversampling(avg)
	PM25 = round(mux.PM25(V),1)

	timestamp = str(datetime.now())[:-7]
	try:
		c.execute("INSERT INTO productTable VALUES(?, ?, ?, ?, ?, ?, ?)", (timestamp, temp, NO2, O3, CO, SO2, PM25))
	except:
		print("Data insert error")
		exit()
	con.commit()

#-----------------------------------------------------------------------------------------------------------------------------------------

	c.execute("SELECT avg(NO2) FROM productTable ORDER BY time DESC LIMIT 3600")
	no2 = c.fetchall()
	no2 = int(no2[0][0])
	no2 = aqi.no2(no2)
	no2c = aqi_dict[no2[0]]

	c.execute("SELECT avg(O3) FROM productTable ORDER BY time DESC LIMIT 3600")
	o3 = c.fetchall()
	o3 = round(float(o3[0][0]),3)
	o3 = aqi.o3(o3)
	o3c = aqi_dict[o3[0]]

	c.execute("SELECT avg(CO) FROM productTable ORDER BY time DESC LIMIT 3600")
	co = c.fetchall()
	co = round(float(co[0][0]),1)
	co = aqi.co(co)
	coc = aqi_dict[co[0]]

	c.execute("SELECT avg(SO2) FROM productTable ORDER BY time DESC LIMIT 3600")
	so2 = c.fetchall()
	so2 = int(so2[0][0])
	so2 = aqi.so2(so2)
	so2c = aqi_dict[so2[0]]

	c.execute("SELECT avg(PM25) FROM productTable ORDER BY time DESC LIMIT 3600")
	pm25 = c.fetchall()
	pm25 = round(float(pm25[0][0]),1)
	pm25 = aqi.pm25(pm25)
	pm25c = aqi_dict[pm25[0]]

	try:
		a.execute("INSERT INTO aqiTable VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", (timestamp, no2c, no2[1], o3c, o3[1], coc, co[1], so2c, so2[1], pm25c, pm25[1]))
	except:
		print("AQI Data insert error")
		exit()

	aon.commit()
	aon.close()
	con.close()



def delete():
	con = sqlite3.connect("Measure.db")
	c = con.cursor()
	aon = sqlite3.connect("Aqi.db")
	a = aon.cursor()
	
	timestamp = str(datetime.now())[:-7].replace(str(datetime.now())[11:13], str(int(str(datetime.now())[11:13])-1))
	ccc = "DELETE from productTable ORDER BY time DESC LIMIT 1 OFFSET 3600"
	aaa = "DELETE from aqiTable ORDER BY time DESC LIMIT 1 OFFSET 3600"
	c.execute(ccc)
	a.execute(aaa)
	con.commit()
	aon.commit()
	con.close()
	aon.close()


def command(data):

	if data == "Mvalue":
		con = sqlite3.connect("Measure.db")
		c = con.cursor()
		c.execute("SELECT * FROM productTable ORDER BY time DESC LIMIT 2")
		aon = sqlite3.connect("Aqi.db")
		a = aon.cursor()
		a.execute("SELECT * FROM aqiTable ORDER BY time DESC LIMIT 2")
		JSON_array = []
		
		rows = (c.fetchall(), a.fetchall())
		rows_zip = map(list, zip(*rows))

		for r in rows_zip:
			JSON_string = {
			"temperature": r[0][1],
			"NO2": r[0][2], "NO2_AQI": r[1][2], "NO2_AQI_C": r[1][1],
			"O3": r[0][3], "O3_AQI": r[1][4], "O3_AQI_C": r[1][3],
			"CO": r[0][4], "CO_AQI": r[1][6], "CO_AQI_C": r[1][5],
			"SO2": r[0][5],"SO2_AQI": r[1][8], "SO2_AQI_C": r[1][7],
			"PM2.5": r[0][6],"PM2.5_AQI": r[1][10], "PM2.5_AQI_C": r[1][9],
			"datetime": r[0][0]
			}
			JSON_array.append(JSON_string)

		y = json.dumps(JSON_array)
		con.close()
		aon.close()
		return y

	# if data == "aqi":
		# aon = sqlite3.connect("Aqi.db")
		# a = aon.cursor()
		# a.execute("SELECT * FROM aqiTable ORDER BY time DESC LIMIT 4")
		
		# rows = a.fetchall()
		# JSON_array = []
		
		# for i in rows:
				# JSON_string = {
				# "NO2_AQI": i[2], "NO2_AQI_C": i[1],
				# "O3_AQI": i[4], "O3_AQI_C": i[3],
				# "CO_AQI": i[6], "CO_AQI_C": i[5],
				# "SO2_AQI": i[8], "SO2_AQI_C": i[7],
				# "PM2.5_AQI": i[10], "PM2.5_AQI_C": i[9]
				# }
				# JSON_array.append(JSON_string)

		# y = json.dumps(JSON_array)
		# aon.close()
		# return y

