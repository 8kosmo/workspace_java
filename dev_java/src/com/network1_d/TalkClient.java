package com.network1_d;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
	JButton jbtn_font 	= new JButton("글꼴");
	JButton jbtn_color 	= new JButton("글자색");
	JButton jbtn_whisper 	= new JButton("1:1대화");
	JButton jbtn_change 	= new JButton("대화명변경");
	JButton jbtn_icon 		= new JButton("이모티콘");
	JButton jbtn_exit 		= new JButton("종료");
	String nickName = null;
	//소켓 선언 - (서버 접속시도 - 객체 손에 쥐면 oos와 ois생성
	Socket mySocket 		= null;
	String ip  				= "192.168.0.22";
	int port				= 3001;
	//말하기는 어디서 하지? - actionPerformed() - 내안에서 처리하기
	//듣기 어디서 하지? - run()처리 - 다른 클래스 처리하기
	//클래스 사이에는 의존관계 - Socket생성 먼저이고 그 소켓을 사용해서 oos생성함.
	ObjectOutputStream oos	= null;
	ObjectInputStream ois	= null;
	//이모티콘 객체 생성 추가
	ImoticonMessage imo = new ImoticonMessage(this);//이모티콘 주입할때 마다 message_process(string msg)호출 해야한다
	//글자색 팔레트 추가할 클래스
	JDialog jdl_color 	= null;
	String fontColor 	= "0";
	
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
		this.setTitle(nickName);
		jbtn_font.addActionListener(this);
		jbtn_color.addActionListener(this);
		jbtn_whisper.addActionListener(this);
		jbtn_icon.addActionListener(this);
		jbtn_change.addActionListener(this);
		jbtn_exit.addActionListener(this);
		this.setLayout(new GridLayout(1, 2));
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
		//윈도우창 닫을 때 사용자원 반납.
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {
					exitChat();
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
		jp_second_south.setLayout(new GridLayout(3,2));
		jp_second_south.add(jbtn_font);
		jp_second_south.add(jbtn_color);
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
	public void exitChat() {
		try {
			oos.writeObject(Protocol.ROOM_OUT
						+Protocol.seperator+this.nickName
						);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	public void change_process() {
		//변경할 대화명 받기
		String afterName = JOptionPane.showInputDialog("변경할 대화명을 입력하세요.");
		if(afterName==null||afterName.length()<2) {
			return;
		}
		try {
			//300|현재대화명|변경대화명|haha가 apple로 변경
			oos.writeObject(Protocol.CHANGE//300
					+Protocol.seperator+nickName
					+Protocol.seperator+afterName
					+Protocol.seperator+nickName+" 님의 대화명이 "+afterName+"으로 변경");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void whisper_process() {
		//상대를 선택
		int row = jtb_name.getSelectedRow();
		if(row==-1) {//end of file 끝까지 찾아봐도 없었다
			JOptionPane.showMessageDialog(this, "상대를 선택하세요");
			return;
		}else {
			String name = (String)dtm_name.getValueAt(row, 0);
			if(nickName.equals(name)) {
				JOptionPane.showMessageDialog(this, "다른 상대를 선택하세요");
				return;
			}
			//대화내용을 받기
			String msg = JOptionPane.showInputDialog(name+" 님에게 보낼 메세지를 입력하세요.");
			try {
				oos.writeObject(Protocol.WHISPER
						+Protocol.seperator+nickName
						+Protocol.seperator+name
						+Protocol.seperator+msg
						+Protocol.seperator+fontColor
						);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//////////////////end of else
		jtb_name.clearSelection();
	}//////////////////////end of whisper_process
	public void message_process(String msg, String imgChoice) {//200|닉네임|메세지
		//이모티콘 메시지를 전송
		if(imgChoice!=null) {
			msg="이모티콘";
			try {
				oos.writeObject(Protocol.MESSAGE//200
						+Protocol.seperator+nickName//닉네임
						+Protocol.seperator+msg//이모티콘
						+Protocol.seperator+imgChoice//선택한 이모티콘 정보 넘김
						+Protocol.seperator+fontColor
						);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//텍스트 메시지를 전송
		else{
			try {
				oos.writeObject(Protocol.MESSAGE//200
						+Protocol.seperator+nickName//닉네임
						+Protocol.seperator+msg//주말에 뭐해?
						+Protocol.seperator+"default"
						+Protocol.seperator+fontColor
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
		if(obj==jbtn_color) {
			jdl_color = new JDialog();
			jdl_color.setSize(600, 500);
			final JColorChooser jcc_color = new JColorChooser();
			ColorSelectionModel model = jcc_color.getSelectionModel();
			ChangeListener listener = new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					Color nfColor = jcc_color.getColor();
					fontColor = String.valueOf(nfColor.getRGB());
				}
				
			};
			model.addChangeListener(listener);
			jdl_color.add(jcc_color);
			jdl_color.setVisible(true);
		}
		else if(obj==jtf_msg) {
			message_process(msg,null);
			jtf_msg.setText("");
		}
		else if(obj==jbtn_whisper) {
			whisper_process();
			jtf_msg.setText("");
		}
		else if(obj==jbtn_icon) {
			//imo = new ImoticonMessage(this);
			imo.setVisible(true);
		}
		else if(obj==jbtn_change) {
			change_process();
		}
		else if(obj==jbtn_exit) {
			exitChat();
			System.exit(0);
		}
	}
}
