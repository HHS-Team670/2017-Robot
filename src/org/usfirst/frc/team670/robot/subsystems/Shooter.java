package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Spark shootMotor = new Spark(RobotMap.dumperMotor);

    public void initDefaultCommand() {
        //setDefaultCommand(new AutoDump(0));
    }
    
    public void runDumper(double value) {
    	shootMotor.set(value);
    }
    
}

