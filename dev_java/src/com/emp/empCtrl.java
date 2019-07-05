package com.emp;

import java.util.List;

public class empCtrl {
	private static final String _SEL = "select";
	private static final String _DET = "detail";
	private static final String _INS = "insert";
	private static final String _UPD = "update";
	private static final String _DEL = "delete";
	public empVO send(empVO paVO) throws Exception {
		empVO raVO = null;
		String command = paVO.getCommand();
		if(_INS.equals(command)) {
			Register reg = new Register();
			raVO = reg.empInsert(paVO);
		}
		else if(_UPD.equals(command)) {
			Modify mod = new Modify();
			raVO = mod.empUpdate(paVO);
		}
		else if(_DEL.equals(command)) {
			Delete del = new Delete();
			raVO = del.empDelete(paVO);
		}
		else if(_DET.equals(command)) {
			Retrieve ret = new Retrieve();
			raVO = ret.empDetail(paVO);
		}else {
			throw new Exception("잘못된 command 입니다.");
		}
		return raVO;
	}
	public List<empVO> send(String command){
		List<empVO> addrList = null;
		if(_SEL.equals(command)) {
		}
		return addrList;
	}
}
