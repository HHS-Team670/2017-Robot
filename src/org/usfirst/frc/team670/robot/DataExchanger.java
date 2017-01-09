package org.usfirst.frc.team670.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DataExchanger {

	public static NetworkTable table;
	
	public DataExchanger()
	{
		table = NetworkTable.getTable("vision");
	}
	
	public String getData()
	{
		return table.getString("data", null);
	}	
}
