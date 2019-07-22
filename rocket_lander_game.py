# Kelvin
# 2019/07/22
# setting up the rocket class

GRAVITY = -10
T = 0.5


class Rocket:
    """This is a rocket.
    Please don't crash!"""

    count = 0

    def __init__(self, new_rocket_name, new_rocket_fuel=100):
        self.name = name

        self.p = 100 # position
        self.v = 0   # velocity
        self.f = new_rocket_fuel # fuel
        self.a = 0   # acceleration
        self.t = 0   # thrust

        self.landed = False
        self.success = False

        Rocket.count += 1

    def update(self):
        self.f = self.f - (self.t * T)
        self.a = GRAVITY + self.t
        self.p = self.p + (self.v * T) + (0.5 * self.a * T**2)
        self.v = self.v + (self.a * T)
        self.p = max(self.p, 0)
        if self.p == 0:
            self.landed = True
			Rocket.count -= 1
            if abs(self.v) <= 3:
                self.success = True

    def get_thrust(self):
        if self.f > 0:
            self.t = int(raw_input(self.name + " Thrust?: "))
            if self.t >= 20:
                self.t = 20
                print self.name, " maximum thrust! (20)"
            if self.t < 0:
                self.t = 0
                print self.name, " no thrust! (0)"
            if (self.t * T) > self.f:
                self.t = self.f / T
                print self.name, " Out of fuel! "
        else:
            self.t = 0
            print self.name, "Out of fuel!"

    def print_status(self):
        print "{0} p: {1} v: {2} f:{3}".format(self.name, self.p, self.v, self.f)
        if self.landed:
            if self.success:
                print "Landing Successful!"
            else:
                print "Landing failed!"

print Rocket.__doc__




# first_rocket = Rocket("Mark1")

# fleet = [Rocket("Mark 10", 100), Rocket("Mark 11", 150), Rocket("Mark 12", 200)]
# fleet.append(Rocket("Mark 50"))

# for rocket in fleet:
#     print rocket.name

# while not first_rocket.landed:
#     first_rocket.get_thrust()
#     first_rocket.update()
#     first_rocket.print_status()
