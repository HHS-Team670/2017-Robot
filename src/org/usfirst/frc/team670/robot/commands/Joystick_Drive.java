package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.OI;
import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.DriveState;

import edu.wpi.first.wpilibj.command.Command;

public class Joystick_Drive extends Command {

	public Joystick_Drive() {
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(OI.isControlsStandard)
		{
			if(Robot.driveBase.getDriveType().equals(DriveState.FOURWHEEL))
				Robot.driveBase.drive(-Robot.oi.getleftStick().getY(),-Robot.oi.getrightStick().getY(), 0);
			if(Robot.driveBase.getDriveType().equals(DriveState.OMNIWHEEL))
				Robot.driveBase.drive(0,0,-Robot.oi.getrightStick().getX());
			else if(Robot.driveBase.getDriveType().equals(DriveState.ALLWHEEL))
				Robot.driveBase.drive(-Robot.oi.getleftStick().getY(),-Robot.oi.getrightStick().getY(),Robot.oi.getrightStick().getX());
		}
		else
		{
			if(Robot.driveBase.getDriveType().equals(DriveState.FOURWHEEL))
				Robot.driveBase.drive(Robot.oi.getrightStick().getY(),Robot.oi.getleftStick().getY(), 0);
			if(Robot.driveBase.getDriveType().equals(DriveState.OMNIWHEEL))
				Robot.driveBase.drive(0,0,Robot.oi.getrightStick().getX());
			else if(Robot.driveBase.getDriveType().equals(DriveState.ALLWHEEL))
				Robot.driveBase.drive(Robot.oi.getrightStick().getY(),Robot.oi.getleftStick().getY(),-Robot.oi.getrightStick().getX());
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
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
