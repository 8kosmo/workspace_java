package com.dvd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MemberView extends JDialog {
	//입력화면 구성
	private JLabel labelId;
	private JTextField txtId;
	private JLabel labelPw;
	private JTextField txtPw;
	private JLabel labelName;
	private JTextField txtName;
	private JLabel labelBirth;
	private JTextField txtBirth;
	private JLabel labelGen;
	private JComboBox comboGender;
	private JLabel labelAddress;
	private JTextField txtAddress;
	
	Font font = new Font("cola",Font.BOLD,14);
	JPanel jp_center = new JPanel();
	JPanel jp_south = new JPanel();
	JButton jbtn_save = new JButton("저장");
	JButton jbtn_cancel = new JButton("취소");
	String title = null;
	DVDManagerView dvdmanagerview = null;
	JScrollPane jsp_line = new JScrollPane(jp_center);
	MemberVO memberVO = null;
	DVDVO dvdVO = null;
	
	public MemberView () {
		initDisplay();
	}
	public void set(MemberVO memberVO,String title,DVDManagerView dvdmanagerview,boolean isEdit) {
		this.memberVO  = memberVO;
		this.title = title;
		this.dvdmanagerview = dvdmanagerview;
		this.setValue();
		this.setTitle(title);
		this.setVisible(true);
		if("회원정보수정".equals(this.title)){
			this.setEditable(false);
		}else {
			this.setEditable(isEdit);
		}
	}
	private void initDisplay() {
		jp_center.setLayout(null);
		jp_center.setBackground(new Color(212,140,106));
		jbtn_save.setBackground(new Color(85,28,0));
		jbtn_save.setForeground(Color.white);
		jbtn_cancel.setBackground(new Color(85,28,0));
		jbtn_cancel.setForeground(Color.white);
		//항목 객체 생성
		labelId = new JLabel("ID");
		labelPw = new JLabel("Password");
		labelName = new JLabel("이름");
		labelBirth = new JLabel("생년월일");
		labelGen = new JLabel("성별");
		labelAddress = new JLabel("주소");
//		labelId.setFont(font);
//		labelPw.setFont(font);
//		labelName.setFont(font);
//		labelBirth.setFont(font);
//		labelGen.setFont(font);
//		labelAddress.setFont(font);
		//데이터입력 객체 생성
		txtId = new JTextField(20);
		txtPw = new JTextField(20);
		txtName = new JTextField(20);
		txtBirth = new JTextField(20);
		String[] genderList = {"남자","여자"};
		comboGender = new JComboBox(genderList);
		txtAddress = new JTextField(20);
		//화면객체 배치
		labelId.setBounds(20,20, 150,20);
		txtId.setBounds	(120,20, 150,20);
		labelPw.setBounds(20,45, 150,20);
		txtPw.setBounds	(120,45, 150,20);
		labelName.setBounds(20,70, 150,20);
		txtName.setBounds (120,70, 150,20);
		labelBirth.setBounds(20,95, 150,20);
		txtBirth.setBounds (120,95, 150,20);
		labelGen.setBounds	  (20,120, 150,20);
		comboGender.setBounds(120,120, 150,20);
		comboGender.setFont(font);
		labelAddress.setBounds(20,145, 150,20);
		txtAddress.setBounds (120,145, 150,20);
		
		jp_center.add(labelId);
		jp_center.add(labelPw);
		jp_center.add(labelName);
		jp_center.add(labelBirth);
		jp_center.add(labelGen);
		jp_center.add(labelAddress);
		jp_center.add(txtId);
		jp_center.add(txtPw);
		jp_center.add(txtName);
		jp_center.add(txtBirth);
		jp_center.add(comboGender);
		jp_center.add(txtAddress);
		this.setLayout(new BorderLayout());
		jp_south.add(jbtn_save);
		jp_south.add(jbtn_cancel);
		this.add("South",jp_south);
		this.add("Center",jsp_line);
		
		this.setTitle(title);
		this.setSize(400, 300);
		this.setVisible(false);
		
		jbtn_cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				quit(e);
			}
		});
		jbtn_save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				memberSave(e);
			}
		});

	}//end of initDisplay
	
	public void setValue() {
		if(memberVO==null) {
			setId("");
			setPw("");
			setName("");
			setBirth("");
			setGen("");
			setAddress("");
		}else {
			setId(memberVO.getId());
			setPw(memberVO.getPw());
			setName(memberVO.getName());
			setBirth(memberVO.getBirth());
			setGen(memberVO.getGen());
			setAddress(memberVO.getAddress());
		}
	}
	public void setEditable(boolean isEdit) {
		txtId.setEditable(isEdit);
//		txtPw.setEditable(isEdit);
//		txtName.setEditable(isEdit);
//		txtBirth.setEditable(isEdit);
//		txtAddress.setEditable(isEdit);
	}
	
	public String getId() {
		return txtId.getText();}
	public void setId(String id) {
		txtId.setText(id);}
	
	
	public String getPw() {
		return txtPw.getText();}
	public void setPw(String pw) {
		txtPw.setText(pw);}
	
	public String getName() {
		return txtName.getText();}
	public void setName(String name) {
		txtName.setText(name);}
	
	public String getBirth() {
		return txtBirth.getText();}
	public void setBirth(String birth) {
		txtBirth.setText(birth);}
	
	public String getGen() {
		if(comboGender.getSelectedItem().equals("남자")) return "1";
		else return "0";}
	public void setGen(String gen) {
		if(gen.equals("1")) comboGender.setSelectedItem("남자");
		else comboGender.setSelectedItem("여자");}
	
	public String getAddress() {
		return txtAddress.getText();}
	public void setAddress(String address) {
		txtAddress.setText(address);}
	
	protected void memberSave(ActionEvent e) {
		String label = e.getActionCommand();
		if("저장".equals(label)) {
			if(memberVO != null) {
				try {
					MemberVO paVO = new MemberVO();
					paVO.setCommand("update");
					paVO.setId(memberVO.getId());
					paVO.setPw(getPw());
					paVO.setName(getName());
					paVO.setBirth(getBirth());
					paVO.setGen(getGen());
					paVO.setAddress(getAddress());
					DVDCtrl dvdctrl = new DVDCtrl();
					MemberVO raVO = dvdctrl.send(paVO);				
					if(raVO!=null) {
						if(raVO.getStatus()==1) {
							JOptionPane.showMessageDialog(this, "수정성공");
							this.dispose();
						}else {
							JOptionPane.showMessageDialog(this, "수정실패");
							return;
						}
					}
					
				}catch (Exception e2) {
				}		
			}
			else {
				try {
					MemberVO paVO = new MemberVO();
					paVO.setCommand("insert");
					paVO.setId(getId());
					paVO.setPw(getPw());
					paVO.setName(getName());
					paVO.setBirth(getBirth());
					paVO.setGen(getGen());
					paVO.setAddress(getAddress());
					DVDCtrl dvdctrl = new DVDCtrl();
					MemberVO raVO = dvdctrl.send(paVO);
					if(raVO!=null) {
						if(raVO.getStatus()==1) {
							JOptionPane.showMessageDialog(this, "입력성공");
							this.dispose();
						}else {
							JOptionPane.showMessageDialog(this, "입력실패");
							return;
						}
					}
				} catch (Exception e2) {
				}
			}
		}
		 this.dispose();
         dvdmanagerview.refreshData();
	}
	protected void quit(ActionEvent e) {
		this.setVisible(false);
	}

}