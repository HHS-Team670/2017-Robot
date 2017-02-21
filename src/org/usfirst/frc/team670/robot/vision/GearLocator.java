package org.usfirst.frc.team670.robot.vision;

import java.awt.Color;
import java.util.ArrayList;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

public class GearLocator 
{
	//Use this for calculating distance and stuff
	private static int targetWidth = 2;
	private static Scalar lower, upper;
	private static Rect a = new Rect(0,0,0,0), b = new Rect(0,0,0,0);
	private static MovementProcessor mp;
	
	public static void init(Scalar min, Scalar max)
	{
		upper = max;
		lower = min;
		mp = new MovementProcessor();
	}
	
	public static String getNextMove(Mat m)
	{
		ArrayList<Rect> boxes = ImageUtilities.getBoundingRectangle(m, lower, upper);
		
		Rect[] rects = ImageUtilities.twoLargestRects(boxes);
		a = rects[0];
		b = rects[1];
		
    	m = ImageUtilities.drawRectangle(m, rects, Color.RED);

    	return mp.nextMove(a, b);
	}
	
}
