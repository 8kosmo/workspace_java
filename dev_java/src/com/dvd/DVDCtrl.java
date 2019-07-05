package com.dvd;

import java.util.List;

public class DVDCtrl {
	private static final String _SEL = "select";
	private static final String _DET = "detail";
	private static final String _INS = "insert";
	private static final String _UPD = "update";
	private static final String _DEL = "delete";
	
	public MemberVO send(MemberVO paVO) throws Exception{
		MemberVO raVO = null;
		String command = paVO.getCommand();
		if(_INS.equals(command)) {
			RegisterLogic regLogic = new RegisterLogic();
			raVO = regLogic.MemberInsert(paVO);
		}
		else if(_UPD.equals(command)) {
			ModifyLogic modLogic = new ModifyLogic();
			raVO = modLogic.MemberUpdate(paVO);
		}
		else if(_DEL.equals(command)) {
			DeleteLogic delLogic = new DeleteLogic();
			raVO = delLogic.MemberLogicDelete(paVO);
		}
		else if(_DET.equals(command)) {
			RetrieveLogic retLogic = new RetrieveLogic();
			raVO = retLogic.MemberLogicDetail(paVO);
		}
		else {
			throw new Exception("잘못된 command 입니다.");
		}
		return raVO;
	}
	//=======================전체조회========================
	public List<MemberVO> send_member(String command){
		List<MemberVO> MemberList = null;
		if(_SEL.equals(command)) {
			RetrieveLogic retLogic = new RetrieveLogic();
			MemberList = retLogic.getMemberList();
		}
		return MemberList;
	}
	public List<DVDVO> send_dvd(String command){
		List<DVDVO> DVDList = null;
		if(_SEL.equals(command)) {
			RetrieveLogic retLogic = new RetrieveLogic();
			DVDList = retLogic.getDVDList();
		}
		return DVDList;
	}
}
