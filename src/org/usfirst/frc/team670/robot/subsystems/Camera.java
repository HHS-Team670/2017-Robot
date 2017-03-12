package org.usfirst.frc.team670.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {
	
	boolean isOnWinch = false;
	CameraServer server = CameraServer.getInstance();
	UsbCamera winchCam, backCam;
	int ON = 10, OFF = 0;
	double resolutionFactor = 0.5;
	int resWidth = (int) (640*resolutionFactor), resHeight = (int) (480*resolutionFactor);
	
	public Camera()
	{
		backCam = server.startAutomaticCapture("Back Cam", 0);
		winchCam = server.startAutomaticCapture("Winch Cam", 1);
		
		backCam.setFPS(ON);
		winchCam.setFPS(OFF);
		
		isOnWinch = false;
		
		winchCam.setResolution(resWidth,resHeight);
		backCam.setResolution(resWidth,resHeight);
	}
	
	public void flipCam()
	{
		if(isOnWinch)
		{
			backCam.setFPS(ON);
			winchCam.setFPS(OFF);
			isOnWinch = false;
		}
		else
		{
			backCam.setFPS(OFF);
			winchCam.setFPS(ON);
			isOnWinch = true;
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

