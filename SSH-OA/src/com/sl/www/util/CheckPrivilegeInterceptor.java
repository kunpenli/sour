package com.sl.www.util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sl.www.domain.User;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {
	
	public String intercept(ActionInvocation invocation) throws Exception {
		// System.out.println("---------> ֮ǰ");
		// String result = invocation.invoke(); // ����
		// System.out.println("---------> ֮��");
		// return result;

		// ��ȡ��Ϣ
		User user = (User) ActionContext.getContext().getSession().get("user"); // ��ǰ��¼�û�
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String privUrl = namespace + actionName; // ��Ӧ��Ȩ��URL

		// ���δ��¼
		if (user == null) {
			if (privUrl.startsWith("/user_login")) { // "/user_loginUI", "/user_login"
				// �����ȥ��¼���ͷ���
				return invocation.invoke();
			} else {
				// �������ȥ��¼����ת����¼ҳ��
				return "loginUI";
			}
		}
		// ����ѵ� ¼�����ж�Ȩ��
		else {
			if (user.hasPrivilegeByUrl(privUrl)) {
				// �����Ȩ�ޣ��ͷ���
				return invocation.invoke();
			} else {
				// ���û��Ȩ�ޣ���ת����ʾҳ��
				return "noPrivilegeError";
			}
		}
	}

}
