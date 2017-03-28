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
	//
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


/*
package org.usfirst.frc.team670.robot.subsystems;

import org.opencv.core.Mat;
import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {
	
	UsbCamera winchCam, backCam, frontCam;
	//0 = Front, 1 = winch, 2 = back
	private CvSource outputStream;
	private CvSink cvSinkFront, cvSinkBack, cvSinkWinch, currentCvSink;
	
	public Camera()
	{
		
	}
	
	public void init()
	{		
		winchCam = CameraServer.getInstance().startAutomaticCapture("winch", 1);
		backCam = CameraServer.getInstance().startAutomaticCapture("back", 0);
		frontCam = CameraServer.getInstance().startAutomaticCapture("front", 2);
        winchCam.setResolution(640, 480);
        backCam.setResolution(640, 480);
        frontCam.setResolution(640, 480);
        cvSinkWinch = CameraServer.getInstance().getVideo("winch");
        cvSinkFront = CameraServer.getInstance().getVideo("front");
        cvSinkBack = CameraServer.getInstance().getVideo("back");
        outputStream = CameraServer.getInstance().putVideo("Video", 640, 480);
        currentCvSink = cvSinkFront;
        
        Mat src = new Mat(), output = new Mat();
        //Run thread to start the processing
        new Thread(() -> {
	        	while(true)
	        	{
		        	if(Robot.oi.runCamera)
			        {
				        currentCvSink.grabFrame(src);
				        //Run processing here if you are inclined to do so
				        outputStream.putFrame(src);
			        }
	        	}
        }).start();
	}
	
	public void flipCam()
	{
		if(currentCvSink.equals(cvSinkFront))
		{
			currentCvSink = cvSinkWinch;
		}
		else if(currentCvSink.equals(cvSinkWinch))
		{
			currentCvSink = cvSinkBack;
		}
		else
		{
			currentCvSink = cvSinkFront;
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
 */

