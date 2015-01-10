/* ******************************************************************************************************
*Components.java is where the application's components such as button, text area are defined and created.
*Also in this class, defines what each components will do on a particular event.
*********************************************************************************************************/
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
public class Components extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1667316849990795112L;
	public Components(){
		initComponents();
		btnCalculate.addActionListener(this);
		btnBrowse1.addActionListener(this);
		btnAbout.addActionListener(this);
		btnHelp.addActionListener(this);
		
	}
	
	public static void initComponents(){
		
		frame = new JFrame("File Clone Checkpoint v1.0");
//****************************************************
//		BUTTON INSTANTIATION (W/ ICON)
//****************************************************
		ImageIcon remIco = new ImageIcon("rem2.gif"); 
		btnBrowse1 = new JButton("Browse...");
		btnBrowse2 = new JButton("Browse...");
		btnCalculate = new JButton("Get hash");
		btnAbout = new JButton("About");
		btnHelp = new JButton("Help");
		buttonSetProperties(remIco);

//****************************************************
//		TEXTFIELD INSTANTIATION
//****************************************************
		textField1 = new JTextField(20);
		textField2 = new JTextField(20);
		textField3 = new JTextField(20);
//****************************************************
//		TEXT AREA INSTANTIATION
//****************************************************
		displayArea = new JTextArea(14,37);
		textAreaSetProperties();
//****************************************************
//		JSCOLLPANE INSTANTIATION
//****************************************************
		js = new JScrollPane(displayArea);
		js.setAutoscrolls(true);
//****************************************************
//		LABEL INSTANTIATION
//****************************************************
		label1 = new JLabel("Directory");
		label2 = new JLabel("File ");
		label3 = new JLabel("File 3");
		about = new JLabel("About");
		help = new JLabel("Help");
		labelTitle = new JLabel();
		about2 = new JLabel();
		
		labelSetProperties();
//****************************************************	
//		PANEL INSTANTIATION
//****************************************************
		paneTitle = new JPanel(new FlowLayout(FlowLayout.CENTER,16,17));
		pane1 = new JPanel(new FlowLayout(FlowLayout.LEFT,16,17));
		pane2 = new JPanel(new FlowLayout(FlowLayout.CENTER,11,10));
		pane3 = new JPanel(new FlowLayout(FlowLayout.CENTER,11,10));
		jDialogPane = new JPanel(new FlowLayout(FlowLayout.CENTER,11,50));
		helpDialogPane = new JPanel(new FlowLayout(FlowLayout.CENTER,11,10));
		paneSetProperties();
//****************************************************
//		FRAME RENDERING SECTION
//****************************************************				
		frameSetProperties();
		frame.setLocation(370,140);
		frame.pack();
		frame.setSize(470, 500);
		frame.setVisible(true);
	}
	
//	Sets the properties of the panels
	public static void paneSetProperties(){
		pane1.setBackground(SystemColor.BLACK);
		pane2.setBackground(SystemColor.DARK_GRAY);
		pane3.setBackground(SystemColor.DARK_GRAY);
		jDialogPane.setBackground(SystemColor.BLACK);
		paneTitle.setBackground(SystemColor.BLACK);
		paneTitle.setBounds(12, 150, 430, 120);
		pane3.setBounds(12, 170, 430, 280);
		pane2.setBounds(12, 50, 430, 80);
		paneTitle.add(labelTitle);
		pane2.add(label1);
		pane2.add(textField1);
		pane2.add(btnBrowse1);
		pane2.add(btnCalculate);
		pane3.add(js);
		pane3.add(btnAbout);
//		pane3.add(btnHelp);
		jDialogPane.add(about);
		jDialogPane.add(about2);
	}
	
	public static void buttonSetProperties(Icon ico){	
		btnAbout.setBackground(SystemColor.BLACK);
		btnAbout.setForeground(SystemColor.GREEN);
		btnHelp.setBackground(SystemColor.BLACK);
		btnHelp.setForeground(SystemColor.GREEN);
		btnBrowse1.setBackground(SystemColor.BLACK);
		btnCalculate.setBackground(SystemColor.BLACK);
		btnBrowse1.setForeground(SystemColor.GREEN);
		btnCalculate.setForeground(SystemColor.GREEN);
	}
	
	public static void labelSetProperties(){
		label1.setForeground(SystemColor.GRAY);
		label2.setForeground(SystemColor.GRAY);
		label3.setForeground(SystemColor.GRAY);
		String title = "Let's check for any file duplication ;-)";
		labelTitle.setText(title);
		labelTitle.setForeground(SystemColor.GREEN);
		about2.setForeground(SystemColor.GRAY);
		about2.setText("     Copyright \u00A9 2012 by unhandled_exception");
		about.setText("File Clone Checkpoint v1.0 ");
		about.setForeground(SystemColor.GREEN);
	}
	
	public static void frameSetProperties(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(pane3);
		frame.add(pane2);
		frame.add(pane1);
		frame.add(paneTitle);	
		frame.setResizable(false);
	}
	
	public static void textAreaSetProperties(){
		displayArea.setAutoscrolls(true);
		displayArea.setEditable(false);
		displayArea.setBackground(SystemColor.BLACK);
		displayArea.setForeground(SystemColor.GREEN);
		displayArea.append("File Clone Checkpoint v1.0 by unhandled_exception\nApplication ready.");
		DefaultCaret caret = (DefaultCaret)displayArea.getCaret(); 
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); 
	}
	
	public static void jScrollPaneProperties(){
		
	}
	public void keyReleased(KeyEvent k){
		int id = k.getID();
		if(id==k.VK_ENTER){
			performAction();
		}
	}
	public void actionPerformed(ActionEvent evt){
		Object source = evt.getSource();
		if(source == btnBrowse1){
			try{
				Actions.btnBrowse1Event();
				textField1.setText(Actions.fileChoosedPath());
			}
			catch(Exception e){	
			}	
		}
		else if(source == btnAbout){
			Actions.aboutAction();
		}
		else if(source == btnCalculate){
			flies = new File(textField1.getText());
			allFiles = flies.listFiles();
			performAction();
			
		}
		else if(source == btnHelp){
			Actions.helpAction();
		}	
	}

	public static void performAction(){
		try{
			Runnable r = new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub	
					if(!flies.exists()){
						Actions.processFiles(allFiles);
						displayArea.setText("File Clone Checkpoint v1.0 by unhandled_exception\nApplication ready.");
						displayArea.append("\n\nNo files found. Check the specified path.");			
					}
					else{
						Actions.processFiles(allFiles);
						displayArea.append("\n----------------------------------------------END--------------------------------------------------\n");
						displayArea.append("\""+textField1.getText() +"\""+ " analysis complete.");
						textField1.setText("");
					}
				}
			};
			
			Thread tr = new Thread(r);
			tr.start();
			
		
		}
		catch(Exception e){
			jop.showMessageDialog(frame, "Cannot Process files");
		}	
	}

	public static JFrame frame;
	public static JTextField textField1,textField2, textField3;
	public static JButton btnBrowse1, btnBrowse2, btnCalculate, btnAbout, btnHelp;
	public static JPanel pane1, pane2, pane3, paneTitle, jDialogPane, helpDialogPane;
	private static JLabel label1, label2, label3, labelTitle, about, about2, help;
	public static JTextArea displayArea;
	public static File file1[], file2[], allFiles[], flies ;
	private static JOptionPane jop = new JOptionPane();
	private static JScrollPane js;
	public static JDialog jd, helpMe;

}
