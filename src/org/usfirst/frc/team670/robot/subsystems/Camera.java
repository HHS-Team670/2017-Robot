package org.usfirst.frc.team670.robot.subsystems;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.usfirst.frc.team670.robot.commands.camera.UpdateCamera;

public class Camera extends Subsystem {
    
	private Rect a = new Rect(0,0,0,0);
	private Rect b = new Rect(0,0,0,0);
	public int cam1=0,cam2=1,cam3=2,cam4=3;
	private int currentCamera;
	public boolean one, two, three, four;
	private CvSource source;
	private Mat frame;
	private CvSink sink;
	private Scalar upper = new Scalar(255,255,255), lower = new Scalar(255,255,255);
	
	// If a camera can't be opened, set its variable to this value.
	// We're assuming the OpenCamera method will never return this value
	// if a camera is found successfully. (The API docs don't clarify.)
	private static final int BAD_CAMERA = -1;
	
	public Camera() {		
		frame = new Mat();
		
		// Attempt to open the cameras, but fail gracefully if they aren't found.
		// Upon error, set the camera variable to the BAD_CAMERA constant defined above.
		try {
 			CameraServer.getInstance().startAutomaticCapture(cam1);
 		} catch (Exception ex) {
 			System.out.println("Camera() failed to open the claw camera (cam0)!!");
 			cam1 = BAD_CAMERA;
 		}
 		try {
 			CameraServer.getInstance().startAutomaticCapture(cam2);
 		} catch (Exception ex){
 			System.out.println("Camera() failed to open the flap camera (cam1)!!");
 			cam2 = BAD_CAMERA;
 		}
 		try {
 			CameraServer.getInstance().startAutomaticCapture(cam3);
 		} catch (Exception ex){
 			System.out.println("Camera() failed to open the flap camera (cam2)!!");
 			cam3 = BAD_CAMERA;
 		}
 		try {
 			CameraServer.getInstance().startAutomaticCapture(cam4);
 		} catch (Exception ex){
 			System.out.println("Camera() failed to open the flap camera (cam3)!!");
 			cam4 = BAD_CAMERA;
 		}
 			
		if (cam1 != BAD_CAMERA)
		{
			setAllToFalse();
			one = true;
			currentCamera = cam1;
		}
		else if (cam2 != BAD_CAMERA)
		{
			setAllToFalse();
			two = true;
			currentCamera = cam2;
		}
		else if (cam3 != BAD_CAMERA)
		{
			setAllToFalse();
			three = true;
			currentCamera = cam3;
		}
		else if (cam4 != BAD_CAMERA)
		{
			setAllToFalse();
			four = true;
			currentCamera = cam4;
		}
		else
		{
			setAllToFalse();
			currentCamera = BAD_CAMERA;
		}
		
		switchToCamera(currentCamera);
		// Don't call startAutomaticCapture() here because we're using setImage() instead
	}
	
	public void switchCam() {
	 		if (one)
	 		{
	 			if(cam2 != BAD_CAMERA)
	 				switchToCamera(cam2);
	 			else if(cam3 != BAD_CAMERA)
	 				switchToCamera(cam3);
	 			else if(cam4 != BAD_CAMERA)
	 				switchToCamera(cam4);
	 			else
	 				switchToCamera(cam1);
	 		}
	 		else if(two)
	 		{
	 			if(cam3 != BAD_CAMERA)
	 				switchToCamera(cam3);
	 			else if(cam4 != BAD_CAMERA)
	 				switchToCamera(cam4);
	 			else if(cam1 != BAD_CAMERA)
	 				switchToCamera(cam1);
	 			else
	 				switchToCamera(cam2);
	 		}
	 		else if(three)
	 		{
	 			if(cam4 != BAD_CAMERA)
	 				switchToCamera(cam4);
	 			else if(cam1 != BAD_CAMERA)
	 				switchToCamera(cam1);
	 			else if(cam2 != BAD_CAMERA)
	 				switchToCamera(cam2);
	 			else
	 				switchToCamera(cam3);
	 		}
	 		else
	 		{
	 			if(cam2 != BAD_CAMERA)
	 				switchToCamera(cam2);
	 			else if(cam3 != BAD_CAMERA)
	 				switchToCamera(cam3);
	 			else if(cam4 != BAD_CAMERA)
	 				switchToCamera(cam4);
	 			else if(cam1 != BAD_CAMERA)
	 				switchToCamera(cam1);
	 		}
	}
	
	public void setAllToFalse()
	{
		one = false;
		two = false;
		three = false;
		four = false;
	}

	/* Private method used to avoid duplicating the code in two places */
	private void switchToCamera(int newCam) {
		setAllToFalse();
		
		if(newCam == cam1)
			one = true;
		else if(newCam == cam2)
			two = true;
		else if(newCam == cam3)
			three = true;
		else if(newCam == cam4)
			four = true;
		// Don't do anything if the desired camera wasn't found
		if (newCam != BAD_CAMERA) {
			// Stop any camera that's running right now
			CameraServer.getInstance().startAutomaticCapture(newCam);
			
			currentCamera = newCam;
			
			sink = CameraServer.getInstance().getVideo();
			
			source = CameraServer.getInstance().putVideo("Rectangle", 640, 480);
			// Get an initial image and display it.
			// We need to run getImage() regularly elsewhere in the code
			// in order to get a continuous feed. See the default command below.
			getImage();
		}
	}
	
	/* Grab a new image from the current camera, putting it into the frame.
	 * Then set that as the current frame in the camera server.
	 * If we don't have a working camera, just skip the whole process.
	 */
	public void getImage() {
		if (currentCamera != BAD_CAMERA) {
			sink.grabFrame(frame);
			source.putFrame(frame);
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new UpdateCamera());
    }
}