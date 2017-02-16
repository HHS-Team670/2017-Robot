package org.usfirst.frc.team670.robot.subsystems;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.usfirst.frc.team670.robot.commands.camera.UpdateCamera;

public class Camera extends Subsystem {
    	
	private int currentCameraIndex = 0;
	private UsbCamera camera;
	private CvSink cvSink;
	private CvSource outputStream;
	private Mat mat;
	
	public Camera() {		
		switchToCamera(currentCameraIndex);
	}
	
	public void switchCam() 
	{
		camera = CameraServer.getInstance().startAutomaticCapture(currentCameraIndex++);
		cvSink = CameraServer.getInstance().getVideo();
		
		if(cvSink.grabFrame(new Mat()) == 0)
			currentCameraIndex=0;
		switchToCamera(currentCameraIndex);
	}

	/* Private method used to avoid duplicating the code in two places */
	private void switchToCamera(int newCam) 
	{
		camera = CameraServer.getInstance().startAutomaticCapture(newCam);
		camera.setResolution(640, 480);
		cvSink = CameraServer.getInstance().getVideo();
		outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);
		mat = new Mat();
		
		getImage();
	}
	
	/* Grab a new image from the current camera, putting it into the frame.
	 * Then set that as the current frame in the camera server.
	 * If we don't have a working camera, just skip the whole process.
	 */
	public void getImage() 
	{
		if (cvSink.grabFrame(mat) == 0) 
			outputStream.notifyError(cvSink.getError());
		else
			outputStream.putFrame(mat);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new UpdateCamera());
    }
}
