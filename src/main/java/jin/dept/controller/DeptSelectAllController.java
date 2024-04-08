package jin.dept.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.dept.control.Controller;
import jin.dept.dao.DeptDAO;
import jin.dept.dto.DeptDTO;
import jin.dept.hander.DeptHandlerAdapter;

public class DeptSelectAllController implements Controller {
	
	private static final Log log = LogFactory.getLog(DeptSelectAllController.class);
	
	@Override
	public DeptHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		
		DeptDAO deptDAO = new DeptDAO();
		DeptDTO deptDTO = new DeptDTO();
		ArrayList<DeptDTO> arrayList = new ArrayList<DeptDTO>();
		
		arrayList = deptDAO.deptSelectAll();
		request.setAttribute("arrayList", arrayList);
		DeptHandlerAdapter deptHandlerAdapter = new DeptHandlerAdapter();
		log.info("전체 부서 조회");
		deptHandlerAdapter.setPath("/WEB-INF/dept/dept_select_all_view.jsp");
		
		return deptHandlerAdapter;
	}

}
