package UI_swing;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class SungJukApp extends	JFrame implements ActionListener {
	//선언부
	//Layout 종류 : BorderLayout, FlowLayout, CardLayout, GridLayout, GridBagLayout,
	//BoxLayout, 
	JPanel		jp_north	=	new JPanel();
	JLabel 		jlb_inwon 	=	new JLabel("인원수",JLabel.RIGHT);
	JLabel 		jlb_myeong 	=	new JLabel("명",JLabel.LEFT);
	JTextField 	jtf_inwon 	=	new JTextField(10);
	JButton 	jbtn_add 	=	new JButton("추가");
	Object		data[][]	=	new Object[0][7];
	String		cols[]		=	{"이름","JAVA","Oracle","JSP","총점","평균","등수"};
	DefaultTableModel 		  dtm_list	=	null;
	DefaultTableColumnModel   dtcm		=	null;
	DefaultListSelectionModel dlsm		=	null;
	TableColumn 				tc1  ,tc2  ,tc3  ,tc4  ,tc5  ,tc6  ,tc7  ;
	DefaultTableCellRenderer 	dtcr1,dtcr2,dtcr3,dtcr4,dtcr5,dtcr6,dtcr7;
	DefaultCellEditor 			dce1 ,dce2 ,dce3 ,dce4 ,dce5 ,dce6 ,dce7 ;
	JTextField					jtf1 ,jtf2 ,jtf3 ,jtf4 ,jtf5 ,jtf6 ,jtf7 ;
	JTableHeader 	jth;
	JTable 			jt_list 		=	null;
	JScrollPane 	jsp_list		=	null;
	Container 		ct				=	this.getContentPane();
	int num = 0;
	//생성자
	/*
	 * 문제 제기
	 * 화면을 처리하는 메소드 호출을 생성자에서 할 수도 있고
	 * 메인 메소드에서 할 수도 있다.
	 * 둘의 차이점에 대해 생각해보자.
	 */
	public SungJukApp() {
		
	}
	//화면처리구현
	public void initDisplay(){
		jbtn_add.addActionListener(this);
		jp_north.add(jlb_inwon);
		jp_north.add(jtf_inwon);
		jp_north.add(jlb_myeong);
		jp_north.add(jbtn_add);
		ct.add("North",jp_north);
		//this.setResizable(false);
		this.setSize(300, 250);
		this.setVisible(true);
		
	}
	//메인메소드
	public static void main(String[] args) {
		SungJukApp sja = new SungJukApp();
		sja.initDisplay();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String inwon = jtf_inwon.getText();
		if(inwon!=null) {
			num = Integer.parseInt(inwon);
		}
		if(obj==jbtn_add) {
			dtm_list = new DefaultTableModel(num,7);
			dtcm = new DefaultTableColumnModel();
			dlsm = new DefaultListSelectionModel();
			jt_list = new JTable(dtm_list,dtcm,dlsm);
			jsp_list = new JScrollPane(jt_list);
			//이름
			dtcr1 = new DefaultTableCellRenderer();
			jtf1 = new JTextField();
			tc1 = new TableColumn(1,120,dtcr1,dce1);
			tc1.setHeaderValue("이름");
			dtcm.addColumn(tc1);
			//JAVA
			dtcr2 = new DefaultTableCellRenderer();
			jtf2 = new JTextField();
			tc2 = new TableColumn(1,120,dtcr2,dce2);
			tc2.setHeaderValue("JAVA");
			dtcm.addColumn(tc2);
			//Oracle
			dtcr3 = new DefaultTableCellRenderer();
			jtf3 = new JTextField();
			tc3 = new TableColumn(1,120,dtcr3,dce3);
			tc3.setHeaderValue("Oracle");
			dtcm.addColumn(tc3);
			//JSP
			dtcr4 = new DefaultTableCellRenderer();
			jtf4 = new JTextField();
			tc4 = new TableColumn(1,120,dtcr4,dce4);
			tc4.setHeaderValue("JSP");
			dtcm.addColumn(tc4);
			//총점
			dtcr5 = new DefaultTableCellRenderer();
			jtf5 = new JTextField();
			tc5 = new TableColumn(1,120,dtcr5,dce5);
			tc5.setHeaderValue("총점");
			dtcm.addColumn(tc5);
			//평균
			dtcr6 = new DefaultTableCellRenderer();
			jtf6 = new JTextField();
			tc6 = new TableColumn(1,120,dtcr6,dce6);
			tc6.setHeaderValue("평균");
			dtcm.addColumn(tc6);
			//등수
			dtcr7 = new DefaultTableCellRenderer();
			jtf7 = new JTextField();
			tc7 = new TableColumn(1,120,dtcr7,dce7);
			tc7.setHeaderValue("등수");
			dtcm.addColumn(tc7);
			
			ct.add("Center",jsp_list);
			ct.validate();
			this.pack();
		}
	}

}
