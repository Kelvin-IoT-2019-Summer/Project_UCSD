import time


def map_select_bit_to_gpio_pin(bit):
	if bit == 0:
		return 24
	if bit == 1:
		return 25
	if bit == 2:
		return 26
	if bit == 3:
		return 27


def s_mask(bit):
	if bit == 0:
		return 1
	if bit == 1:
		return 2
	if bit == 2:
		return 4
	if bit == 3:
		return 8


def s_ch(bit):
	if bit == 2:
		return 1
	if bit == 4:
		return 2
	if bit == 8:
		return 3
	if bit == 16:
		return 4
	if bit == 32:
		return 5


def write_bit_to_gpio_pin(pin, value):
	# here is the code to write "value" to pin #
        if value == 0:
            filename = "/gpio/pin" + str(pin) + "/value"
            file = open(filename, 'w')
            file.write("0")
            file.close()
        if value == 1:
            filename = "/gpio/pin" + str(pin) + "/value"
            file = open(filename, 'w')
            file.write("1")
            file.close()


def mux_select_and_set(channel, num, en=True):
	s = [0 for i in range(s_ch(num))]
	for i in range(s_ch(num)):
		s[i] = (channel & s_mask(i)) >> i
		write_bit_to_gpio_pin(map_select_bit_to_gpio_pin(i), s[i])


class mux:
	def __init__(self,  number_of_channels, en_pin, signal_pin, select_pins, active_low=True):
		self.en_pin = en_pin
		self.select_pins = select_pins
		self.number_of_channels = number_of_channels
		self.signal_pin = signal_pin


	def enable(self):
		write_bit_to_gpio_pin(self.en_pin, 0) # mux enable is active low
	

	def disable(self):
		write_bit_to_gpio_pin(self.en_pin, 1)


	def select_channel(self, ch):
		mux_select_and_set(ch, self.number_of_channels)
		return 0


	def read_signal(self):
		# ADC reading code
		sig_dict = {0:"0/in_voltage0", 1:"0/in_voltage1", 2:"0/in_voltage2", 3:"0/in_voltage3", 4:"1/in_voltage0", 5:"1/in_voltage1"}
		raw = int(open("/sys/bus/iio/devices/iio:device"+ sig_dict[self.signal_pin] + "_raw").read())
		scale = float(open("/sys/bus/iio/devices/iio:device0/in_voltage_scale").read())
		return raw*scale


	def oversampling(self, num):
		val = 0
		for i in range(num):
			val += self.read_signal()
		val = val / num
		return val


def SO2(WE, AE, T):
	SO2_dict = {-3:0.85, -2:0.85, -1:0.85, 0:0.85, 1:0.85, 2:1.15, 3:1.45, 4:1.75, 5:1.95}
	num = T//10
	if num in SO2_dict:
		t = SO2_dict.get(num)
	ppb = ((WE-333) - t*(AE-274)) / 0.288
	return ppb


def O3(WE, AE, T):
	O3_dict = {-3:0.18, -2:0.18, -1:0.18, 0:0.18, 1:0.18, 2:0.18, 3:0.18, 4:0.18, 5:2.87}
	num = T//10
	if num in O3_dict:
		t = O3_dict.get(num)
	ppb = ((WE-418) - t*(AE-404)) / 0.393
	return ppb


def CO(WE, AE, T):
	CO_dict = {-3:1.40, -2:1.03, -1:0.85, 0:0.62, 1:0.30, 2:0.03, 3:-0.25, 4:-0.48, 5:-0.80}
	num = T//10
	if num in CO_dict:
		t = CO_dict.get(num)
	ppb = ((WE-345) - t*(AE-315)) / 0.292
	return ppb


def NO2(WE, AE, T):
	NO2_dict = {-3:1.18, -2:1.18, -1:1.18, 0:1.18, 1:1.18, 2:1.18, 3:1.18, 4:2.00, 5:2.70}
	num = T//10
	if num in NO2_dict:
		t = NO2_dict.get(num)
	ppb = ((WE-287) - t*(AE-292)) / 0.258
	return ppb


def temperature(V):
	T = (V - 790) / 10 + 25
	return T


def PM25(V):
	hppcf = 50 + (2433*V) + (1386*V**2)
	pm = 0.518 + 0.00274*hppcf
	return pm