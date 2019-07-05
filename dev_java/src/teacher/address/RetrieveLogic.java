package teacher.address;

import java.util.List;
import java.util.Map;

public class RetrieveLogic {
	AddressBookInterface aDao = new AddressBookDao();
	public AddressVO addressDetail(AddressVO paVO) {
		System.out.println("RetrieveLogic addressDetail");
		AddressVO raVO = null;
		raVO = aDao.getAddressDetail(paVO);
		return raVO;
	}

	public List<AddressVO> getAddressList() {
		System.out.println("RetrieveLogic getAddressList호출");
		List<AddressVO>list = null;
		list = aDao.getAddress();
		return list;
	}
	public List<Map<String, Object>> getAddressListMap() {
		System.out.println("RetrieveLogic getAddressList호출");
		List<Map<String, Object>> list = null;
		list = aDao.getAddressMap();
		return list;
	}

}
