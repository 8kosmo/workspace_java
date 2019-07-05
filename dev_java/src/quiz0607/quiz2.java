package quiz0607;
	interface Foo{}
	class Alpha implements Foo{}
	class Beta extends Alpha{}
	class Delta extends Beta{
		public static void main(String[]args) {
			Beta x = new Beta();
			Alpha a = x;
			Foo f = (Delta)x;
			Foo f = (Alpha)x;
			Beta b = (Beta)(Alpha)x;
		}
	}
public class quiz2 {}

