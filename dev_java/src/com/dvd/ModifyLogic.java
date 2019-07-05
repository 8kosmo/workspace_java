package com.dvd;

public class ModifyLogic {
	DVDInterface dvdDao = new DVDDao();
	public MemberVO MemberUpdate(MemberVO paVO) {
		MemberVO raVO = null;
		raVO = dvdDao.MemberUpdate(paVO);
		return raVO;
	}
}
