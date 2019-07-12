package UI_swing;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class InnerFrame extends JInternalFrame {
	String data[][]= {
			 {"1_1","1_2","1_3"}
			,{"2_1","2_2","2_3"}
			,{"3_1","3_2","3_3"}
	};
	String cols[]= {"1반","2반","3반"};
	JTable jt = new JTable(data,cols);
	JScrollPane jsp = new JScrollPane(jt);

	public InnerFrame(String title,boolean a,boolean b,boolean c,boolean d) {
		super(title,a,d,b,d);
		this.setTitle(title);
		this.setSize(300, 200);
		this.setVisible(true);
	}
}//end of InnerFrame 
public class JInternalFrameTest extends JFrame implements ActionListener {
	JRootPane jrp = this.getRootPane();//JMenuBar관련 api
	Container cp = this.getContentPane();//swing component붙임
	JDesktopPane jdp = new JDesktopPane();
	JPanel jp_south = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JButton jbtn_new = new JButton("새창");
	JButton jbtn_excel = new JButton("엑셀");
	int i = 1;
	public void initDisplay() {
		jbtn_new.addActionListener(this);
		jbtn_excel.addActionListener(this);
		jp_south.add(jbtn_new);
		jp_south.add(jbtn_excel);
		cp.add("South",jp_south);
		cp.add("Center",jdp);//센터에 들어올 내부 프레임 정보 InnerFrame
		this.setSize(700, 400);
		this.setVisible(true);
	}//end of initDisplay()
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==jbtn_new) {
			InnerFrame inn = new InnerFrame("새창-"+i++, true, true, true, true);
			jdp.add(inn);
		}else if(arg0.getSource()==jbtn_excel) {
			ExcelFrame inn = new ExcelFrame("엑셀-"+i++, true, true, true, true);
			jdp.add(inn);
		}
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JInternalFrameTest jif = new JInternalFrameTest();
		jif.initDisplay();
	}

}
