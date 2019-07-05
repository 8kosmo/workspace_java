package teacher.address;
/*
 * NullPointerException - 인스턴스화 하지 않고 선언만 되어 있는 경우
 * 그 주소번지를 사용하여 메소드를 호출하면 바로 그때 NullPointerException발생함.
 * A a = new A(); 스텐다드 객체주입 다형성 기대 할 수 없다.
 * A a = new B(); 다형성 기대 가능
 * A a = B.getXXX(); 기대
 * 3)번이 1),2)번과의 차이점은 싱글톤기대가능
 */
public class RegisterLogic {
	//선언부에 인터페이스 생성부에 구현체 클래스 사용 - 인스턴스화
	//스프링 프레임워크를 이용해서 MVC패턴을 적용할 때 -> 추상클래스, 인터페이스 중심의 코딩전개
	AddressBookInterface aDao = new AddressBookDao();// 객체주입
	public AddressVO addressInsert(AddressVO paVO) {
		System.out.println("RegisterLogic addressInsert 호출 성공");
		AddressVO raVO = new AddressVO();
		raVO = aDao.addresssInsert(paVO);
//		raVO.setStatus(1);
		return raVO;
	}

}
