package org.usfirst.frc.team670.robot.commands.autonomous;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.CancelCommand;
import org.usfirst.frc.team670.robot.commands.DriveDistance;
import org.usfirst.frc.team670.robot.commands.PivotRight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterGearLeft extends CommandGroup {
	
	private int epsilon = 36;
	private double baseWidthInches = Robot.oi.baseWidthInches;
	
    public CenterGearLeft() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new DriveDistance(34.5 + 93.25 + epsilon));
    	addSequential(new PivotRight(90));
    	addSequential(new DriveDistance(baseWidthInches/2 - 19));
    	addSequential(new PivotRight(90));
    	addSequential(new DriveDistance(epsilon));
        addSequential(new CancelCommand());

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
