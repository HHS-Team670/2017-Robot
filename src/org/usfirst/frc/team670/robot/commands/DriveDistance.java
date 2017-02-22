package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {

	private double inches = 0;
	
    public DriveDistance(double inches) {
    	this.inches = inches;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveBase.driveDistance(inches);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;		
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveBase.drive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
