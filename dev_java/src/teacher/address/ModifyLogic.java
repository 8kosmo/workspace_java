package teacher.address;

public class ModifyLogic {
	AddressBookInterface aBookInterface = new AddressBookDao();
	public AddressVO addressUpdate(AddressVO paVO) {
		AddressVO raVO = null;
		System.out.println("ModifyLogic addressUpdate()호출");
		// insert here
		raVO = aBookInterface.addresssUpdate(paVO);
		return raVO;
	}

}
