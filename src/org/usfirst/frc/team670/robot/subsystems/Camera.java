package org.usfirst.frc.team670.robot.subsystems;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.opencv.core.Mat;
import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.camera.UpdateCamera;
import org.usfirst.frc.team670.robot.utilities.CameraLoc;

public class Camera extends Subsystem {
    	
	private UsbCamera camera;
	private CvSink cvSink;
	private CvSource outputStream;
	private Mat mat;
	private CameraLoc currentCamera;
	private int currentCameraIndex = 0;
	
	public Camera() 
	{		
		currentCamera = CameraLoc.GEAR;
		switchToCamera(currentCamera);
	}
	
	//Goes from: Gear-->Shooter-->Climber
	public void switchCam() 
	{
		if (currentCamera.equals(CameraLoc.GEAR))
			currentCamera = CameraLoc.SHOOTER;
		else if (currentCamera.equals(CameraLoc.SHOOTER))
			currentCamera = CameraLoc.CLIMBER;
		else if (currentCamera.equals(CameraLoc.CLIMBER))
			currentCamera = CameraLoc.GEAR;
	}

	/* Private method used to avoid duplicating the code in two places */
	private void switchToCamera(CameraLoc x) 
	{
		if(x.equals(CameraLoc.CLIMBER))
			currentCameraIndex = RobotMap.climberCamera;
		else if(x.equals(CameraLoc.GEAR))
			currentCameraIndex = RobotMap.gearCamera;
		else if(x.equals(CameraLoc.SHOOTER))
			currentCameraIndex = RobotMap.shooterCamera;
		
		camera = CameraServer.getInstance().startAutomaticCapture(currentCameraIndex);
		camera.setResolution(640, 480);
		cvSink = CameraServer.getInstance().getVideo();
		outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);
		mat = new Mat();
		
		getImage();
	}
	
	public void getImage() 
	{
		if (cvSink.grabFrame(mat) == 0) 
			outputStream.notifyError(cvSink.getError());
		else
			outputStream.putFrame(mat);
	}
	
    public void initDefaultCommand()
    {
        setDefaultCommand(new UpdateCamera());
    }
}
