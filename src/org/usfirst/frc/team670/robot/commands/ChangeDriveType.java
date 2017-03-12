package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.extras.DriveState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeDriveType extends Command {

	private DriveState drive;
	
    public ChangeDriveType(DriveState d) {
    	this.drive = d;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveBase.setDriveType(drive);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.driveBase.setOmniDrive(drive);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
