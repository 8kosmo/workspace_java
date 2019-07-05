package com.emp;

import java.util.List;

public interface empInterface {
	public empVO getAddressDetail(empVO paVO);
	public empVO addressInsert(empVO paVO);
	public empVO addressUpdate(empVO paVO);
	public empVO addressDelete(empVO paVO);
	public List<empVO> getAddress(empVO paVO);
}
