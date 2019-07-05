package com.dvd;

public class DeleteLogic {
	DVDInterface dvdDao = new DVDDao();
	public MemberVO MemberLogicDelete(MemberVO paVO) {
		MemberVO raVO = null;
		raVO = dvdDao.MemberDelete(paVO);
		return raVO;
	}
	public DVDVO DVDLogicDelete(DVDVO paVO) {
		DVDVO raVO = null;
		raVO = dvdDao.DVDDelete(paVO);
		return raVO;
	}
}
