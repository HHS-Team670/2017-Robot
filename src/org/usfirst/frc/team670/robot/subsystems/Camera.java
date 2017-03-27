package org.usfirst.frc.team670.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {
	
	boolean isOnWinch = false;
	CameraServer server;
	UsbCamera winchCam, backCam, frontCam;
	int ON = 10, OFF = 1;
	double resolutionFactor = 0.5;
	double invalResFactor = 0.1;
	int resWidth = (int) (640*resolutionFactor), resHeight = (int) (480*resolutionFactor);
	int resWidthinval = 1, resHeightinval = 1;
	//0 = Front, 1 = winch, 2 = back
	int camNum = 0;
	
	public Camera()
	{
		
	}
	
	public void init()
	{
		server = CameraServer.getInstance();
		
		winchCam = server.startAutomaticCapture("Winch Cam", 1);
		backCam = server.startAutomaticCapture("Back Cam", 0);
		frontCam = server.startAutomaticCapture("Front Cam", 2);
		
		frontCam.setResolution(resWidth, resHeight);
		backCam.setResolution(resWidthinval,resHeightinval);
		winchCam.setResolution(resWidthinval,resHeightinval);
		
		//winchCam.setFPS(OFF);
		//backCam.setFPS(ON);
	}
	
	public void flipCam()
	{
		if(camNum == 0)
		{
			winchCam.setResolution(resWidth, resHeight);
			backCam.setResolution(resWidthinval,resHeightinval);
			frontCam.setResolution(resWidthinval,resHeightinval);
			camNum = 1;
		}
		else if(camNum == 1)
		{
			backCam.setResolution(resWidth, resHeight);
			frontCam.setResolution(resWidthinval,resHeightinval);
			winchCam.setResolution(resWidthinval,resHeightinval);
			camNum = 2;
		}
		else
		{
			frontCam.setResolution(resWidth, resHeight);
			backCam.setResolution(resWidthinval,resHeightinval);
			winchCam.setResolution(resWidthinval,resHeightinval);
			camNum = 0;
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

