package org.usfirst.frc.team670.robot.subsystems;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Camera extends Subsystem {
    	
	private ArrayList<Integer> cameras;
	private int currentCam = 0, BAD_CAMERA = -1;
	private Mat rawSRC = new Mat();
	private Mat outputSRC = new Mat();
	private CvSink cvSink;
	private CvSource outputStream;
	
	public Camera() 
	{		
		//Decide which cameras are used
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(currentCam);
        camera.setResolution(640, 480);
        
        cvSink = CameraServer.getInstance().getVideo();
        outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
      
		switchToCamera(currentCam);
	}
	
	//Goes from: Gear-->Shooter-->Climber
	public void switchCam() 
	{
		if(cameras.size() > 1)
		{
			int newCam = 0;
			if(cameras.size() <= cameras.indexOf(currentCam))
				newCam = cameras.get(0);
			else
				newCam = cameras.get(cameras.indexOf(currentCam)+1);
			switchToCamera(newCam);
		}
	}

	/* Private method used to avoid duplicating the code in two places */
	private void switchToCamera(int newCam) 
	{
		if (newCam != BAD_CAMERA)
		{
			 currentCam = newCam;
			 UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(currentCam);
		     camera.setResolution(640, 480);
		}
		getImage();
	}
	
	public void getImage()
	{
		cvSink.grabFrame(rawSRC);
        Imgproc.cvtColor(rawSRC, outputSRC, Imgproc.COLOR_BGR2GRAY);
        outputStream.putFrame(outputSRC);
	}
	
    public void initDefaultCommand()
    {
    	
    }
}
