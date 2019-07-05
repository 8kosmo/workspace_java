package teacher.address;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.DBConnectionMgr;

public class AddressBookDao implements AddressBookInterface {
	java.sql.Connection					con 	= null;
	java.sql.PreparedStatement			pstmt 	= null;
	java.sql.ResultSet					rs 		= null;
	com.util.DBConnectionMgr			dbMgr	= null;
	@Override
	public AddressVO getAddressDetail(AddressVO paVO) {
		System.out.println("DAO getAddressDetail호출 성공");
		dbMgr = DBConnectionMgr.getInstance();					//4
		StringBuilder sql = new StringBuilder();				//2
		sql.append("SELECT id, name, address, gender, hp  ");	//1
	    sql.append("  ,birthday, comments, regdate        ");	//1
	    sql.append("  FROM MKADDRTB                       ");	//1
	    sql.append(" WHERE id=?                    	      ");	//1
	    AddressVO raVO = null;								//7
	    try {													//4
			con = dbMgr.getConnection();							//4
			pstmt = con.prepareStatement(sql.toString());		//5
			pstmt.setString(1, paVO.getId());
			rs = pstmt.executeQuery();							//6
			//rs.previous()쓰지 않는 이유는 오라클 커서가 항상(디폴트) top에 있으니깐..list.
			//AddressVO는 한행만 담을 수 있는 장애를 가지고 있다.
			if(rs.next()) {
				raVO = new AddressVO();							//9
				raVO.setId(rs.getString("id"));					//10
				raVO.setName(rs.getString("name"));				//10
				raVO.setAddress(rs.getString("address"));		//10
				raVO.setGender(rs.getString("gender"));			//10
				raVO.setBirthday(rs.getString("birthday"));		//10
				raVO.setHp(rs.getString("hp"));					//10
				raVO.setRegdate(rs.getString("regdate"));		//10
				raVO.setComments(rs.getString("comments"));		//10
			}
		} catch (Exception e) {									//4
			e.printStackTrace();
		} finally {//사용한 자원 반납하기. con, pstmt, rs
			dbMgr.freeConnection(con,pstmt,rs);
		}
		return raVO;
	}

	@Override
	public AddressVO addresssInsert(AddressVO paVO) {
		System.out.println("DAO addresssInsert호출 성공");
		AddressVO raVO = new AddressVO();
		dbMgr=DBConnectionMgr.getInstance();
		StringBuilder sql = new StringBuilder();//String쓰지말고 StringBuilder쓴다. 버퍼생김
		int status = 0; //0이면 입력실패, 1이면 입력 성공
		try {
			sql.append("INSERT INTO MKADDRTB (id, name, hp, gender, birthday, comments, address,regdate)								");
			sql.append(" VALUES (?,?,?,?,?,?,?,TO_CHAR(sysdate,'YYYY-MM-DD'))	");
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			int i=0;
			/*
			 * java.sql.SQLException: 인덱스에서 누락된 IN 또는 OUT 매개변수 :1
			 * 원인:PreparedStatement사용시 인덱스 값 치환 누락
			 * 해결방법:?자리에 대응되는 값을 설정할 것.
			 */
			pstmt.setString(++i,paVO.getId());
			pstmt.setString(++i,paVO.getName());
			pstmt.setString(++i,paVO.getHp());
			pstmt.setString(++i,paVO.getGender());
			pstmt.setString(++i,paVO.getBirthday());
			pstmt.setString(++i,paVO.getComments());
			pstmt.setString(++i,paVO.getAddress());
			//입력된 후에 오라클 서버로 부터 응답 받은 값 -int
			status = pstmt.executeUpdate();
			//Dao계층에서 처리된 결과를 리턴타입인 raVO(AddressVO)에 담자
			raVO.setStatus(status);//AddressVO status변수에 1저장
		} catch (SQLException se) {//ORA-XXXXX
			System.out.println(sql.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			dbMgr.freeConnection(con, pstmt);
		}
		return raVO;
	}

	@Override
	public AddressVO addresssUpdate(AddressVO paVO) {
		System.out.println("DAO addresssUpdate호출 성공");
		return null;
	}

	@Override
	public AddressVO addresssDelete(AddressVO paVO) {
		System.out.println("DAO addresssDelete호출 성공");
		//DML구문 작성시 절대 String사용불가
		//문자열을 여러 행 붙일때는 반드시 StringBuilder -싱글스레드 안전-속도빠름
		//혹은 StringBuffer사용할 것. 멀티스레드 안전 why?동기화 처리
		StringBuilder sql = new StringBuilder();
		AddressVO raVO = new AddressVO();
		sql.append("delete from MKADDRTB where id =?");
		dbMgr = DBConnectionMgr.getInstance();
		int status = 0;
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, paVO.getId());
			status = pstmt.executeUpdate();
			raVO.setStatus(status);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return raVO;
	}


	public static void main(String args[]) {
		new AddressBookDao().addresssInsert(null);
	}
//조인한 결과를 화면 출력 - VO와VO 조인한 결과 출력은 MAP으로만 가능하다.
	@Override
	public List<Map<String,Object>> getAddressMap() {// public List<AddressVO> getAddress() {
		System.out.println("Dao getAddress 메소드 호출성공");
		//insert here
		//조회한 결과 n건을 담기 위한 객체 생성
		//배열을 선택하지않는 이유 - 테이블의 정보는 계속 변하기 때문에 - 해결방법:ArrayList or Vector
		List<Map<String,Object>>list = new ArrayList<>();		//3
		dbMgr = DBConnectionMgr.getInstance();					//4
		StringBuilder sql = new StringBuilder();				//2
		sql.append("SELECT id, name, address, gender, hp  ");	//1
	    sql.append("  ,birthday, comments, regdate        ");	//1
	    sql.append("  FROM MKADDRTB                       ");	//1
	    try {													//4
			con = dbMgr.getConnection();							//4
			pstmt = con.prepareStatement(sql.toString());		//5
			rs = pstmt.executeQuery();							//6
			//rs.previous()쓰지 않는 이유는 오라클 커서가 항상(디폴트) top에 있으니깐..list.
			//AddressVO는 한행만 담을 수 있는 장애를 가지고 있다.
			Map<String,Object> rMap = null;								//7
			while(rs.next()) {//몇건이 있는지 몰라서 for문을 쓸수 없다.		//8		//for(;rs.next()){
				rMap = new HashMap<>();							//9
				rMap.put("id",rs.getString("id"));					//10
				rMap.put("name",rs.getString("name"));				//10
				rMap.put("address",rs.getString("address"));		//10
				rMap.put("hp",rs.getString("hp"));					//10
				list.add(rMap);									//11
				//커서가 이동할 때 마다 주소번지가 바뀌므로 주소번지가 덮어쓰기 되지전에
				//그 주소번지를 Map에 담자
			}
		} catch (Exception e) {									//4
			// TODO: handle exception
		} finally {//사용한 자원 반납하기. con, pstmt, rs
			dbMgr.freeConnection(con,pstmt,rs);
		}
		return list;											//3
	}

	@Override
	public List<AddressVO> getAddress() {
		// TODO Auto-generated method stub
		return null;
	}
}
