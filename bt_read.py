import mux
import udoo_pin
import db_measure
from bluetooth import *
from datetime import datetime



server_sock = BluetoothSocket(RFCOMM)
server_sock.bind(("",PORT_ANY))
server_sock.listen(1)

port = server_sock.getsockname()[1]
uuid = "94f39d29-7d6d-437d-973b-fba39e49d4ee"
advertise_service(server_sock, "BluetoothServerMark", service_id = uuid, service_classes = [uuid, SERIAL_PORT_CLASS], profiles = [SERIAL_PORT_PROFILE])
print "Waiting for connection on RFCOMM channel {0}".format(port)


server_sock.settimeout(5)

try:
	client_sock = None
	client_sock, client_info = server_sock.accept()
	print "Accepted connection from {0}".format(client_info[0])
except:
	print "Socket Timed out!"


client_sock.settimeout(1)
cnt = 0
try:
    while client_sock != None:
		try:
			if cnt//3==1:
				client_sock.send(db_measure.command("Mvalue"))
				# client_sock.send(db_measure.command("aqi"))
				cnt = 0
			data = client_sock.recv(1024)
			if data:
				print "Received: {0}".format(data)
		except:
			db_measure.measure()
			db_measure.delete()
			cnt+=1



except Exception as e:
    print "Error: {0}".format(repr(e))

print "Disconnected!"
client_sock.close()
server_sock.close()
print "Sockets closed. Bye!"