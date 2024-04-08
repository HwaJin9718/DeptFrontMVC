package jin.dept.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.dept.dto.DeptDTO;
import jin.dept.service.DeptService;

public class DeptDAO implements DeptService {
	
	private static final Log log = LogFactory.getLog(DeptDAO.class);
	
	@Override
	public ArrayList<DeptDTO> deptSelectAll() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<DeptDTO> arrayList = new ArrayList<DeptDTO>();
		
		try {
			
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			
			String sql = "select deptno, dname, loc from dept";
			log.info("SQL 확인 - " + sql);
			
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				DeptDTO deptDTO = new DeptDTO();
				deptDTO.setDeptno(resultSet.getInt("deptno"));
				deptDTO.setDname(resultSet.getString("dname"));
				deptDTO.setLoc(resultSet.getString("loc"));
				arrayList.add(deptDTO);
			}
			
			if (resultSet.getRow() == 0) {
				log.info("등록한 부서가 없습니다. 부서를 등록해주세요.");
			}
			
		} catch (NamingException e) {
			log.info("부서 전체 조회 실패(NamingException) - " + e);
			e.printStackTrace();
		} catch (SQLException e) {
			log.info("부서 전체 조회 실패(SQLException) - " + e);
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return arrayList;
	}
	
	@Override
	public DeptDTO deptSelectDetail(int deptno) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DeptDTO deptInsert(DeptDTO deptDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DeptDTO deptUpdate(DeptDTO deptDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DeptDTO deptDelete(int deptno) {
		// TODO Auto-generated method stub
		return null;
	}

}
