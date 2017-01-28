package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterGear_Vision extends CommandGroup {
    
    public  CenterGear_Vision() {
    	requires(Robot.driveBase);
    	
    	//Drive forward to the area in front of the gear
    	addSequential(new DriveDistance(6*12 + 6));
    	//Align the robot in front of the gear
    	addSequential(new PlaceGear(12));
    	//Drive forward and place the gear
    }
}
