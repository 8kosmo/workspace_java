package com.network2_c;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class WaitRoom extends JPanel implements ActionListener {
	//선언부
	TalkClientVer2 tc2 = null;//절대로 인스턴스화 하지 않는다.
	JPanel jp_first 		= new JPanel();
	JPanel jp_second 		= new JPanel();
	JPanel jp_second_south 	= new JPanel();
	//첫번째 테이블-대기목록
	String cols1[] 		= {"대화명","위치"};
	String data1[][] 	= new String[0][2];
	DefaultTableModel dtm_wait = new DefaultTableModel(data1,cols1);
	//데이터를 가질 수 없음 - 클릭은 화면에서 일어나므로 여기서 처리
	JTable			  jtb_wait = new JTable(dtm_wait);//양식, 폼 제공함
	JScrollPane		  jsp_wait = new JScrollPane(jtb_wait
			,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	//두번째 테이블-현재참여목록
	String cols2[] 		= {"단톡명","현재원"};
	String data2[][] 	= new String[0][2];
	DefaultTableModel dtm_room = new DefaultTableModel(data2,cols2);
	//데이터를 가질 수 없음 - 클릭은 화면에서 일어나므로 여기서 처리
	JTable			  jtb_room = new JTable(dtm_room);//양식, 폼 제공함
	JScrollPane		  jsp_room = new JScrollPane(jtb_room
			,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JButton jbtn_create = new JButton("단톡생성");
	JButton jbtn_in 	= new JButton("입장");
	JButton jbtn_blank 	= new JButton("미정");
	JButton jbtn_exit 	= new JButton("종료");
	JTableHeader jth_wait = jtb_wait.getTableHeader();
	JTableHeader jth_room = jtb_room.getTableHeader();
	//대화명 정보 담기
	String nickName = null;
	//단톡명 담기
	String roomTitle = null;
	//현재원 담기
	int currentNum = 0;
	
	//생성자
	public WaitRoom() {
		
	}
	//tc2에서 소켓과 oos, ois를 소유하고 있다.
	//그 원본들을 나도 접근해야한다.
	public WaitRoom(TalkClientVer2 tc2) {
		this.tc2 = tc2;
		initDisplay();
	}
	//화면처리부
	public void initDisplay() {
		jbtn_create.addActionListener(this);
		jbtn_in.addActionListener(this);
		jbtn_blank.addActionListener(this);
		jbtn_exit.addActionListener(this);
		//JFrame은 디폴트가 BorderLayout이나 필요에 따라 다른 레이아웃으로 설정가능함.
		this.setLayout(new GridLayout(1,2));
		jp_first.setLayout(new BorderLayout());
		jp_first.add("Center",jsp_wait);
		this.add(jp_first);
		jp_second.setLayout(new BorderLayout());
		jp_second.add("Center",jsp_room);
		jp_second_south.setLayout(new GridLayout(2,2));
		jp_second_south.add(jbtn_create);
		jp_second_south.add(jbtn_in);
		jp_second_south.add(jbtn_blank);
		jp_second_south.add(jbtn_exit);
		jp_second.add("South",jp_second_south);
		this.add(jp_second);
		this.setVisible(true);
	}
	//단위테스트 때문에 추가함 - TalkClientVer2에 붙일것임
	public static void main(String[] args) {
//		new WaitRoom(null);
	}
	public void intro_process() {
		//테이블에서 선택한 로우 값 담기
		int index = jtb_room.getSelectedRow();
		if(index<0) {
			JOptionPane.showMessageDialog(tc2, "단톡 선택하세요");
			return;//intro_process 탈출
		}else {
			try {
				//방목록에서 선택한 로우 찾기
				for(int i=0;i<dtm_room.getRowCount();i++) {
					//선택한 로우의 indext로 확인
					if(jtb_room.isRowSelected(i)) {
						String roomTitle = (String)dtm_room.getValueAt(i, 0);
						tc2.oos.writeObject(Protocol.ROOM_IN
											+Protocol.seperator+roomTitle
											+Protocol.seperator+tc2.nickName
											);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		//선택한 로우 정보 삭제
		jtb_room.clearSelection();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj == jbtn_in ) {
			intro_process();
		}//end of if(obj == jbtn_in )
		else if(obj == jbtn_create) {
			roomTitle = JOptionPane.showInputDialog("단톡명 입력하세요");
			if(roomTitle != null) {
				try {
					//==> 110|단톡명|0
					tc2.oos.writeObject(Protocol.ROOM_CREATE
										+Protocol.seperator+roomTitle
										+Protocol.seperator+0
										);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}//end of if(obj == jbtn_create)
	}//////end of actionPerformed

}
