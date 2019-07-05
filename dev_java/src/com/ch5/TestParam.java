package com.ch5;
class Param{
	int ival = 0;
}
public class TestParam {
	public void effectParam(Param p) {
		p.ival = 0;/**/
		p = new Param();/**/
		p.ival = 500;/**/
		//insert here - sub ival = ?
		System.out.println(p.ival);
	}
	public static void main(String[] args) {
		TestParam tp = new TestParam();
		Param p = new Param();
		p.ival = 100;
		tp.effectParam(p);
		//insert here = main ival = ?
		System.out.println(p.ival);
	}

}
