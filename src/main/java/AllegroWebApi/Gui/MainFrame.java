package AllegroWebApi.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import AllegroWebApi.H2Database.ItemAttribsJDBCImpl;
import AllegroWebApi.iReportGenerate.Raport;
import AllegroWebApiClient.WebApi.App;

public class MainFrame {

	JFrame MainFrame;

	JPanel MainPanel;
	
	JFrame doLoginFrame;
	JPanel doLoginPanel;
	
	JTable CategoryList;
	TextField CategoryId;
	JScrollPane CategoryListScroll;
	JButton ItemListRespRefresh;


	App AllegroFunctionality;
	ArrayList<ArrayList<String>> CategoryListResp;
	String[][] tempdata;
	String[][] selectedCategores;
	String[][] allCategories;
	String selectedRow;
	String selectedRowPrevious;
	JButton DownloadItem;
	JTextField Login;
	JPasswordField Password;
	JButton doLogin;
	String sessionHandlePart;
	JButton CreateRaport;
	JPanel RaportPanel;
	
	ItemAttribsJDBCImpl ItemConncetionList=new ItemAttribsJDBCImpl();

	
	JButton clearDatabase;
	JTextArea XMLFileContent;
	JScrollPane XMLFileContentScroll;
	JFrame RaportFrame;
	
	public JFrame getRaportFrame() {
		RaportFrame= new JFrame("Raport");
		RaportFrame.setSize(700,900);
		RaportFrame.setLayout(new BorderLayout());
		RaportFrame.add(getRaportPanel(), BorderLayout.CENTER);
		RaportFrame.setVisible(true);
		RaportFrame.setResizable(false);
		RaportFrame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
		
		return doLoginFrame;
	}
	
