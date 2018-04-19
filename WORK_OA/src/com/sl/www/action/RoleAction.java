package com.sl.www.action;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sl.www.base.BaseAction;
import com.sl.www.domain.Privilege;
import com.sl.www.domain.Role;
import com.sl.www.service.RoleService;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	
	private Integer[] privilegeIds;
	
	public String list() throws Exception{
		List<Role> list=roleService.findAll();
		ActionContext.getContext().put("list", list);
		return "list";
	}
	
	public String addUI() throws Exception{
		
		return "addUI";
	}
	
	public String add() throws Exception{
		roleService.save(model);
		return "toList";
	}
	
	public String editUI() throws Exception{
		//��������
		//����id�����ݿ�����
		Role role=roleService.getById(model.getId());
		//��ջ��
		ActionContext.getContext().getValueStack().push(role);
		return "editUI";
	}
	
	
	public String edit() throws Exception{
		//��һ���������ݿ����õ�ԭ���Ķ��� 
		Role role=roleService.getById(model.getId());
		//�ڶ����������µ�����
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		//�����������µ����ݿ���
		roleService.update(role);
		return "toList";
	}
	
	public String delete() throws Exception{
		roleService.delete(model.getId());
		return "toList";
	}
	
	/** ����Ȩ��ҳ�� */
	public String setPrivilegeUI() throws Exception {
		// ׼�����Ե�����
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);

		if (role.getPrivileges() != null) {
			privilegeIds = new Integer[role.getPrivileges().size()];
			int index = 0;
			for (Privilege priv : role.getPrivileges()) {
				privilegeIds[index++] = priv.getId();
			}
		}

		// ׼������ privilegeList
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList); 

		return "setPrivilegeUI";
	}

	/** ����Ȩ�� */
	public String setPrivilege() throws Exception {
		// 1�������ݿ��л�ȡԭ����
		Role role = roleService.getById(model.getId());

		// 2������Ҫ�޸ĵ�����
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));

		// 3�����µ����ݿ�
		roleService.update(role);

		return "toList";
	}
	
	// ---

	public Integer[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Integer[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}



}
