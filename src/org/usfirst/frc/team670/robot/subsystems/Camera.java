package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.commands.camera.UpdateCamera;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {
    
	private CameraServer server;
	private Image frame;
	private int[] camera;
	private final int totalCameraNumber = 3;
	private int currentCamera;
	public int totalAvailableCams = 0;
	
	// If a camera can't be opened, set its variable to this value.
	// We're assuming the OpenCamera method will never return this value
	// if a camera is found successfully. (The API docs don't clarify.)
	private static final int BAD_CAMERA = -1;
	
	public Camera() {
		
		// Attempt to open the cameras, but fail gracefully if they aren't found.
		// Upon error, set the camera variable to the BAD_CAMERA constant defined above.
		for(int i = 0; i < totalCameraNumber; i++)
		{
			try {
				camera[i] = NIVision.IMAQdxOpenCamera("cam" + i, 
						NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			} catch (Exception ex) {
				System.out.println("Unable to openCamera: " + i);
				camera[i] = BAD_CAMERA;
			}
		}
		// Set the default camera here, skipping to the secondary camera if needed.
		// Also set the frontCam boolean (front = claw side = true)
		for(int i = totalCameraNumber; i > 0; i--)
		{
			if(camera[i] != BAD_CAMERA)
			{
				totalAvailableCams++;
				currentCamera = camera[i];
			}
		}
		if(totalAvailableCams <= 0)
		{
			currentCamera = BAD_CAMERA;
		}
				
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		server = CameraServer.getInstance();
		server.setQuality(50);
		
		switchToCamera(currentCamera);
		// Don't call startAutomaticCapture() here because we're using setImage() instead
	}
	
	public void switchCam() 
	{
			switchToCamera(getNextCamIndex());
	}
	
	public int getNextCamIndex()
	{
		if(currentCamera != BAD_CAMERA)
		{
			int index = 0;
			for(int i = 0; i < camera.length; i++)
			{
				if(currentCamera == camera[i])
				{
					index = i;
					break;
				}
			}
			if(index + 1 > camera.length - 1)
			{
				for(int i = 0; i < camera.length; i++)
				{
					if(camera[i] != BAD_CAMERA)
						return i;
				}
			}
			else
				return index+1;
		}
		currentCamera = BAD_CAMERA;
		return -1;
	}

	/* Private method used to avoid duplicating the code in two places */
	private void switchToCamera(int newCam) {
		// Don't do anything if the desired camera wasn't found
		if (newCam != BAD_CAMERA) {
			// Stop any camera that's running right now
			if (currentCamera != BAD_CAMERA)
				NIVision.IMAQdxStopAcquisition(currentCamera);
			
			NIVision.IMAQdxConfigureGrab(newCam);
			NIVision.IMAQdxStartAcquisition(newCam);
			
			currentCamera = newCam;
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
			NIVision.IMAQdxGrab(currentCamera, frame, 1);
			server.setImage(frame);
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new UpdateCamera());
    }
}