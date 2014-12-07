package AllegroWebApi.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import AllegroWebApiClient.WebApi.App;

public class MainFrame {

	JFrame MainFrame = new JFrame();
	JPanel MainPanel;
	JTable CategoryList;
	TextField CategoryId;
	JScrollPane CategoryListScroll;

	App AllegroFunctionality;
	ArrayList<ArrayList<String>> ItemListResp;
	String[][] tempdata;
	String[][] data;
	String selectedRow;

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

						selectedRow = (String) getCategoryList().getValueAt(
								CategoryList.getSelectedRow(), 2);
						System.out.println(selectedRow);
						getCategoryId().repaint();
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
