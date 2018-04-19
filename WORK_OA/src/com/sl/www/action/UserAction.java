package com.sl.www.action;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.sl.www.base.BaseAction;
import com.sl.www.domain.Department;
import com.sl.www.domain.Role;
import com.sl.www.domain.User;
import com.sl.www.util.DepartmentUtil;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	
	private Integer departmentId;
	private Integer[] roleIds;

	/** �б� */
	public String list() throws Exception {
		 List<User> userList = userService.findAll();
		 ActionContext.getContext().put("userList", userList);

		return "list";
	}

	/** ɾ�� */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/** ���ҳ�� */
	public String addUI() throws Exception {
		// ׼������, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtil.getTreeList(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// ׼������, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "addUI";
	}

	/** ��� */
	public String add() throws Exception {
		// ��װ�������У���model��ʵ������ʱ��Ҳ����ʹ��model����Ҫ����δ��װ�����ԣ�
		// >> ������������
		model.setDepartment(departmentService.getById(departmentId));
		// >> ���ù����ĸ�λ
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		// >> ����Ĭ������Ϊ1234��Ҫʹ��MD5ժҪ��
		String md5Digest = DigestUtils.md5Hex("1234");
		model.setPassword(md5Digest);

		// ���浽���ݿ�
		userService.save(model);

		return "toList";
	}

	/** �޸�ҳ�� */
	public String editUI() throws Exception {
		// ׼������, departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtil.getTreeList(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// ׼������, roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		// ׼�����Ե�����
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		if (user.getRoles() != null) {
			roleIds = new Integer[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}

		return "editUI";
	}

	/** �޸� */
	public String edit() throws Exception {
		// 1�������ݿ���ȡ��ԭ����
		User user = userService.getById(model.getId());

		// 2������Ҫ�޸ĵ�����
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// >> ������������
		user.setDepartment(departmentService.getById(departmentId));
		// >> ���ù����ĸ�λ
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));

		// 3�����µ����ݿ�
		userService.update(user);

		return "toList";
	}

	/** ��ʼ������Ϊ1234 */
	public String initPassword() throws Exception {
		// 1�������ݿ���ȡ��ԭ����
		User user = userService.getById(model.getId());

		// 2������Ҫ�޸ĵ����ԣ�Ҫʹ��MD5ժҪ��
		String md5Digest = DigestUtils.md5Hex("1234");
		user.setPassword(md5Digest);

		// 3�����µ����ݿ�
		userService.update(user);

		return "toList";
	}

	/** ��¼ҳ�� */
	public String loginUI() throws Exception {
		return "loginUI";
	}

	/** ��¼ */
	public String login() throws Exception {
		User user = userService.findByLoginNameAndPassword(model.getLoginName(), model.getPassword());
		if (user == null) {
			addFieldError("login", "�û��������벻��ȷ��");
			return "loginUI";
		} else {
			// ��¼�û�
			ActionContext.getContext().getSession().put("user", user);
			
			//���ڲ˵���չʾ
			Map<String,Object> applicationTop=ActionContext.getContext().getApplication();
			applicationTop.put("topPrivilegeList", privilegeService.findTopList());
			
			//���ڵ�¼���ж�
			Map<String,Object> applicationAll=ActionContext.getContext().getApplication();
			applicationAll.put("allPrivilegeUrls", privilegeService.getAllPrivilegeUrls());
			return "toIndex";
		}
	}

	/** ע�� */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}

	// ---

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}


}
