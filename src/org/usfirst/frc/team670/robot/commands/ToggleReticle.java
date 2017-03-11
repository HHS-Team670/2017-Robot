package org.usfirst.frc.team670.robot.commands;


import org.usfirst.frc.team670.robot.OI;
import org.usfirst.frc.team670.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleReticle extends Command {
	
	//This is the rectangle that gets drawn, need to offset it 4 inches to the left somehow.
	
    public ToggleReticle() {
     //requires(Robot.camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	OI.drawRopeReticle = !OI.drawRopeReticle;
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
