
import java.io.File;
import java.io.FileInputStream;

import java.security.MessageDigest;

public class MD5Calculations{
	public String getCheckSums(File path) throws Exception {
			//creates a "pipe" to open a file....
			FileInputStream fis = new FileInputStream(path);
		
			//creates MessageDigest Object...
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			//creates that a variable that will hold the content of the specified file
			//byte by byte			
			byte databuff[] = new byte[1024];
			
			//get the contents of a certain file
			int nread = fis.read(databuff);
			
				while (nread > 0) {
					md.update(databuff, 0,  nread);
					nread = fis.read(databuff);
				}
				
				byte mdbytes[] = md.digest();
				
//				StringBuffer variable to store character that may grow dynamically
				StringBuffer hexStrBuff = new StringBuffer();
				
//				translates each hashed files into hexadecimal and store it on a string
				for (int i=0; i<mdbytes.length; i++) {
					String hexStrTemp = Integer.toHexString(0xFF & mdbytes[i]);
					
					if (hexStrTemp.length() == 1)
						hexStrBuff.append('0');
					
							hexStrBuff.append(hexStrTemp);
				}
//				returns the computed MD5 hash of a certain file
				return hexStrBuff.toString();	
	}
}
