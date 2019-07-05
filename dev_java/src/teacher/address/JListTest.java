package teacher.address;

import javax.swing.JFrame;
import javax.swing.JList;

public class JListTest extends JFrame {

	public static void main(String[] args) {
		JListTest jlt = new JListTest();
		String[] data = {"DVD명", "감독", "배우"};
		JList<String> myList = new JList<String>(data);
		jlt.add("Center",myList);
		jlt.setSize(400, 300);
		jlt.setVisible(true);
	}

}
