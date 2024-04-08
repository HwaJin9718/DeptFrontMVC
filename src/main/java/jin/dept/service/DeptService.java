package jin.dept.service;

import java.util.ArrayList;

import jin.dept.dto.DeptDTO;

public interface DeptService {
	
	public ArrayList<DeptDTO> deptSelectAll();
	
	public DeptDTO deptSelectDetail(int deptno);
	
	public DeptDTO deptInsert(DeptDTO deptDTO);
	
	public DeptDTO deptUpdate(DeptDTO deptDTO);
	
	public DeptDTO deptDelete(int deptno);

}
