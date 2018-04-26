package com.sl.www.util;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sl.www.domain.Privilege;
import com.sl.www.domain.User;

@Component
public class Install {
	
	@Resource
	private SessionFactory sessionFactory;

	/**
	 * ִ�а�װ
	 */
	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();

		// ==============================================================
		// ���泬������Ա�û�
		User user = new User();
		user.setLoginName("admin");
		user.setName("��������Ա");
		user.setPassword(DigestUtils.md5Hex("admin"));
		session.save(user); // ����

		// ==============================================================
		// ����Ȩ������
		Privilege menu, menu1, menu2, menu3, menu4, menu5;

		// --------------------
		menu = new Privilege("ϵͳ����", null, null);
		menu1 = new Privilege("��λ����", "/role_list", menu);
		menu2 = new Privilege("���Ź���", "/department_list", menu);
		menu3 = new Privilege("�û�����", "/user_list", menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);

		session.save(new Privilege("��λ�б�", "/role_list", menu1));
		session.save(new Privilege("��λɾ��", "/role_delete", menu1));
		session.save(new Privilege("��λ���", "/role_add", menu1));
		session.save(new Privilege("��λ�޸�", "/role_edit", menu1));

		session.save(new Privilege("�����б�", "/department_list", menu2));
		session.save(new Privilege("����ɾ��", "/department_delete", menu2));
		session.save(new Privilege("�������", "/department_add", menu2));
		session.save(new Privilege("�����޸�", "/department_edit", menu2));

		session.save(new Privilege("�û��б�", "/user_list", menu3));
		session.save(new Privilege("�û�ɾ��", "/user_delete", menu3));
		session.save(new Privilege("�û����", "/user_add", menu3));
		session.save(new Privilege("�û��޸�", "/user_edit", menu3));
		session.save(new Privilege("��ʼ������", "/user_initPassword", menu3));

		// --------------------
		menu = new Privilege("���Ͻ���", null, null);
		menu1 = new Privilege("��̳����", "/forumManage_list", menu);
		menu2 = new Privilege("��̳", "/forum_list", menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);

		// --------------------
		menu = new Privilege("������ת", null, null);
		menu1 = new Privilege("�������̹���", "/processDefinition_list", menu);
		menu2 = new Privilege("����ģ�����", "/template_list", menu);
		menu3 = new Privilege("�������", "/flow_templateList", menu);
		menu4 = new Privilege("��������", "/flow_myTaskList", menu);
		menu5 = new Privilege("�ҵ������ѯ", "/flow_myApplicationList", menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
	}

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Install installer = (Install) ac.getBean("install");
		installer.install();
	}

}
