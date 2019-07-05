package com.address;

public class ModifyLogic {
	AddressBookInterface aBookInterface = new AddressBookDao();
	public AddressVO addressUpdate(AddressVO paVO) {
		AddressVO raVO = null;
		System.out.println("Sucess to call ModifyLogic addressUpdate");
		// insert here
		raVO = aBookInterface.addressUpdate(paVO);
		raVO.setStatus(1);
		return raVO;
	}

}
