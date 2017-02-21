package org.usfirst.frc.team670.robot.commands.vision_auto;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.AlignWithGear;
import org.usfirst.frc.team670.robot.commands.DriveDistance;
import org.usfirst.frc.team670.robot.commands.PivotRight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterGearLeft_Vision extends CommandGroup {
    
	private int epsilon = 36;
	private double baseWidthInches = Robot.oi.baseWidthInches;
	
    public  AutoCenterGearLeft_Vision() {
    	requires(Robot.driveBase);
    	
    	//Drive forward to the area in front of the gear
    	addSequential(new DriveDistance(34.5 + 93.25 + epsilon));
    	addSequential(new PivotRight(90));
    	addSequential(new DriveDistance(baseWidthInches/2 - 19));
    	addSequential(new PivotRight(90));
    	addSequential(new AlignWithGear());
    	
    	//Align the robot in front of the gear
    	//addSequential(new PlaceGear(12));
    	//Drive forward and place the gear
    }
}
