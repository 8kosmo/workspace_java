package com.network1_c;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

//클라이언트 측에서는 Runnable하지 않았다. - 단일스레드이다
//why? 경합, 선택에 따른 지속적인 서비스
public class TalkClient extends JFrame implements ActionListener{
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
	String nickName = null;
	String youName	= null;
	//소켓 선언 - (서버 접속시도 - 객체 손에 쥐면 oos와 ois생성
	Socket mySocket 		= null;
	String ip  				= "192.168.0.22";
	int port				= 3001;
	int index 				= -1;
	//말하기는 어디서 하지? - actionPerformed() - 내안에서 처리하기
	//듣기 어디서 하지? - run()처리 - 다른 클래스 처리하기
	//클래스 사이에는 의존관계 - Socket생성 먼저이고 그 소켓을 사용해서 oos생성함.
	ObjectOutputStream oos	= null;
	ObjectInputStream ois	= null;
	//이모티콘 객체 생성 추가
	ImoticonMessage imo = null;//이모티콘 주입할때 마다 message_process(string msg)호출 해야한다
	
	public TalkClient() {//3.
		nickName = JOptionPane.showInputDialog(this,"대화명을 입력하세요");
		initDisplay();
		try {
			mySocket = new Socket(ip,port);
			//actionPerformed
			oos = new ObjectOutputStream(mySocket.getOutputStream());
			//thread에서 사용 run()
			ois = new ObjectInputStream(mySocket.getInputStream());
			//서버에 내가 로그인 했음을 알림.
			oos.writeObject(Protocol.ROOM_IN+Protocol.seperator+nickName); //110|nickName
			TalkClientThread tct = new TalkClientThread(this);
			tct.start();//run메소드 호출됨 - 콜백함수
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void initDisplay() {
		jbtn_icon.addActionListener(this);
		this.setLayout(new GridLayout(1, 2));
		jbtn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exit(e);
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
		jbtn_whisper.addActionListener(this);
		//윈도우창 닫을 때 사용자원 반납.
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {
					if(mySocket != null) {
						mySocket.close();
						mySocket = null;
					}
					System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	}//end of initDisplay()//end of initDisplay()//end of initDisplay()//end of initDisplay()//end of initDisplay()
	protected void exit(ActionEvent e) {
		this.setVisible(false);
	}
//	protected void whisper(ActionEvent e) {
//		String label = e.getActionCommand();
//		System.out.println("버튼");
//		index = jtb_name.getSelectedRow();
//		String msg = jtf_msg.getText();
//		if(index<0) {
//			JOptionPane.showMessageDialog(this, "대화 상대를 클릭하세요");
//			return;
//		}else {
//			try {
//				youName = (String)jtb_name.getValueAt(index, 0);
//				message_process(msg);
//				
//				
//				jtb_name.clearSelection();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}
//	}
	public void exitChat() {
		
	}
	public void whisper_process(String msg) {
		try {
			oos.writeObject(Protocol.WHISPER
							+Protocol.seperator+youName
							+Protocol.seperator+msg
							+Protocol.seperator+index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void message_process(String msg, String imgChoice) {//200|닉네임|메세지
		//이모티콘 메시지를 전송
		if(imgChoice!=null) {
			msg="이모티콘";
			try {
				oos.writeObject(Protocol.MESSAGE//200
						+Protocol.seperator+nickName//닉네임
						+Protocol.seperator+msg//이모티콘
						+Protocol.seperator+imo.imgChoice//선택한 이모티콘 정보 넘김
						);
//				imo.imgChoice ="default";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//텍스트 메시지를 전송
		else {
			try {
				oos.writeObject(Protocol.MESSAGE//200
						+Protocol.seperator+nickName//닉네임
						+Protocol.seperator+msg//주말에 뭐해?
						+Protocol.seperator+"default"
						);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		jtf_msg.setText("");
	}
	public static void main(String[] args) {
		TalkClient tc = new TalkClient();//3.
	}
	//말하기 인가? 아님 듣기 인가? 말하기
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String msg = jtf_msg.getText();
		if(obj==jtf_msg) {
			message_process(msg,null);
			jtf_msg.setText("");
		}
		else if(obj==jbtn_whisper) {
		}
		else if(obj==jbtn_icon) {
			imo = new ImoticonMessage(this);
			imo.setVisible(true);
		}
	}
}
