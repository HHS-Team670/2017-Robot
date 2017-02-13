# 2017-Robot
Code for Team 670's Steamworks Robot

### TODO list

- [ ] Mercury
- [x] Venus
- [x] Earth (Orbit/Moon)
- [x] Mars
- [ ] Jupiter
- [ ] Saturn
- [ ] Uranus
- [ ] Neptune
- [ ] Comet Haley

- [ ] Robot
- [ ] DriveBase
- [ ] Drive With Joystick
- [ ] PID authodrive
- [ ] PID auto pivot
	      - [ ]  Shooter (low goal)
		      - [ ] Auto Shoot w/ button
	      - [ ]  Intake 
		      - [ ] Joystick control (y-axis)
	      - [ ] . Grappler (rope climber)
		       - [ ] . Joystick control (x-axis)
	      e. Camera
		    i.      Update Camera command
		    ii.     Switch input
	      f. Autonomous
		    i.  Do nothing (0pt.) 1
		    ii.  Move to line (5pt.) 2
		    iii. Gear Auto
			Center gear w/o vision 3
			Center gear w/ vision 4
			Right and left gear w/ vision 5 & 6
Vision 
Send back one of these
“(Pivot_x_degrees)”
“(foward)”
“(left)”
	      b. Calibration tool
		   i.       EZ interface
	      c. Communication w/ RoboRIO

UPDATE (2/12/17): FIRST is nice, but as always updates disable us for long periods of time. The SRX CANTalon libraries have been disabled, so now need to go to: http://www.ctr-electronics.com/control-system/hro.html#product_tabs_technical_resources, to install the update library (Only on Windows)
