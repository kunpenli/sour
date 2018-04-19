package com.sl.www.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sl.www.base.BaseAction;
import com.sl.www.domain.Department;
import com.sl.www.service.DepartmentService;
import com.sl.www.util.DepartmentUtil;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{
	
	private Integer parentId;
	
	
	public String list() throws Exception{
		
//		List<Department> list=departmentService.findAll();
//		ActionContext.getContext().put("list", list);
		
		//��ѯ��������
		if (parentId==null) {
			List<Department> list=departmentService.findTopList();
			ActionContext.getContext().put("list", list);
		}else{
			List<Department> list=departmentService.findChildrenList(parentId);
			ActionContext.getContext().put("list", list);
			
			ActionContext.getContext().put("parent", departmentService.getById(parentId));
		
		}
		//��ѯ�Ƕ�������
		
		
		return "list";
	}
	
	public String addUI() throws Exception{
		//׼������������
//		List<Department> list=departmentService.findAll();
//		ActionContext.getContext().put("list", list);
		
		List<Department> treeList=DepartmentUtil.getTreeList(departmentService.findTopList());
		ActionContext.getContext().put("list", treeList);
		
		return "addUI";
	}
	
	public String add() throws Exception{
		//��װû�з�װ��model�е�����
		model.setParent(departmentService.getById(parentId));
		
		departmentService.save(model);
		return "toList";
	}
	
	public String editUI() throws Exception{
		//��������
		//����id�����ݿ�����
		Department department=departmentService.getById(model.getId());
		//��ջ��
		ActionContext.getContext().getValueStack().push(department);
		
		//׼������������
		List<Department> list=departmentService.findAll();
		ActionContext.getContext().put("list", list);
		
		//�����ϼ�����
		if (department.getParent()!=null) {
			parentId=department.getParent().getId();
		}
		
		return "editUI";
	}
	
	
	public String edit() throws Exception{
		//��һ���������ݿ����õ�ԭ���Ķ��� 
		Department department=departmentService.getById(model.getId());
		//�ڶ����������µ�����
		department.setParent(departmentService.getById(parentId));
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		//�����������µ����ݿ���
		departmentService.update(department);
		return "toList";
	}
	
	public String delete() throws Exception{
		departmentService.delete(model.getId());
		return "toList";
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	

}
