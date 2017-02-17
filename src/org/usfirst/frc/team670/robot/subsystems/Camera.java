package org.usfirst.frc.team670.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.CameraServer;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class Camera extends Subsystem {
    	
	private ArrayList<USBCamera> cameras;
	private USBCamera currentCamera;
	private USBCamera BAD_CAMERA = null;
	
	public Camera() 
	{		
		try {
 			cameras.add(new USBCamera("cam0"));
 			CameraServer.getInstance().startAutomaticCapture(cameras.get(0));
 			currentCamera = cameras.get(0);
 		} catch (Exception ex) {
 			System.out.println("Camera() failed to open the claw camera (cam0)!!");
 			cameras.remove(0);
 		}
		try {
 			cameras.add(new USBCamera("cam1"));
 			CameraServer.getInstance().startAutomaticCapture(cameras.get(1));
 			currentCamera = cameras.get(1);
 		} catch (Exception ex) {
 			System.out.println("Camera() failed to open the claw camera (cam1)!!");
 			cameras.remove(1);
 		}
		try {
 			cameras.add(new USBCamera("cam2"));
 			CameraServer.getInstance().startAutomaticCapture(cameras.get(2));
 			currentCamera = cameras.get(2);
 		} catch (Exception ex) {
 			System.out.println("Camera() failed to open the claw camera (cam2)!!");
 			cameras.remove(2);
 		}
		try {
 			cameras.add(new USBCamera("cam3"));
 			CameraServer.getInstance().startAutomaticCapture(cameras.get(3));
 			currentCamera = cameras.get(3);
 		} catch (Exception ex) {
 			System.out.println("Camera() failed to open the claw camera (cam3)!!");
 			cameras.remove(3);
 		}
		
		switchToCamera(currentCamera);
	}
	
	//Goes from: Gear-->Shooter-->Climber
	public void switchCam() 
	{
		USBCamera newCam = null;
	
		if(cameras.size() <= cameras.indexOf(currentCamera))
			newCam = cameras.get(0);
		else
			newCam = cameras.get(cameras.indexOf(currentCamera)+1);
		
		switchToCamera(newCam);
	}

	/* Private method used to avoid duplicating the code in two places */
	private void switchToCamera(USBCamera newCam) 
	{
		currentCamera.closeCamera();
		newCam.openCamera();
		if (currentCamera != BAD_CAMERA)
		{
			 CameraServer.getInstance().startAutomaticCapture(newCam);
			 currentCamera = newCam;
		}
		CameraServer.getInstance().setQuality(50);
	}
	
    public void initDefaultCommand()
    {
    	
    }
}
