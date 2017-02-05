package org.usfirst.frc.team670.robot.utilities;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NetworkTablesServer {

	private static NetworkTable table;
	
	public NetworkTablesServer(String tableName)
	{
		table = NetworkTable.getTable(tableName);
	}
	
	public String getData(String key)
	{
		return table.getString(key, "data_unavailable");
	}
	
	public void sendData(String key, String data)
	{
		table.putString(key, data);
	}

	public boolean isConnected() {
		return table.isConnected();
	}
}
