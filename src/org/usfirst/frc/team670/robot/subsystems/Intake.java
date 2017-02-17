package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.IntakeWithJoystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
    
	Spark intake = new Spark(RobotMap.intakeMotor);
	
    public void initDefaultCommand() {
        setDefaultCommand(new IntakeWithJoystick());
    }
    
    public void intake(double value)
    {
    	intake.set(value);
    }
}

