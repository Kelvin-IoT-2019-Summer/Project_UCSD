for n in range(24,29):
	print "pin Number:", n
	direction = open("/gpio/pin"+str(n)+"/direction").read()
	print direction

	value = int(open("/gpio/pin"+str(n)+"/value").read())
	print value
	print
