package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistance extends Command {

	private double numberOfUnits;
    
    public DriveDistance(double numberOfUnits) {
    	this.numberOfUnits = numberOfUnits;
    }

    protected void initialize() {
        Robot.driveBase.leftTalon1.reset();
        Robot.driveBase.leftTalon1.enable();
        Robot.driveBase.leftTalon1.setPosition(0);
        
        Robot.driveBase.rightTalon1.reset();
        Robot.driveBase.rightTalon1.enable();
        Robot.driveBase.rightTalon1.setPosition(0);
        
        Robot.driveBase.leftTalon1.set(numberOfUnits);
        Robot.driveBase.rightTalon1.set(numberOfUnits);
    }

    protected void execute() {
    	// Should be nothing in here - right?  
        // Once the new setpoint is set in initialize the work is done.
    }

    protected boolean isFinished() {
    	//Check this stuff
    	if (numberOfUnits < 0)
    		return ((Robot.driveBase.leftTalon1.getPosition() - Robot.driveBase.rightTalon1.getPosition())/2 >= -numberOfUnits);
    	else
    		return ((Robot.driveBase.leftTalon1.getPosition() - Robot.driveBase.rightTalon1.getPosition())/2 <= -numberOfUnits);
    }

    protected void end() {
        // Shouldn't have to do this to get it to stop
    	Robot.driveBase.rightTalon1.disableControl();
    	Robot.driveBase.leftTalon1.disableControl();
        Robot.driveBase.leftTalon1.setPosition(0);
        Robot.driveBase.rightTalon1.setPosition(0);

    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
