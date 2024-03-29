package com.ch15;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class MyEditor extends JFrame {
	//선언부
	URL imgURL = getClass().getResource("memo.jpg");
	ImageIcon appIcon = new ImageIcon(imgURL);
	//container 객체 변수를 선언
	Container myCon = null;
	//메뉴바 추가하기
	//메뉴아이템을 메뉴에 추가하고 메뉴를 메뉴바에 추가함.
	JMenuBar jmb 	= new JMenuBar();
	JMenu jm_file 		= new JMenu("File");//New, Open, Save, Exit
	JMenuItem jmi_new 	= new JMenuItem("New");
	JMenuItem jmi_open 	= new JMenuItem("Open");
	JMenuItem jmi_save 	= new JMenuItem("Save");
	JMenuItem jmi_exit 	= new JMenuItem("Exit");
	JMenu jm_edit 	= new JMenu("Edit");//Copy, Paste, Cut
	JMenuItem jmi_copy 	= new JMenuItem("Copy");
	JMenuItem jmi_paste = new JMenuItem("Paste");
	JMenuItem jmi_cut 	= new JMenuItem("Cut");
	//입력용 텍스트 영역 작성하기
	JTextArea jta_text = new JTextArea();
	JScrollPane jsp_text = new JScrollPane(jta_text
			,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	JFileChooser jfc = new JFileChooser();
	String imgPath = ".//src//images//";
	//툴바 추가하기 - 북쪽에 배치함.
	JToolBar jtb = new JToolBar();
	JButton jbtn_ins = new JButton();
	JButton jbtn_sel = new JButton();
	JButton jbtn_upd = new JButton();
	JButton jbtn_del = new JButton();
	//생성자
	public MyEditor() {
		System.out.println(imgURL);
	}
	//화면처리부
	private void initDisplay() {
		jmi_cut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jta_text.cut();
			}
		});
		jmi_paste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jta_text.paste();
			}
		});
		jmi_copy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jta_text.copy();
			}
		});
		jbtn_ins.setIcon(new ImageIcon(imgPath+"new.gif"));
		jbtn_sel.setIcon(new ImageIcon(imgPath+"detail.gif"));
		jbtn_upd.setIcon(new ImageIcon(imgPath+"update.gif"));
		jbtn_del.setIcon(new ImageIcon(imgPath+"delete.gif"));
		jtb.add(jbtn_ins);
		jtb.add(jbtn_sel);
		jtb.add(jbtn_upd);
		jtb.add(jbtn_del);
		jmi_save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//저장 대화상자 오픈
				int ret = jfc.showSaveDialog(MyEditor.this);
				if(ret==JFileChooser.APPROVE_OPTION) {
					//파일 저장 처리- OutputStream
					try {
						File myFile = jfc.getSelectedFile();//파일 객체 생성
						PrintWriter pwriter = new PrintWriter(new BufferedWriter(new FileWriter(myFile.getAbsolutePath())));
						pwriter.write(jta_text.getText());
						pwriter.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
			}
		});
		//익명클래스(내부 클래스의 일종)
		//자바스크립트 - 내부에 메소드나 변수 선언 처리
		//$("#dg").datagrid({프로그램 코딩부}); 와 비슷한 형태
		//ActionListener는 인터페이스 이다. - 자신만으로 인스턴스화 불가, 구현체클래스 있어야함.
		//캡차, 네아로, 카카오, UI, Spring Boot 등등
		jmi_open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//열기 대화상자를 오픈
				int ret = jfc.showOpenDialog(MyEditor.this);
				//yes 나 혹은 ok버튼을 클릭했을 때
				if(ret == JFileChooser.APPROVE_OPTION) {
					//파일을 여는 처리
					try {
						//읽어들이기 String - Gson, JsonObject, JsonArray
						String strLine;
						//파일명에 대한 객체생성만 됨. 내용은 담을 수 없다.(IO필요)
						File myFile = jfc.getSelectedFile();
						//선택한 파일의 절대경로를 지정해서 BufferedReader생성함.
						BufferedReader br = new BufferedReader(new FileReader(myFile.getAbsolutePath()));
						jta_text.setText(br.readLine());
						//2행 부터는 행바꾸기 코드를 넣어줌.
						while((strLine=br.readLine())!=null) {
							jta_text.append("\n"+strLine);
						}
						br.close();
					} catch (IOException ie) {
						System.out.println(ie.toString());
					}
				}
			}//////////end of actionPerformed
		});////////////end of jmi_open
		myCon = this.getContentPane();
		myCon.setLayout(new BorderLayout());
		jm_file.add(jmi_new);
		jm_file.add(jmi_open);
		jm_file.add(jmi_save);
		jm_file.add(jmi_exit);
		jm_edit.add(jmi_copy);
		jm_edit.add(jmi_paste);
		jm_edit.add(jmi_cut);
		jm_file.setMnemonic('F');
		jm_edit.setMnemonic('E');
		jmb.add(jm_file);
		jmb.add(jm_edit);
		myCon.add("Center",jsp_text);
		myCon.add("North",jtb);
		jmb.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.setJMenuBar(jmb);
		//Look & Feel 설정
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.setIconImage(appIcon.getImage());
		this.setTitle("메모장");
		this.setSize(500, 300);
		this.setVisible(true);
	}//end of initDisplay()
	//메인메소드
	public static void main(String[] args) {
		MyEditor myEditor = new MyEditor();
		myEditor.initDisplay();
	}
}
