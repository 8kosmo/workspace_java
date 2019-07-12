package UI_swing;

import java.awt.Color;
import java.awt.Container;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class ExcelFrame extends JInternalFrame {
	JRootPane jrp = new JRootPane();
	Container cp = this.getContentPane();
	/*
	 * DefaultTableModel을 구현한 것들은 데이터가 어떻게 저장되었는지 뿐만 아니라
	 * 어떻게 데이터를 덧붙이고 조작하고 얻어내는지에 대해서 명시한다.
	 * 예를 들면 setValueAt(값,로우index,컬럼index)
	 * 		 getValueAt(로우index,컬럼index)
	 * 또한 DefaultTableModel은 특정셀이 편집될 수 있는지 각 열의 데이터 타입은 무엇인지를 얻어 내는 역할도 함.
	 * JTable의 DefaultTableModel에 있는 데이터 위치는 JTable의 화면에 디스플레이 되었을 때 위치와 일치 하지않는다.
	 */
	DefaultTableModel dtm 			= new DefaultTableModel(100,27);
	/*
	 * DefaultTableColumnModel은 TableColumn의 인스턴스를 관리하기 위해 디자인됨.
	 * TableColumn은 DefaultTableColumnModel 데이터의 한 열을 표현하여
	 * 실제 JTable GUI에 보여지는 열을 관리하는데 필요함.
	 */
	DefaultTableColumnModel dtcm 	= new DefaultTableColumnModel();
	/*
	 * DefaultTableColumnModel은 열의 순서, 선택, 여백 등 TableColumn들을 관리함.
	 * 열을 선택하는 방법을 여러가지로 지원하기 위해 ListSelectionModel을 사용
	 */
	DefaultListSelectionModel dlsm	= new DefaultListSelectionModel();
	JTable jt = new JTable(dtm,dtcm,dlsm);
	JScrollPane jsp = new JScrollPane(jt);
	/*
	 * TableColumn은 JTable에서 화면에 보여지는 것의 기본적인 단위로 테이블의 모델과
	 * JTable GUI 사이를 연결해 주는 부분임.
	 */
	TableColumn[] tc = new TableColumn[27];
	/*
	 * DefaultTableCellRenderer는 TableCellRender 인터페이스를 구현한 디폴트 클래스 이다.
	 * DefaultTableCellRenderer는 JLabel을 상속받고
	 * Number, Icon, Object 데이터 타입을 위한 디폴트 클래스 기반 렌더러로 사용함
	 */
	DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
	JTextField jtf = new JTextField();
	/*
	 * DefaultCellEditor는 TableCellEditor 인터페이스와 TreeCellEditor인터페이스 모두를 구현한다.
	 * 이 에디터는 셀을 편집하기 위해 JTextField, JComboBox, 또는 JCheckBox를 반환하도록 디자인 되었다.
	 */
	DefaultCellEditor dce = new DefaultCellEditor(jtf);
	JTableHeader jth = new JTableHeader();
	public ExcelFrame(String title, boolean a,boolean b,boolean c,boolean d) {
		super(title,true,true,true,true);
		this.initDisplay();
		this.setTitle(title);
		this.setSize(300, 200);
		this.setVisible(true);
	}
	public void initDisplay() {
		tc[0] = new TableColumn(0,75,dtcr,dce);
		tc[0].setHeaderValue("rownum");
		dtcr.setBackground(Color.LIGHT_GRAY);
		//헤더에 출력되는 문자열 중앙정렬
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		dtcm.addColumn(tc[0]);
		//가로줄의 맨상단 헤더값 설정
		for(int i=1;i<27;i++) {
			tc[i] = new TableColumn(i,75);
			//아스키코드표에 의하면 10진수 65 알파벳 A이므로
			tc[i].setHeaderValue((char)(i+64)+"");
			dtcm.addColumn(tc[i]);
		}
		//헤더영역
		jth = new JTableHeader(dtcm);
		//컬럼이 이동할 수 없도록 고정하기
		jth.setReorderingAllowed(false);//default:true
		jt.setTableHeader(jth);
		//한 화면에 보여지는 셀의 갯수 설정
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//한개 셀만 선택하기 옵션활성화 하기
		//false를 주면 한행 전체가 선택되니까
		jt.setCellSelectionEnabled(true);
		//세로줄의 맨앞쪽 로우 수 설정
		for(int i=0;i<100;i++) {
			jt.setValueAt(String.valueOf(i+1), i, 0);
		}
		cp.add("Center",jsp);
	}//end of initDisplay()
}
