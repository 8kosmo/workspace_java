package com.address;

public class AddressBookSimulation {

	public static void main(String[] args) {
		AddressBookCtrl bookCtrl = new AddressBookCtrl();
		AddressVO paVO = new AddressVO();//->command- 입력- AddressVO
		AddressVO raVO = null;
		paVO.setCommand("insert");//private command 접근
		paVO.setId("test");
		paVO.setName("김유신");
		paVO.setHp("01085482645");
		try {
			raVO = bookCtrl.send(paVO);
			if(raVO.getStatus()==1) {
				System.out.println("입력성공");
			}else {
				System.out.println("입력실패");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		/*************************************************/
		paVO = new AddressVO();
		paVO.setCommand("update");
		paVO.setId("test1");
		paVO.setName("강감찬");
		paVO.setHp("01085482645");
		try {
			raVO = bookCtrl.send(paVO);
			if(raVO.getStatus()==1) {
				System.out.println("입력성공");
			}else {
				System.out.println("입력실패");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
