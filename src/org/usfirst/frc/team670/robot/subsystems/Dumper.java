package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import com.ctre.*;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Dumper extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon dumperMotor = new CANTalon(RobotMap.dumperMotor);
	public boolean shouldRunDumber = true;

    public void initDefaultCommand() {
        //setDefaultCommand(new AutoDump(0));
    }
    
    public void runDumper(double value) {
    	dumperMotor.set(value);
    }
    
}

