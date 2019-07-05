package com.dvd;

public class RegisterLogic {
	DVDInterface dvdDao = new DVDDao();
	public MemberVO MemberInsert(MemberVO paVO) {
		MemberVO raVO = new MemberVO();
		raVO = dvdDao.MemberInsert(paVO);
		return raVO;
	}
	public DVDVO DVDInsert(DVDVO paVO) {
		DVDVO raVO = new DVDVO();
		raVO = dvdDao.DVDInsert(paVO);
		return raVO;
	}
}
