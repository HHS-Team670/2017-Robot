package org.usfirst.frc.team670.robot.commands.auto_vision;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.DriveDistance;
import org.usfirst.frc.team670.robot.commands.PivotLeft;
import org.usfirst.frc.team670.robot.commands.PivotRight;
import org.usfirst.frc.team670.robot.commands.PlaceGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightGear_Vision extends CommandGroup {
    
	double baseWidthInches = Robot.oi.baseWidthInches;
	
    public  AutoRightGear_Vision() {
    	requires(Robot.driveBase);
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
        addSequential(new DriveDistance(76.25-(((baseWidthInches/2)-19-((34.5/(2*Math.sqrt(3)))))/(Math.sqrt(3)))));
    	addSequential(new PivotLeft(60));
    	addSequential(new PlaceGear());
    	//addSequential(new PlaceGear(12));
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
