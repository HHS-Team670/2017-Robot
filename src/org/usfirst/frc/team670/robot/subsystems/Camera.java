package org.usfirst.frc.team670.robot.subsystems;

import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team670.robot.commands.camera.UpdateCamera;

public class Camera extends Subsystem {
    	
	//Max of four cameras
	public int[] camera = new int[0];
	private int currentCamera;
	private VideoCapture capture;
	public boolean one, two, three, four;
	private CvSource source;
	private Mat frame;
	
	public Camera() {	
		
		for(int i = 0; i < 6; i++)
		{
			capture = new VideoCapture(i);
			if(capture.isOpened())
			{
				int[] temp = camera;
				camera = new int[i];
				for(int x = 0; x < temp.length; x++)
					camera[x] = temp[x];
				camera[i] = i;
			}
			else
				break;
		}
		
		frame = new Mat();
		
		switchToCamera(currentCamera);
	}
	
	public void switchCam() 
	{
		int newCam = 0;
		for(int i = 0; i < camera.length; i++)
		{
			if(i == currentCamera)
				newCam = i + 1;
			if(newCam >= camera.length)
				newCam = 0;
		}
		switchToCamera(newCam);
	}

	/* Private method used to avoid duplicating the code in two places */
	private void switchToCamera(int newCam) 
	{
			capture = new VideoCapture(newCam);
			currentCamera = newCam;			
			source = CameraServer.getInstance().putVideo("Rectangle", 640, 480);
			getImage();
	}
	
	/* Grab a new image from the current camera, putting it into the frame.
	 * Then set that as the current frame in the camera server.
	 * If we don't have a working camera, just skip the whole process.
	 */
	public void getImage() 
	{
			capture.read(frame);
			source.putFrame(frame);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new UpdateCamera());
    }
}
