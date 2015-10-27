/**
 * 
 */
package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author gnanasrinivas
 *
 */
public class ReadFromFile 
{
	private FileReader fr;
	private String fName;
	private BufferedReader br;
	private String l_sLine = null;
	
	public ReadFromFile(String fName_IN)
	{
		fName = fName_IN;
		initializeInputFile();
	}
	
	private void initializeInputFile()
	{
		try 
		{
			fr = new FileReader(fName);
			br = new BufferedReader(fr);
		} 
		catch (FileNotFoundException fnfe) 
		{
			System.err.println("Input file not found");
			System.exit(1);
		}
	}
	
	public String readItems() 
	{
		try 
		{
			l_sLine = br.readLine();
		}
		catch(IOException ioe)
		{
			System.err.println("Error in reading file");
			System.exit(1);
		}
		return l_sLine;
	}
}
