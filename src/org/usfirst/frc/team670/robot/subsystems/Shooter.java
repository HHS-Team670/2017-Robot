package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.ShootWithJoystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
  
	Spark shootMotor = new Spark(RobotMap.dumperMotor);

    public void initDefaultCommand() {
        setDefaultCommand(new ShootWithJoystick());
    }
    
    public void runDumper(double value) {
    	shootMotor.set(value);
    }
    
}

