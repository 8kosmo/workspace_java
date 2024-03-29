package com.network2_b;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class MessageRoom extends JPanel implements ActionListener{
	//선언부
	TalkClientVer2 tc2 = null;
	JPanel jp_first 			= new JPanel();
	JPanel jp_second 			= new JPanel();
	JPanel jp_second_south 		= new JPanel();
	//메세지 내역 출력 - 비활성함. 이벤트처리 필요없음.
	//메세지 전송할 때 - 이벤트 처리필요함
	JTextField jtf_msg		= new JTextField("메세지를 입력하세요.");
	/*
	 * JTextPane에 스타일을 적용하기 위해서는 스타일을 지원하는 클래스를 추가로 매핑해야함
	 * 왜냐하면 문자도 그리는 개념으로 이해해야 하므로 글꼴을 변경하거나 글크기를 변경하는 부분도
	 * 반영하려면 필요함
	 */
	StyledDocument sd_display = new DefaultStyledDocument(new StyleContext());
	JTextPane jtp_display = new JTextPane(sd_display);
	JScrollPane jsp_display	= new JScrollPane(jtp_display
			,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	String cols[] = {"닉네임"};
	String data[][] = new String[0][1];
	//실제 정보를 담을 객체
	DefaultTableModel	dtm_name = new DefaultTableModel(data,cols);
	//화면을 처리해줄 객체
	JTable 				jtb_name = new JTable(dtm_name);
	JScrollPane 		jsp_name = new JScrollPane(jtb_name);
	JButton jbtn_whisper 	= new JButton("1:1대화");
	JButton jbtn_change 	= new JButton("대화명변경");
	JButton jbtn_icon 		= new JButton("이모티콘");
	JButton jbtn_exit 		= new JButton("종료");
	ImoticonMessage imo = new ImoticonMessage(this);
	
	//생성자
	public MessageRoom() {
		
	}//end of MessageRoom
	public MessageRoom(TalkClientVer2 tc2) {
		this.tc2 = tc2;
	}
	//화면처리부
	public void initDisplay() {
		jbtn_whisper.addActionListener(this);
		jbtn_icon.addActionListener(this);
		jbtn_change.addActionListener(this);
		this.setLayout(new GridLayout(1, 2));
		jbtn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exitChat(e);
			}
		});
		jtf_msg.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				jtf_msg.setText("");
				
			}
		});
		jtf_msg.addActionListener(this);
		jp_first.setLayout(new BorderLayout());
		jp_first.add("Center",jsp_display);
		jp_first.add("South",jtf_msg);
		jp_second.setLayout(new BorderLayout());
		jp_second.add("Center",jsp_name);
		jp_second_south.setLayout(new GridLayout(2,2));
		jp_second_south.add(jbtn_whisper);
		jp_second_south.add(jbtn_change);
		jp_second_south.add(jbtn_icon);
		jp_second_south.add(jbtn_exit);
		jp_second.add("South",jp_second_south);
		this.add("Center",jp_first);
		this.add("East",jp_second);
		this.setSize(500, 600);
		this.setVisible(true);	
	}//end of initDisplay
	protected void exitChat(ActionEvent e) {
		this.setVisible(false);
	}
	
	public void message_process(String msg, String imgChoice) {//200|닉네임|메세지
		//이모티콘 메시지를 전송
		if(imgChoice!=null) {
			msg="이모티콘";
			try {
				tc2.oos.writeObject(Protocol.MESSAGE//200
						+Protocol.seperator+tc2.nickName//닉네임
						+Protocol.seperator+msg//이모티콘
						+Protocol.seperator+imgChoice//선택한 이모티콘 정보 넘김
						);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//텍스트 메시지를 전송
		else{
			try {
				tc2.oos.writeObject(Protocol.MESSAGE//200
						+Protocol.seperator+tc2.nickName//닉네임
						+Protocol.seperator+msg//주말에 뭐해?
						+Protocol.seperator+"default"
						);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		jtf_msg.setText("");
	}
	
	//단위테스트 때문에 추가함 - TalkClientVer2에 붙일것임
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String msg = jtf_msg.getText();
		if(obj==jtf_msg) {
			message_process(msg,null);
			jtf_msg.setText("");
		}
		else if(obj==jbtn_icon) {
			//imo = new ImoticonMessage(this);
			imo.setVisible(true);
		}	
	}//////////////////////////end of actionPerformed
}
