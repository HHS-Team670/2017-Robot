package org.usfirst.frc.team670.robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DataExchanger 
{

	private NetworkTable table;
	private String key;
	
	public DataExchanger(String key)
	{
		this.key = key;
		table = NetworkTable.getTable(key);
	}
	
	public String getData(String dataKey)
	{
		return table.getString(dataKey, null);
	}
	
	public String getKey()
	{
		return key;
	}
	
}
