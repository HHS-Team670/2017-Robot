package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.IntakeWithJoystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
    
	final Spark intake = new Spark(RobotMap.intakeMotor);
	
    public void initDefaultCommand() {
        setDefaultCommand(new IntakeWithJoystick());
    }
    
    public void intake(double value)
    {
    	if(value > 0)
    	{
    		Robot.intake.spinForward(value);
    	}
    	else if(value < 0)
    	{
    		Robot.intake.spinBackward(value);
    	}
    }
    
    public void spinForward(double value)
    {
    	value = Math.abs(value);
    	intake.set(value);
    }
    
    public void spinBackward(double value)
    {
    	value = -(Math.abs(value));
    	intake.set(value);
    }
}

