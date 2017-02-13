package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.RunIntake;
import com.ctre.*;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    
	CANTalon intakeMotor = new CANTalon(RobotMap.intakeMotor);
	
    public void initDefaultCommand() {
        setDefaultCommand(new RunIntake());
    }
    
    public void runIntake(double value)
    {
    	intakeMotor.set(value);
    }
}

