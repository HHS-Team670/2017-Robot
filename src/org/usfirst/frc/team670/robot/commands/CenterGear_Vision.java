package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterGear_Vision extends CommandGroup {
    
    public  CenterGear_Vision() {
    	requires(Robot.driveBase);
    	
    	addSequential(new DriveDistance(6*12 + 6));
    	addSequential(new GearAlignmentAuto());
    }
}
