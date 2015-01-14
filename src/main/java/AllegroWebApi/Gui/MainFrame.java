package AllegroWebApi.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import AllegroWebApi.iReportGenerate.Raport;
import AllegroWebApiClient.WebApi.App;

public class MainFrame {

	JFrame MainFrame;
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
		double width = 600;
		double height = screenSize.getHeight();
		MainFrame.setSize((int) width, (int) height);
		MainFrame.setLayout(new BorderLayout());
		MainFrame.add(getMainPanel(), BorderLayout.CENTER);
		MainFrame.setVisible(true);
		MainFrame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
		
		
		return MainFrame;
	}

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
		MainPanel.add(getButtomPanel(), BorderLayout.NORTH);
		MainPanel.add(getCategoryListScroll(), BorderLayout.WEST);
		MainPanel.add(getCreateRaport(), BorderLayout.SOUTH);
		return MainPanel;
	}
	JPanel ButtomPanel;
	public JPanel getButtomPanel() {
		ButtomPanel = new JPanel();
		ButtomPanel.setLayout(new BorderLayout());

		ButtomPanel.add(getItemListRespRefresh(), BorderLayout.WEST);
		ButtomPanel.add(getCategoryId(), BorderLayout.CENTER);
		ButtomPanel.add(getDownloadItem(), BorderLayout.EAST);
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
