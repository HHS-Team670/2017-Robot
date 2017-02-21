package org.usfirst.frc.team670.robot.commands;


import org.usfirst.frc.team670.robot.OI;
import org.usfirst.frc.team670.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DrawReticle extends Command {
	
	private boolean on = false;
	//This is the rectangle that gets drawn, need to offset it 4 inches to the left somehow.
	
    public DrawReticle(boolean on) {
     requires(Robot.camera);
     this.on = on;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(on == true)
    	{
    		OI.drawRopeReticle = true;
    	}
    	else
    		OI.drawRopeReticle = false;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
