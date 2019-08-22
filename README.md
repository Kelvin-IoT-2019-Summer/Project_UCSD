# Measuring AQI
This is a **AQI project** for measuring that includes NO2, O3, CO, SO2 and PM2.5 and for communicating with App through Bluetooth.

Base on https://github.com/pybluez/pybluez

## Installation
Code If you have git installed, clone the repository.

     sudo pip install pybluez
     git clone git@github.com:Kelvin-IoT-2019-Summer/Project_UCSD.git
## Running
Download a application about this.

To run the application run the following command

     sudo nano /etc/machine-info 
PRETTY_HOSTNAME=XXXX_UDOO

     sudo service bluetooth restart
     sudo hciconfig hci0 piscan
     sudo bluez-simple-agent
Tap XXXX_UDOO on phone Yes on both phone and UDOO

     sudo sdptool add sp
After phone has already paired with udoo:

     sudo hciconfig hci0 piscan
     sudo bluez-simple-agent
Tap connect on phone Yes on udoo Ctrl + c

     sudo sdptool add sp
     python create_db.py
     python bt_read.py
Tap connect on android terminal app

Demo and Build Instructions If you want to get measured date, following command

     python db_read.py

![Instructure](https://github.com/Kelvin-IoT-2019-Summer/Project_UCSD/blob/bran/Instructure.jpg)
![db_table](https://github.com/Kelvin-IoT-2019-Summer/Project_UCSD/blob/bran/db_table.png)
![db_table](https://github.com/Kelvin-IoT-2019-Summer/Project_UCSD/blob/bran/Sensor.png)
