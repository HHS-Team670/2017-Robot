package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.OI;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class flipControls extends Command {

	public static boolean reversedState = false;
	
    public flipControls() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	OI.winchControls = !OI.winchControls;
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
