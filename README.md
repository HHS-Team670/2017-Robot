# The Vegan
Code for Team 670's Steamworks Robot
--------------------------
**Robot Code**

- [x] DriveBase
- [x] |---Drive With Joystick
- [x] |---PID authodrive
- [x] |---PID auto pivot
- [x] Shooter (low goal)
- [x] |---Shooter joystick control
- [x]	|---Shooter toggle control
- [x] Intake 
- [x] |---Joystick control (y-axis)
- [x] Grappler (rope climber)
- [x] |---Joystick control (x-axis)
- [x] Camera
- [x]	|---Update Camera command
- [x]	|---Switch input

**Autonomous Code**

- [x] Do nothing (0pt.)
- [x] Move to line (5pts.)
- [x] Gear Auto (60 pts.)
- [x] |---All gear sides w/ vision (x3)
- [x]	|---All sides gear w/o vision (x1)

--------------------------
#Guides

**Steps to flash a new RoboRIO**

[HERE](docs/RoboRIO_Flash.md)

**Steps to flash a new Router**

[HERE](docs/Router_Flash.md)

--------------------------
#UPDATES

**(2/12/17):** 
FIRST is nice, but as always, updates disable us for long periods of time. The SRX CANTalon libraries have been disabled, so now need to go to: http://www.ctr-electronics.com/control-system/hro.html#product_tabs_technical_resources, to install the update library (Only on Windows) --> NEVER OPEN THIS PROJECT ON A MAC OR PC WITHOUT THIS PROGRAM INSTALLED

**(2/16/17):** 
FIRST is not nice, but as always, updates disable us for long periods of time. The Nivision (Camera) libraries have been disabled, so now need to go to: https://github.com/wpilibsuite/nivision/releases, to install the update library (Only on Windows) --> NEVER OPEN THIS PROJECT ON A MAC OR PC WITHOUT THIS PROGRAM INSTALLED
