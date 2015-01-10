/* *********************************************************************
 * Actions.java contains the buttons behavior
 ***********************************************************************/
import java.io.File;
import java.util.*;
import javax.swing.*;
public class Actions extends Components{
	static File ff[], folder;
	static JFileChooser chooser;
	static JOptionPane jop;
	public static int btnBrowse1Event(){
		chooser = new JFileChooser( new File(".") ) 
		{ 
		    public void approveSelection() 
		    { 
		        if (getSelectedFile().isFile()) 
		        { 
		            return; 
		        } 
		        else 
		            super.approveSelection(); 
		    } 
		};
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setCurrentDirectory(chooser.getCurrentDirectory());
		return chooser.showOpenDialog(frame);
	}
	
	public static String getSelectedFiles(){
		String path = chooser.getSelectedFile().getAbsolutePath();
		return path;
	}
	
	public static String fileChoosedPath(){
		return chooser.getSelectedFile().getAbsolutePath();
	}
	
	public static void processFiles(File[] eachFiles){
		
		String files, files2, files3 = "";
		HashMap<String, String> hMap = new HashMap<String, String>();
		MD5Calculations mds = new MD5Calculations();
		ArrayList<String> duplicateList = new ArrayList<String>();
		ArrayList<String> hashedMessages = new ArrayList<String>();
		ArrayList<String> anyFiles = new ArrayList<String>();
		displayArea.setText("File Clone Checkpoint v1.0 by unhandled_exception\nApplication ready.\n");
		displayArea.append("\nAnalyzing " + textField1.getText() + "\nPlease wait...\n");
		
		try{
//			Gets each files in a certain directory
			for (int i = 0; i < eachFiles.length; i++) { 
		        if (eachFiles[i].isFile()) {
		        	files = eachFiles[i].getName();
		            files2 = mds.getCheckSums(eachFiles[i]);
					hMap.put(files, files2);
		            hashedMessages.add(files);
	            	files3 += files + " : " + files2 + "\n";
		        }       
			}
			displayArea.append("------------------------------------HASHED FILES------------------------------------\n\n");
			displayArea.append(files3);
//			filters the duplicated files and store it on Arraylist duplicate files		
			for(String fileName : hashedMessages){
				for(String fileName2 : hashedMessages){
					if(!(fileName.equals(fileName2.toString()))){
						if(hMap.get(fileName).equals(hMap.get(fileName2))){
							anyFiles.add(fileName2);
							if(!duplicateList.contains(fileName)){
								duplicateList.add(fileName);
							}
						}
					}
				}
			}
//			if there are no any duplicated files found
			if(duplicateList.isEmpty()){
				jop.showMessageDialog(frame, "No duplicate files found");
				
			}
			else{
//			displays the duplicated files filename and MD5 hashes 
				String dups = Integer.toString(duplicateList.size());
				jop.showMessageDialog(frame, ("There are " + dups + " duplicated files."));
				displayArea.append("\n\n--------------------------------------Duplicated Files------------------------------------\n\n");
				for(String each: duplicateList){
					displayArea.append(each + " : " + hMap.get(each) + "\n");
				}
			}
			
		}
//		if invalid directory is detected
		catch (Exception e) {
			// TODO Auto-generated catch block
			jop.showMessageDialog(frame, "Invalid directory. Please check the specified path.", "Invalid directory", JOptionPane.ERROR_MESSAGE );
		}
	}
	
	public static void aboutAction(){
		jd = new JDialog(frame, "About", true);
		jd.add(jDialogPane);
		jd.setSize(400,200);
		jd.setLocation(396, 250);
		jd.setVisible(true);	
	}
	
	public static void helpAction(){
		helpMe = new JDialog(frame, "Help", true);
		helpMe.add(helpDialogPane);
		helpMe.setSize(400,200);
		helpMe.setLocation(396, 250);
		helpMe.setVisible(true);
	}
}
