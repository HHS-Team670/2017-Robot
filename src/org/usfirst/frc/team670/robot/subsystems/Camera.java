package org.usfirst.frc.team670.robot.subsystems;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team670.robot.commands.camera.UpdateCamera;

public class Camera extends Subsystem {
    
	//private Rect a = new Rect(0,0,0,0), Rect b = new Rect(0,0,0,0);
	
	//Max of four cameras
	public int[] cam = new int[2];
	
	private int currentCamera;
	
	public boolean one, two, three, four;
	private CvSource source;
	private Mat frame;
	private CvSink sink;
	//private Scalar upper = new Scalar(255,255,255), lower = new Scalar(255,255,255);
	
	public Camera() {	
		
		for(int i = 0; i < cam.length; i++)
		{
			cam[i] = i;
			VideoCapture cap = new VideoCapture(cam[i]);
			
			if(cap.isOpened())
				currentCamera = cam[i];
			else
				break;
		}
		
		frame = new Mat();
				
		CameraServer.getInstance().startAutomaticCapture(currentCamera);
				
		switchToCamera(currentCamera);
		// Don't call startAutomaticCapture() here because we're using setImage() instead
	}
	
	public void switchCam() 
	{
		int newCam = 0;
		for(int i = 0; i < cam.length; i++)
		{
			if(i == currentCamera)
				newCam = i + 1;
			if(newCam >= cam.length)
				newCam = 0;
		}
		switchToCamera(newCam);
	}

	/* Private method used to avoid duplicating the code in two places */
	private void switchToCamera(int newCam) 
	{
			CameraServer.getInstance().startAutomaticCapture(newCam);
			currentCamera = newCam;	
			sink = CameraServer.getInstance().getVideo();		
			source = CameraServer.getInstance().putVideo("Rectangle", 640, 480);
			getImage();
	}
	
	/* Grab a new image from the current camera, putting it into the frame.
	 * Then set that as the current frame in the camera server.
	 * If we don't have a working camera, just skip the whole process.
	 */
	public void getImage() 
	{
			sink.grabFrame(frame);
			source.putFrame(frame);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new UpdateCamera());
    }
}
