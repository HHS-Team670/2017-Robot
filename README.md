# 2017-Robot
Code for Team 670's Steamworks Robot

###Steps to flash a new RoboRIO

HERE(docs/RoboRIO_Flash.md)

###Steps to flash a new Router

HERE(docs/Router_Flash.md)

### TODO list

##Robot

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
- [ ]	|---Update Camera command
- [ ]	|---Switch input

##Autonomous

- [x] Do nothing (0pt.)
- [x] Move to line (5pts.)
- [ ] Gear Auto (60 pts.)
- [ ] |---All gear sides w/ vision (x3)
- [ ]	|---Center gear w/o vision (x1)

##Vision

- [ ] Calibration Tool
- [x] Communication with RoboRIO
- [ ] |---Sending command data

#UPDATE (2/12/17): 

FIRST is nice, but as always, updates disable us for long periods of time. The SRX CANTalon libraries have been disabled, so now need to go to: http://www.ctr-electronics.com/control-system/hro.html#product_tabs_technical_resources, to install the update library (Only on Windows) --> NEVER OPEN THIS PROJECT ON A MAC OR PC WITHOUT THIS PROGRAM INSTALLED
