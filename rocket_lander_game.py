# Kelvin Jang KyungSu
# Rocket Lander

GRAVITY = -10

class Rocket:
    count = 0

    def __init__(self, new_rocket_name="NO NAME", new_rocket_fuel=100):
        self.fuel = new_rocket_fuel
        self.name = new_rocket_name
        self.position = 100
        self.velocity = 0
        self.thrust = 0
        self.acceleration = 0
        Rocket.count += 1


    def update(self):
        self.fuel = self.fuel - self.thrust
        self.acceleration = GRAVITY + self.thrust
        self.position = self.position + self.velocity + 0.5*self.acceleration
        self.velocity = self.velocity + self.acceleration

    def print_statue(self):
        print "{0} P: {1} v: {2} : F: {3}".format(self.name, self.position, self.velocity, self.fuel)

    def get_thrust(self):
        if self.fuel > 0:
            self.thrust = int(raw_input(self.name + " Set thrusters(0-20): "))
            if self.thrust > 20:
                self.thrust = 20
                print self.name + " Thrusters at max(20)!"
            if self.thrust < 0:
                self.thrust = 0
                print self.name + " No thrusters(0)!"
            if self.thrust > self.fuel:
                self.thrust = self.fuel
                print self.name + " Out of fuel! Thrusters at {0}".format(self.thrust)
        else:
            print(self.name + "No fuel -- rocket is in free-fall!")
            self.thrust = 0


o = int(raw_input("How many Rocket? "))
fleet = [Rocket(raw_input("Input the Rocket's name: "), int(raw_input("Rocket Fuel: "))) for i in range(o)]

while True:
    for i in fleet:
        if (i.position > 0):
            i.get_thrust()

    for i in fleet:
        if (i.position > 0):
            i.update()
            i.print_statue()

    for k in range(len(fleet)):
        if fleet[k].position <= 0:
            if fleet[k].velocity > -3:
                print fleet[k].name + "rocket landed!"
                if k == len(fleet) - 1:
                    exit()
            else:
                print fleet[k].name + "Rocket crashed! Velocity was {0} m/s".format(i.velocity)
                if k == len(fleet) - 1:
                    exit()

# while(first_rocket.print_statue() > 0):
#     first_rocket.get_thrust()
#     first_rocket.update()
#     first_rocket.print_statue()

# class ADC:
#
#     def __init__(self, number):
#         self.file = "/asdfsadf" + number + "sadfasdf"
#
#     def read(self, ):
#         open()
