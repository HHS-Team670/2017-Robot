package org.usfirst.frc.team670.robot.subsystems;

import java.util.ArrayList;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.usfirst.frc.team670.robot.commands.camera.UpdateCamera;
import org.usfirst.frc.team670.robot.vision.ImageUtilities;
import org.usfirst.frc.team670.robot.vision.MovementProcessor;
import org.usfirst.frc.team670.robot.vision.ParticleReport;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.CameraServer;

/**
 *
 */
public class Camera extends Subsystem {
    
	CameraServer server;
	private Image frame;
	public int cam1, cam2, cam3, cam4;
	private int currentCamera;
	public boolean one, two, three, four;
	
	//Vision stuff
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0, 0, 1, 1);
	MovementProcessor mp = new MovementProcessor();
	
	// If a camera can't be opened, set its variable to this value.
	// We're assuming the OpenCamera method will never return this value
	// if a camera is found successfully. (The API docs don't clarify.)
	private static final int BAD_CAMERA = -1;
	
public Camera() {
		
		// Attempt to open the cameras, but fail gracefully if they aren't found.
		// Upon error, set the camera variable to the BAD_CAMERA constant defined above.
		try {
 			cam1 = NIVision.IMAQdxOpenCamera("cam0", 
 					NIVision.IMAQdxCameraControlMode.CameraControlModeController);
 		} catch (Exception ex) {
 			System.out.println("Camera() failed to open the claw camera (cam0)!!");
 			cam1 = BAD_CAMERA;
 		}
 		try {
 			cam2 = NIVision.IMAQdxOpenCamera("cam1", 
 					NIVision.IMAQdxCameraControlMode.CameraControlModeController);
 		} catch (Exception ex){
 			System.out.println("Camera() failed to open the flap camera (cam1)!!");
 			cam2 = BAD_CAMERA;
 		}
 		try {
 			cam3 = NIVision.IMAQdxOpenCamera("cam2", 
 					NIVision.IMAQdxCameraControlMode.CameraControlModeController);
 		} catch (Exception ex){
 			System.out.println("Camera() failed to open the flap camera (cam2)!!");
 			cam3 = BAD_CAMERA;
 		}
 		try {
 			cam4 = NIVision.IMAQdxOpenCamera("cam3", 
 					NIVision.IMAQdxCameraControlMode.CameraControlModeController);
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
				
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		
		server = CameraServer.getInstance();
		server.setQuality(50);
		
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
	
	public ArrayList<Rect> getAllBoundingRects(Scalar up, Scalar down)
	{
		ArrayList<Rect> output = new ArrayList<Rect>();
		
		Image processed = frame;
		
		NIVision.imaqColorThreshold(processed, frame, 255, NIVision.ColorMode.HSV, new NIVision.Range((int)down.val[0],(int)up.val[0]), new NIVision.Range((int)down.val[1],(int)up.val[1]), new NIVision.Range((int)down.val[2],(int)up.val[2]));
		int numParticles = NIVision.imaqCountParticles(processed, 1);
		numParticles = NIVision.imaqCountParticles(processed, 1);
		if(numParticles > 0)
		{
			//Measure particles and sort by particle size
			if(numParticles > 0)
			{
				//Measure particles
				for(int particleIndex = 0; particleIndex < numParticles; particleIndex++)
				{
					ParticleReport par = new ParticleReport();
					par.PercentAreaToImageArea = NIVision.imaqMeasureParticle(processed, particleIndex, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
					par.Area = NIVision.imaqMeasureParticle(processed, particleIndex, 0, NIVision.MeasurementType.MT_AREA);
					par.BoundingRectTop = NIVision.imaqMeasureParticle(processed, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
					par.BoundingRectLeft = NIVision.imaqMeasureParticle(processed, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
					par.BoundingRectBottom = NIVision.imaqMeasureParticle(processed, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
					par.BoundingRectRight = NIVision.imaqMeasureParticle(processed, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);

					int width = Math.abs((int)par.BoundingRectLeft - (int)par.BoundingRectRight);
					int height = Math.abs((int)par.BoundingRectTop - (int)par.BoundingRectBottom);
					
					output.add(new Rect((int)par.BoundingRectLeft, (int)par.BoundingRectTop, width, height));
				}
			}
		}
		return output;
	}
	
	public Rect[] getTwoLargestRects(Scalar upper, Scalar lower)
	{
		ArrayList<Rect> output = getAllBoundingRects(upper, lower);
		Rect[] x = ImageUtilities.twoLargestRects(output);
		return x;
	}
	
	public String getNextCommandGear(Scalar upper, Scalar lower)
	{
		Rect[] in = getTwoLargestRects(upper, lower);
		drawOnFrame(in);
		return mp.nextMove(in[0], in[1]);
	}
	
	public void drawOnFrame(Rect[] x)
	{
		for(int i = 0; i < x.length; i++)
		{
			com.ni.vision.NIVision.Rect rect = new com.ni.vision.NIVision.Rect(x[i].x, x[i].y, x[i].width, x[i].height);
			NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.PAINT_VALUE, ShapeMode.SHAPE_RECT, 20);
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new UpdateCamera());
    }
}