	public JScrollPane getXMLFileContentScroll() {
		XMLFileContentScroll=new JScrollPane(getXMLFileContent());
		return XMLFileContentScroll;
	}
	public JTextArea getXMLFileContent() {
		XMLFileContent=new JTextArea();
		
		return XMLFileContent;
	}
	JButton chooseJRXMLFile;
	public JButton getChooseJRXMLFile() {
		chooseJRXMLFile= new JButton("Pobierz plik");
		chooseJRXMLFile.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fileReader(FileChooser());
				getRaportFrame();
			}
		});
		return chooseJRXMLFile;
	}

	public String fileReader(String path){
		getXMLFileContentScroll();
		String content=null;
		   try {
			   BufferedReader br = new BufferedReader(new FileReader(path));
			   
		        String line = br.readLine();

		        while (line != null) {
		           
		            line = br.readLine();
		            System.out.println(line);
		            XMLFileContent.append(line);
		            XMLFileContent.append("\n");
		        }
		        
		    }
		   catch(IOException e){
			   e.printStackTrace();
		   }
		return content;
	}
	public void fileWriter(String content){
		try {
			 
 
			File file = new File("Raport.jrxml");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public String FileChooser(){

		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Jrxml files", "Jrxml");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(MainFrame);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " +
	            chooser.getSelectedFile().getPath());
	    }	
	    return chooser.getSelectedFile().getPath();
	}
	
	
	public JButton getClearDatabase() {
		clearDatabase= new JButton("Czyść baze danych");
		clearDatabase.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					ItemConncetionList.clearDatabase();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return clearDatabase;
	}

	public JPanel getRaportPanel(){
		
		RaportPanel= new JPanel();
		RaportPanel.setLayout(new BorderLayout());
		RaportPanel.add(getCreateRaport(), BorderLayout.SOUTH);

		RaportPanel.add(XMLFileContentScroll, BorderLayout.CENTER);
		return RaportPanel;
	}
	
	
	public JFrame getMainFrame() {
		MainFrame = new JFrame();
		
		CategoryListResp = AllegroFunctionality.doGetCatsData();
		//AllegroFunctionality.itemListUpdate(AllegroFunctionality.doGetItemsList("49256"), sessionHandlePart);
		
		allCategories = new String[CategoryListResp.get(0).size()][CategoryListResp
				.size()];

		for (int i = 0; i < CategoryListResp.get(0).size(); i++) {
			for (int j = 0; j < CategoryListResp
					.size(); j++) {
				allCategories[i][j] = CategoryListResp.get(j).get(i);
			}
		}
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = 900;
		double height = screenSize.getHeight();
		MainFrame.setSize((int) width, (int) height);
		MainFrame.setLayout(new BorderLayout());
		MainFrame.add(getMainPanel(), BorderLayout.CENTER);
		
		MainFrame.setVisible(true);
		MainFrame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
		
		
		return MainFrame;
	}

	public JFrame getDoLoginFrame() {
		doLoginFrame= new JFrame("Zaloguj się");
		doLoginFrame.setSize(250,120);
		doLoginFrame.setLayout(new BorderLayout());
		doLoginFrame.add(getDoLoginPanel(), BorderLayout.CENTER);
		doLoginFrame.setVisible(true);
		doLoginFrame.setResizable(false);
		doLoginFrame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
		
		return doLoginFrame;
	}

	public JPanel getDoLoginPanel() {
		doLoginPanel= new JPanel();
		doLoginPanel.add(new JLabel("Login"),BorderLayout.NORTH);
		doLoginPanel.add(getLogin(),BorderLayout.NORTH);
		doLoginPanel.add(new JLabel("Hasło"),BorderLayout.CENTER);
		doLoginPanel.add(getPassword(),BorderLayout.CENTER);
		doLoginPanel.add(getDoLogin(),BorderLayout.SOUTH);
		return doLoginPanel;
	}

	public JButton getDoLogin() {
		doLogin=new JButton("Zaloguj");
		doLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println(Password.getText());
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					
					@Override
					protected Void doInBackground() {
						//sessionHandlePart=AllegroFunctionality.doLogin(Login.getText(), Password.getText());
						sessionHandlePart=AllegroFunctionality.doLogin("Luksky92","Mjuhygv123");
						return null;
					};

					@Override
					protected void done() {
						System.out.println(sessionHandlePart);	
						if(sessionHandlePart!=null){
							getMainFrame();
							doLoginFrame.setVisible(false);
						}
						else{
							JOptionPane.showMessageDialog(doLoginFrame,
								    "Błędny login lub hasło",
								    "Inane error",
								    JOptionPane.ERROR_MESSAGE);
						}
					};
				};
				worker.execute();
				
			}
		});
		return doLogin;
	}

	
	public JButton getCreateRaport() {
		CreateRaport= new JButton("Generuj raport");
		CreateRaport.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fileWriter(XMLFileContent.getText());
				new Raport();
			}
		});
		
		return CreateRaport;
	}

	public JTextField getLogin() {
		Login=new JTextField(16);
		return Login;
	}

	public JPasswordField getPassword() {
		Password = new JPasswordField(16);
		return Password;
	}


	
	public JButton getItemListRespRefresh() {
		ItemListRespRefresh = new JButton("Podkategoria");
		final Object[] columnNames = { "Nazwa kategorii", "Id kategorii", "Id nadkategorii" };
		ItemListRespRefresh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() {
						int k =0;
						String id=CategoryId.getText();
						for (int i = 0; i < allCategories.length; i++) {
							if(allCategories[i][2].equals(id)){
								k++;
							}
						}
						selectedCategores= new String[k][3]; 
						k=0;
						for (int i = 0; i < allCategories.length; i++) {
							if(allCategories[i][2].equals(id)){
								selectedCategores[k][0]=allCategories[i][0];
								selectedCategores[k][1]=allCategories[i][1];
								selectedCategores[k][2]=allCategories[i][2];
								k++;
							}
						}	
						return null;
					};

					@Override
					protected void done() {
						TableModel dataModel = new DefaultTableModel(
								selectedCategores, columnNames);
						CategoryList.setModel(dataModel);
						CategoryList.repaint();

					};
				};
				worker.execute();

			}
		});

		return ItemListRespRefresh;
	}


	public JScrollPane getCategoryListScroll() {
		CategoryListScroll = new JScrollPane(getCategoryList());
		return CategoryListScroll;
	}

	public TextField getCategoryId() {
		CategoryId = new TextField(selectedRow);
		CategoryId.setSize(100, 30);
		return CategoryId;
	}

	public JPanel getMainPanel() {
		MainPanel = new JPanel();
		MainPanel.setLayout(new BorderLayout());
		MainPanel.add(getButtomPanel(), BorderLayout.SOUTH);
		MainPanel.add(getCategoryListScroll(), BorderLayout.WEST);
		ButtomPanel.add(getChooseJRXMLFile(), BorderLayout.NORTH);
		return MainPanel;
	}
	JPanel ButtomPanel;
	public JPanel getButtomPanel() {
		ButtomPanel = new JPanel();
		ButtomPanel.setLayout(new BorderLayout());

		ButtomPanel.add(getItemListRespRefresh(), BorderLayout.WEST);
		ButtomPanel.add(getCategoryId(), BorderLayout.CENTER);
		ButtomPanel.add(getDownloadItem(), BorderLayout.EAST);

		ButtomPanel.add(getClearDatabase(),BorderLayout.NORTH);
		return ButtomPanel;
	}

	public JButton getDownloadItem() {
		DownloadItem= new JButton("Pobierz przedmioty");
		DownloadItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AllegroFunctionality.itemListUpdate(AllegroFunctionality.doGetItemsList(CategoryId.getText()), sessionHandlePart);
			}
		});
		return DownloadItem;
	}

	public JTable getCategoryList() {

		Object[] columnNames = { "Nazwa kategorii", "Id kategorii", "Id nadkategorii"};
		int k=0;
		for (int i = 0; i < allCategories.length; i++) {
			if(allCategories[i][2].equals("0")){
				k++;
			}
		}
		selectedCategores= new String[k][3]; 
		k=0;
		for (int i = 0; i < allCategories.length; i++) {
			if(allCategories[i][2].equals("0")){
				selectedCategores[k][0]=allCategories[i][0];
				selectedCategores[k][1]=allCategories[i][1];
				selectedCategores[k][2]=allCategories[i][2];
				k++;
			}
		}		
		CategoryList = new JTable(selectedCategores, columnNames);
		CategoryList.getSelectionModel().addListSelectionListener(

		new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub

				try {
					selectedRow = (String) CategoryList.getValueAt(
							CategoryList.getSelectedRow(), 1);
					selectedRowPrevious = (String) CategoryList.getValueAt(
							CategoryList.getSelectedRow(), 2);
					CategoryId.setText(selectedRow);
		
				} catch (Exception e) {
			
				}
			}

		});

		return CategoryList;
	}

	public MainFrame() {
		//getDoLoginFrame();
		
		AllegroFunctionality = new App();
		getMainFrame();
	}
}
