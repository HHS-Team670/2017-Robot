package org.usfirst.frc.team670.robot.server;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NetworkTablesServer {

	private static NetworkTable table;
	private static String tableName;
	
	public NetworkTablesServer()
	{
		tableName = "vision";
		table = NetworkTable.getTable(tableName);
	}
	
	public String getData()
	{
		return table.getString("data", null);
	}
}
