package AllegroWebApi.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import AllegroWebApiClient.WebApi.App;

public class MainFrame {

	JFrame MainFrame = new JFrame();
	JPanel MainPanel;
	JTable CategoryList;
	TextField CategoryId;
	JScrollPane CategoryListScroll;
	JButton ItemListRespRefresh;
	

	App AllegroFunctionality;
	ArrayList<ArrayList<String>> ItemListResp;
	String[][] tempdata;
	Object[][] data;
	String selectedRow;

	public JButton getItemListRespRefresh() {
		ItemListRespRefresh=new JButton("> > >");
		final Object[] columnNames = { "Nazwa kategorii", "Ilośc przedmiotów",
		"Id kategorii" };
		ItemListRespRefresh.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				ItemListResp = AllegroFunctionality.doGetItemsList(CategoryId.getText(), Integer.parseInt(CategoryId.getText()));
				data = new String[ItemListResp.get(0).size()][ItemListResp.size()];
				for (int i = 0; i < ItemListResp.get(0).size(); i++) {
					for (int j = 0; j < ItemListResp.size(); j++) {
						data[i][j] = ItemListResp.get(j).get(i);
					}
				}
				TableModel dataModel= new DefaultTableModel(data, columnNames);
				CategoryList.setModel(dataModel);
				CategoryList.repaint();
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
		MainPanel.add(getCategoryId(), BorderLayout.PAGE_START);
		
		MainPanel.add(getCategoryListScroll(), BorderLayout.CENTER);
		MainPanel.add(getItemListRespRefresh(), BorderLayout.PAGE_START);
		return MainPanel;
	}

	public JTable getCategoryList() {

		Object[] columnNames = { "Nazwa kategorii", "Ilośc przedmiotów",
		"Id kategorii" };
		data = new String[ItemListResp.get(0).size()][ItemListResp.size()];
		for (int i = 0; i < ItemListResp.get(0).size(); i++) {
			for (int j = 0; j < ItemListResp.size(); j++) {
				data[i][j] = ItemListResp.get(j).get(i);
			}
		}

		CategoryList = new JTable(data, columnNames);
		CategoryList.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent arg0) {
						// TODO Auto-generated method stub

							selectedRow = (String) CategoryList.getValueAt(
									CategoryList.getSelectedRow(), 2);
						CategoryId.setText(selectedRow);
						/*
						 * tempdata = new
						 * String[ItemListResp.get(0).size()][ItemListResp
						 * .size()]; System.out.println("Start"); ItemListResp =
						 * AllegroFunctionality.doGetItemsList(
						 * Integer.toString(selectedRow), selectedRow);
						 * 
						 * for (int i = 0; i < ItemListResp.get(0).size(); i++)
						 * { for (int j = 0; j < ItemListResp.size(); j++) {
						 * tempdata[i][j] = ItemListResp.get(j).get(i); }
						 * 
						 * } data=tempdata;
						 * 
						 * System.out.println("Stop"); getCategoryList();
						 * getCategoryList().repaint();
						 */}

				});

		
		return CategoryList;
	}

	public MainFrame() {
		AllegroFunctionality = new App();

		// System.out.println(AllegroFunctionality.doLogin("luksky92","kanalia665",1,
		// 1415799229,"569a5dcc"));
		ItemListResp = AllegroFunctionality.doGetItemsList("1", 0);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth() - 55;
		double height = screenSize.getHeight();
		MainFrame.setSize((int) width, (int) height);
		MainFrame.setLayout(new BorderLayout());
		MainFrame.add(getMainPanel(), BorderLayout.CENTER);
		MainFrame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
		MainFrame.setVisible(true);

	}
}
