package org.usfirst.frc.team670.robot.commands.camera;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlipCamera extends Command {

	public static boolean firstCam = true;

	public FlipCamera() {
		requires(Robot.camera);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.camera.flipCam();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
