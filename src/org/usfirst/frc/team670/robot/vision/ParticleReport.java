package org.usfirst.frc.team670.robot.vision;

import java.util.Comparator;

public class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport> {
	public double PercentAreaToImageArea;
	public double Area;
	public double BoundingRectLeft;
	public double BoundingRectTop;
	public double BoundingRectRight;
	public double BoundingRectBottom;

	@Override
	public int compareTo(ParticleReport r) {
		return (int) (r.Area - this.Area);
	}

	@Override
	public int compare(ParticleReport r1, ParticleReport r2) {
		return (int) (r1.Area - r2.Area);
	}
};