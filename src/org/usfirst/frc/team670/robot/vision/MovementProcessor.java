package org.usfirst.frc.team670.robot.vision;

import java.awt.Point;
import org.opencv.core.Rect;

public class MovementProcessor {

		private static Rect right = new Rect(0, 0 , 0, 0);
		private static Rect left = new Rect(0,0,0,0);	
		private static boolean isLeftValid, isRightValid;
		private static double EPSILON = 30;

		//private static Point targetPoint = new Point(640/2,480/2);
		
		public MovementProcessor()
		{
			
		}
		
		//Returns true when robot should slam the gas and hit the gear with full force
		//Will be called when the vision targets have a combined area of > 3/4*the area of the picture
		
		
		public String nextMove(Rect a, Rect b) 
		{
				if (a.x < b.x)
				{
					left = a;
					right = b;
				} 
				else if (a.x > b.x)
				{
					left = b;
					right = a;
				}
				
				defineRectangleValidity();
				
				if(checkIfFinished())
					return "cancel";
				
				return decideAction();
		}
		
		//Returns true if command should cancel, and alignment is perfect
		public boolean checkIfFinished()
		{
			boolean output = false;
			//Decide if the command is done
			return output;
		}
		
		//Checks if the rectangles are valid
		public void defineRectangleValidity()
		{
			if(left.height/2 >= left.width)
				isLeftValid = true;
			else
				isLeftValid = false;
			
			if(right.height/2 >= right.width)
				isRightValid = true;
			else
				isRightValid = false;
			
			if(right.area() >= left.area() * 3/4)
				isRightValid = true;
			else
				isRightValid = false;
			
			if(left.area() >= right.area() * 3/4)
				isLeftValid = true;
			else
				isLeftValid = false;
		}
	
		public String decideAction()
		{
			String result = getRectOutOfRangeCommand();
			
			if(result.equals("return"))	
				result = getPivot(left, right);
						
			if(result.equals("return"))
				result = alignPerpendicular(left, right);
			
			return result;
		}
		
		//Returns a movement if one of the rectangles is out of range of the camera view
		public String getRectOutOfRangeCommand()
		{
			if(isLeftValid & !isRightValid)
				return "left";
			else if(!isLeftValid && isRightValid)
				return "right";
			else if(isLeftValid && isRightValid)
				return "return";
			else
				return "cancel";
		}
		
		//returns "left", "right" or "return" or "centered" -- Moves with omniwheel
		public String alignPerpendicular(Rect left, Rect right)
		{
			double xAverage = midpointX(left,right);
			double centerX = 300;
			double avgDif = centerX - xAverage;
			if(avgDif < EPSILON && avgDif > -EPSILON)
				return "centered";
			else if(avgDif > EPSILON)
				return "right";
			else if(avgDif < -EPSILON)
				return "left";
			else
				return "centered";
		}
		
		//Will return "left_pivot" or "right_pivot" or "return"
		public static String getPivot(Rect left, Rect right)
		{
			double areaDif = (left.area() - right.area());
			int thresh = 1000;
			if(areaDif > thresh)
				 return "right_pivot";
			else if(areaDif < -thresh)
				 return "left_pivot";
			else
				return "return";
		}

		private static double midpointX(Rect left, Rect right)
		{
			double leftX = left.x + left.width;
			double rightX = right.x;

			double midpointX = leftX + rightX / 2;
			return midpointX;
		}

		
	}
