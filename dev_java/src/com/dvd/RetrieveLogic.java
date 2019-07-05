package com.dvd;

import java.util.List;

public class RetrieveLogic {
	DVDInterface dvdDao = new DVDDao();
	
	public MemberVO MemberLogicDetail(MemberVO paVO) {
		MemberVO raVO = null;
		raVO = dvdDao.getMemberDetail(paVO);
		return raVO;
	}
	public List<MemberVO> getMemberList(){
		List<MemberVO>list = null;
		list = dvdDao.getMember();
		return list;
	}
	public List<DVDVO> getDVDList(){
		List<DVDVO>list = null;
		list = dvdDao.getDVD();
		return list;
	}
}
