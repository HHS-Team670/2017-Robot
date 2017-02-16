package org.usfirst.frc.team670.robot.vision;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageUtilities{

	//Get bounding rectangle with defined HSV values
	public static ArrayList<Rect> getBoundingRectangle(Mat frame, Scalar lowerHSV, Scalar upperHSV)
    {
			Mat processed = new Mat();
	    	Mat mHierarchy = new Mat();
	    	
	    	ArrayList<Rect> boxes = new ArrayList<Rect>();

	    	Core.inRange(frame, lowerHSV, upperHSV, processed);
	    	List<MatOfPoint> contours = new ArrayList<MatOfPoint>();   
	    	Imgproc.findContours(processed, contours, mHierarchy, Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
	    	MatOfPoint2f approxCurve = new MatOfPoint2f();
	    		    	
	    	for (int i = 0; i < contours.size(); i++)
	    	{
	    	        MatOfPoint2f contour2f = new MatOfPoint2f(contours.get(i).toArray());
	    	        double approxDistance = Imgproc.arcLength(contour2f, true)*0.02;
	    	        Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);
	    	        MatOfPoint points = new MatOfPoint( approxCurve.toArray() );
	    	        boxes.add(Imgproc.boundingRect(points));
	    	}
	    	
	    	if(boxes.size() < 2)
	    	{
	    		boxes.add(new Rect(0, 0, 0, 0));
	    	}
	    	
	    	if(boxes.size() < 2)
	    	{
	    		boxes.add(new Rect(0, 0, 0, 0));
	    	}
	    	
	    	return boxes;
	}
		
	//Two largest Rectangles by area
	public static Rect[] twoLargestRects(ArrayList<Rect> rects)
	{
		for(int i = 0; i < rects.size(); i++)
		{
			if(((rects.get(i))).area() >= 220000)
					rects.remove(i);
		}
		
		if(rects.size() == 0)
    	{
    		rects.add(new Rect(0, 0, 0, 0));
    	}
		Rect[] result = new Rect[2];	
		
		Rect prevMVal = new Rect(0,0,0,0);
		Rect currVal = new Rect(0,0,0,0);
		Rect currMVal = new Rect(0,0,0,0);
		int currMIndex = 0;
		int prevMIndex = 0;

		for (int i = 0; i < rects.size(); i++) {
			currVal = (Rect) rects.get(i);
			if (currVal.area() >= currMVal.area()) {
				prevMVal = currMVal;
				currMVal = currVal;
				prevMIndex = currMIndex;
				currMIndex = i;
			} else if (currVal.area() >= prevMVal.area()) {
				prevMVal = currVal;
				prevMIndex = i;
			}
		}
		
		result[0] = (Rect) rects.get(currMIndex);
		result[1] = (Rect) rects.get(prevMIndex);
		
		return result;
	}
	
	//Turn an opencv mat to a byte array
	public static byte[] extractBytes(Mat frame){
		MatOfByte bytemat = new MatOfByte();
		Imgcodecs.imencode(".jpg", frame, bytemat);
		byte[] bytes = bytemat.toArray();
		return bytes;
	}
	
	//Method to compress the quality of a mat file
	public static Mat compressMat(Mat m, double compressionRatio)
	{
		Size s = new Size(m.width() * compressionRatio, m.height() * compressionRatio);
		Imgproc.resize(m, m, s);
		return m;
	}

	//Return mat with rectangles drawn
	public static Mat drawRectangle(Mat frame, Rect[] boxes, Color c) {
		for(int i = 0; i < boxes.length; i++)
			Imgproc.rectangle(frame, new Point(boxes[i].x, boxes[i].y), new Point(boxes[i].x+boxes[i].width, boxes[i].y+boxes[i].height), new Scalar(c.getBlue(), c.getGreen(), c.getRed()));
		return frame;
	}
}
