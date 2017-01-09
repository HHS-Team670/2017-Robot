package org.usfirst.frc.team670.robot;

import java.util.Comparator;
import java.util.Vector;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

public class ImageTool {

	static double RectangleLeftCoordinate;
	static double RectangleTopCoordinate;
	static double RectangleRightCoordinate;
	static double RectangleBottomCoordinate;
	static boolean targetDetected;
	static double targetDistance, towerDistance;
	static double targetVerticalAngle, targetHorizontalAngle;
	static double crosshairHorizontalAngle; // angle from target to crosshair
	static Image binaryFrame;
	static Image testFrame;
	static int imaqError;


	// Constants
	static NIVision.Range TARGET_SAT_RANGE = new NIVision.Range(0, 16);
	static NIVision.Range TARGET_HUE_RANGE = new NIVision.Range(0, 180);
	static NIVision.Range TARGET_VAL_RANGE = new NIVision.Range(227, 255);

	static double AREA_MINIMUM = 0.1;
	
	static NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	static NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0, 0, 1, 1);
	static Scores scores = new Scores();
	
	public static Image processImage(Image frame){
		//Threshold the image looking for green (retroreflective target color)
		NIVision.imaqColorThreshold(binaryFrame, frame, 255, NIVision.ColorMode.HSV, TARGET_HUE_RANGE, TARGET_SAT_RANGE, TARGET_VAL_RANGE);
		int numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		float areaMin = (float)AREA_MINIMUM;
		criteria[0].lower = areaMin;
		imaqError = NIVision.imaqParticleFilter4(binaryFrame, binaryFrame, criteria, filterOptions, null);
		numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		if(numParticles > 0)
		{
			//Measure particles and sort by particle size
			Vector<ParticleReport> particles = new Vector<ParticleReport>();
			int topWidthIndex = 0;
			for(int particleIndex = 0; particleIndex < numParticles; particleIndex++)
			{
				ParticleReport par = new ParticleReport();
				par.PercentAreaToImageArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
				par.Area = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA);
				par.BoundingRectTop = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
				par.BoundingRectLeft = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
				par.BoundingRectBottom = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
				par.BoundingRectRight = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
				
				if(AreaScore(par) > 50 && (par.BoundingRectRight-par.BoundingRectLeft) > 50) {
					particles.add(par); //if(par.BoundingRectTop>480)
					if((par.BoundingRectRight - par.BoundingRectLeft) > (particles.elementAt(topWidthIndex).BoundingRectRight - particles.elementAt(topWidthIndex).BoundingRectLeft)) {
						topWidthIndex = particles.size()-1;
					}
				}
				
				RectangleTopCoordinate = par.BoundingRectTop;
				RectangleLeftCoordinate = par.BoundingRectLeft;
				RectangleBottomCoordinate = par.BoundingRectBottom;
				RectangleRightCoordinate = par.BoundingRectRight;
			}
				if(particles.size()>0){
				particles.set(0, particles.elementAt(topWidthIndex));
				scores.Aspect = AspectScore(particles.elementAt(0));
				//SmartDashboard.putNumber("Aspect", scores.Aspect);
				scores.Area = AreaScore(particles.elementAt(0));
				//SmartDashboard.putNumber("Area", scores.Area);

				//Set values
				}			
		}
		return DrawGoal(frame);
	}
	
	public static Image DrawGoal(Image frame){
		int top = (int)RectangleTopCoordinate;
		int left = (int)RectangleLeftCoordinate;
		int width = (int)(RectangleRightCoordinate - RectangleLeftCoordinate);
		int height = (int)(RectangleBottomCoordinate - RectangleTopCoordinate);
		System.out.println(top + ", " + left + ", " + height + ", " + width);
		NIVision.Rect rect = new NIVision.Rect(top, left, height, width);
		NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.PAINT_VALUE, ShapeMode.SHAPE_RECT, 100.0f);	
		return frame;
	}
	
	static double ratioToScore(double ratio)
	{
		return (Math.max(0, Math.min(100*(1-Math.abs(1-ratio)), 100)));
	}

	static double AreaScore(ParticleReport report)
	{
		double boundingArea = (report.BoundingRectBottom - report.BoundingRectTop) * (report.BoundingRectRight - report.BoundingRectLeft);
		//Tape is 12" by 20" edge so 240" bounding rect. With 2" wide tape it covers 80" of the rect.
		return ratioToScore((240/80)*report.Area/boundingArea);
	}


	static double AspectScore(ParticleReport report)
	{
		return ratioToScore((12/20)*(report.BoundingRectRight-report.BoundingRectLeft)/(report.BoundingRectBottom-report.BoundingRectTop));
	}

	
	public static class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport> {
		double PercentAreaToImageArea;
		double Area;
		double BoundingRectLeft;
		double BoundingRectTop;
		double BoundingRectRight;
		double BoundingRectBottom;

		@Override
		public int compareTo(ParticleReport r) {
			return (int) (r.Area - this.Area);
		}

		public int compare(ParticleReport r1, ParticleReport r2) {
			return (int) (r1.Area - r2.Area);
		}
	};

	// Structure to represent the scores for the various tests used for target
	// identification
	public static class Scores {
		double Area;
		double Aspect;
	}
	
	
}
