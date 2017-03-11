package org.usfirst.frc.team670.robot.commands;

import org.opencv.core.Scalar;
import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GetNextCommand extends Command {

    public GetNextCommand() {
        // Use requires() here to declare subsystem dependencies
//        requires(Robot.camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
//    	Robot.camera.getImage();
//    	String move = Robot.camera.getNextCommandGear(new Scalar(240,236,179), new Scalar(255,255,255));
//    	SmartDashboard.putString("MOVEMENT:", move);
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
