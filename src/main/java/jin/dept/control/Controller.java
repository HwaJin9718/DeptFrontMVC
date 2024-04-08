package jin.dept.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jin.dept.hander.DeptHandlerAdapter;

public interface Controller {
	
	public DeptHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response);

}
