package com.address;
/*
 * NullPointerException - 인스턴스화 하지 않고 선언만 되어 있는 경우
 * 그 주소번지를 사용하여 메소드를 호출하면 바로 그때 NullPointerException발생함.
 */
public class RegisterLogic {

	public AddressVO addressInsert(AddressVO paVO) {
		System.out.println("Sucess to call RegisterLogic addressInsert ");
		AddressVO raVO = new AddressVO();
		raVO.setStatus(1);
		return raVO;
	}

}
