package teacher.address;
//반환타입 raVO 파라미터타입 paVO
public class DeleteLogic {
	AddressBookInterface aDao = new AddressBookDao();
	public AddressVO addressDelete(AddressVO paVO) {
		System.out.println("DeleteLogic addressDelete호출 성공");
		AddressVO raVO = null; 
		raVO = aDao.addresssDelete(paVO);
		return raVO;
	}

}
