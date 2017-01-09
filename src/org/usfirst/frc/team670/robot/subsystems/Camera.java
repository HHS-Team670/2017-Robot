package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.ImageTool;
import org.usfirst.frc.team670.robot.commands.UpdateCamera;

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
	private int cameraFront;
	private int cameraBack;
	private int currentCamera;
	private boolean frontCam;
	private static final int BAD_CAMERA = -1;
	
	public Camera() {
		try {
			cameraFront = NIVision.IMAQdxOpenCamera("cam0", 
					NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		} catch (Exception ex) {
			System.out.println("Camera() failed to open the front camera (cam0)!!");
			cameraFront = BAD_CAMERA;
		}
		try {
			cameraBack = NIVision.IMAQdxOpenCamera("cam1", 
					NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		} catch (Exception ex){
			System.out.println("Camera() failed to open the back camera (cam1)!!");
			cameraBack = BAD_CAMERA;
		}
		
		// Set the default camera here, skipping to the secondary camera if needed.
		// Also set the frontCam boolean (front = claw side = true)
		if (cameraFront != BAD_CAMERA)
			currentCamera = cameraFront;
		else if (cameraBack != BAD_CAMERA)
			currentCamera = cameraBack;
		else
			currentCamera = BAD_CAMERA;
				
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		server = CameraServer.getInstance();
		server.setQuality(50);
		
		switchToCamera(currentCamera);
		// Don't call startAutomaticCapture() here because we're using setImage() instead
	}
	
	public void switchCamera() {
		if (frontCam == true)
			switchToCamera(cameraBack);
		else
			switchToCamera(cameraFront);
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
			frontCam = (newCam == cameraFront);
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
			Runnable processor = new Runnable() {
	            public void run() {
					NIVision.IMAQdxGrab(currentCamera, frame, 1);
					frame = ImageTool.processImage(frame);
					server.setImage(frame);
	            	}
	            };
	            new Thread(processor).start(); 
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new UpdateCamera());
    }
}