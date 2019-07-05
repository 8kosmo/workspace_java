package quiz0527;

import javax.swing.JButton;

public class quiz1 {

	public static void main(String[] args) {
		int []x= {1,2,3,4,5};
		int y[] = x; // y[]=x[]
		System.out.println(y[2]);
		JButton jbtn_save = new JButton("저장");
		JButton jbtn_imsi = null;
		jbtn_imsi =  jbtn_save;
		System.out.println(jbtn_imsi.getText());
		
	}

}
