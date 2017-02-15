package org.usfirst.frc.team670.robot.subsystems;

import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team670.robot.commands.camera.UpdateCamera;

public class Camera extends Subsystem {
    	
	public int[] cameraArray = new int[0];
	public int maxCameras = 4;
	private final int BAD_CAMERA = -1;
	private int currentCameraIndex = BAD_CAMERA;
	private VideoCapture videoInput;
	private CvSource source;
	private Mat frame;
	
	public Camera() {	
		
		for(int i = 0; i < maxCameras; i++)
		{
			if(new VideoCapture(i).isOpened())
			{
				int[] temp = cameraArray;
				cameraArray = new int[i+1];
				for(int x = 0; x < temp.length; x++)
					cameraArray[x] = temp[x];
				cameraArray[i] = i;
				currentCameraIndex = cameraArray[i];
			}
			else
				break;
		}
		
		frame = new Mat();
		
		switchToCamera(currentCameraIndex);
	}
	
	public void switchCam() 
	{
		int newCam = 0;
		for(int i = 0; i < cameraArray.length; i++)
		{
			if(i == currentCameraIndex)
				newCam = i + 1;
			if(newCam >= cameraArray.length)
				newCam = 0;
		}
		switchToCamera(newCam);
	}

	/* Private method used to avoid duplicating the code in two places */
	private void switchToCamera(int newCam) 
	{
		if(newCam != BAD_CAMERA)
		{
			videoInput = new VideoCapture(newCam);
			currentCameraIndex = newCam;			
			source = CameraServer.getInstance().putVideo("Rectangle", 640, 480);
			getImage();
		}
	}
	
	/* Grab a new image from the current camera, putting it into the frame.
	 * Then set that as the current frame in the camera server.
	 * If we don't have a working camera, just skip the whole process.
	 */
	public void getImage() 
	{
		if(currentCameraIndex != BAD_CAMERA)
		{
			videoInput.read(frame);
			source.putFrame(frame);
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new UpdateCamera());
    }
}
