package com.dvd;

import java.util.List;

public interface DVDInterface {
	public MemberVO getMemberDetail(MemberVO paVO);
	public MemberVO MemberInsert(MemberVO paVO);
	public MemberVO MemberUpdate(MemberVO paVO);
	public MemberVO MemberDelete(MemberVO paVO);
	public List<MemberVO> getMember();
	
	public DVDVO DVDInsert(DVDVO paVO);
	public DVDVO DVDDelete(DVDVO paVO);
	public List<DVDVO> getDVD();
}
