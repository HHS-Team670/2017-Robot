package org.usfirst.frc.team670.robot.commands.autonomous;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.PlaceGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterGear_Vision extends CommandGroup {
    
    public  AutoCenterGear_Vision() {
    	requires(Robot.driveBase);
    	
    	//Drive forward to the area in front of the gear
    	addSequential(new DriveDistance(6*12 + 6));
    	//Align the robot in front of the gear
    	addSequential(new PlaceGear(12));
    	//Drive forward and place the gear
    }
}
