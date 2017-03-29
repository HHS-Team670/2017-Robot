package org.usfirst.frc.team670.robot.subsystems;


import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.Joystick_Shoot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
  
	final Spark shootMotor = new Spark(RobotMap.dumperMotor);

    public void initDefaultCommand() {
        setDefaultCommand(new Joystick_Shoot());
    }
    
    public void shoot(double value) {
    	if(value > 0)
    	{
    		Robot.shooter.shootForward(value);
    	}
    	else if(value < 0)
    	{
    		Robot.shooter.shootBackward(value);
    	}
    }
    
    public void shootForward(double value)
    {
    	value = -Math.abs(value);
    	shootMotor.set(value);
    }
    
    public void shootBackward(double value)
    {
    	value = Math.abs(value);
    	shootMotor.set(value);
    }
}

