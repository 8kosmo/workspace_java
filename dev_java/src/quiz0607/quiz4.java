package quiz0607;
	class Money {
		private String country, name;
		public String getCountry() {
			return country; 
		}
	}
	
	class Yen extends Money {
//		public String getCountry() { 
//			return super.country;//클래스Money의 전역변수 country는 private이다.
//		}//super.는 부모클래스 예약어
	}
	
	class Euro extends Money {
		public String getCountry(String timeZone) {
			return super.getCountry();
		}
	}
public class quiz4 {}
