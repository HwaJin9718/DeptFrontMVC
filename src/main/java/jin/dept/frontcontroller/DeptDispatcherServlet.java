package jin.dept.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jin.dept.control.Controller;
import jin.dept.controller.DeptDeleteController;
import jin.dept.controller.DeptInsertController;
import jin.dept.controller.DeptSelectAllController;
import jin.dept.controller.DeptSelectDetailController;
import jin.dept.controller.DeptUpdateController;
import jin.dept.controller.DeptUpdateViewController;
import jin.dept.hander.DeptHandlerAdapter;

public class DeptDispatcherServlet extends HttpServlet implements Servlet {
	
	private static final Log log = LogFactory.getLog(DeptDispatcherServlet.class);
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requestURI.substring(contextPath.length());
		log.info("매핑명 조회 - " + pathURL);
		
		DeptHandlerAdapter deptHandlerAdapter = null;
		Controller controller = null;
		
		if (pathURL.equals("/DeptSelectAll.do")) {
			
			controller = new DeptSelectAllController();
			deptHandlerAdapter = controller.execute(request, response);
			log.info("부서 전체 조회 확인 - " + deptHandlerAdapter);
			
		} else if (pathURL.equals("/DeptSelectDetail.do")) {
			
			controller = new DeptSelectDetailController();
			deptHandlerAdapter = controller.execute(request, response);
			log.info("부서 상세 조회 확인 - " + deptHandlerAdapter);

		} else if (pathURL.equals("/DeptInsertView.do")) {
			
			deptHandlerAdapter = new DeptHandlerAdapter();
			deptHandlerAdapter.setPath("/WEB-INF/dept/dept_insert.jsp");
			log.info("부서 등록 화면 뷰 확인 - " + deptHandlerAdapter);
			
		} else if (pathURL.equals("/DeptInsert.do")) {
			
			controller = new DeptInsertController();
			deptHandlerAdapter = controller.execute(request, response);
			log.info("부서 등록 확인 - " + deptHandlerAdapter);
			
		} else if (pathURL.equals("/DeptUpdateView.do")) {
			
			controller = new DeptUpdateViewController();
			deptHandlerAdapter = controller.execute(request, response);
			log.info("부서 수정 화면 뷰 확인 - " + deptHandlerAdapter);
			
		} else if (pathURL.equals("/DeptUpdate.do")) {
			
			controller = new DeptUpdateController();
			deptHandlerAdapter = controller.execute(request, response);
			log.info("부서 수정 확인 - " + deptHandlerAdapter);
			
		} else if (pathURL.equals("/DeptDeleteView.do")) {
			
			deptHandlerAdapter = new DeptHandlerAdapter();
			deptHandlerAdapter.setPath("/WEB-INF/dept/dept_delete.jsp");
			log.info("부서 삭제 화면 뷰 - " + deptHandlerAdapter);
			
		} else if (pathURL.equals("/DeptDelete.do")) {
			
			controller = new DeptDeleteController();
			deptHandlerAdapter = controller.execute(request, response);
			log.info("부서 삭제 확인 - " + deptHandlerAdapter);
			
		}
		
		if (deptHandlerAdapter != null) {
			
			if (deptHandlerAdapter.isRedirect()) {
				response.sendRedirect(deptHandlerAdapter.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(deptHandlerAdapter.getPath());
				dispatcher.forward(request, response);
			}
			
		}
		
	}

}
