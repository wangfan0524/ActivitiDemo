package org.crazyit.act.c3;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class FirstAct {

	public static void main(String[] args) {
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		/*�洢����*/
		RepositoryService rs= engine.getRepositoryService();
		/*����ʱ����*/
		RuntimeService runService= engine.getRuntimeService();
		/*�������*/
		TaskService taskService=engine.getTaskService();
		
		rs.createDeployment().addClasspathResource("first.bpmn").deploy();
		//��ȡʵ��
		ProcessInstance pi=runService.startProcessInstanceByKey("myProcess");
		
		System.out.println(pi.getId());
		//��ͨԱ������������
		//��������ʵ������ѯ��ǰ����ʵ����ID��ʹ������id,��ȡ������󣬻�ȡ����ʵ��id
		Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		System.out.println("��ǰ���̽ڵ㣺"+task.getName());
		taskService.complete(task.getId());
		
		/*//ָ��activiti���������ļ�
		ProcessEngineConfiguration config=
				ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");*/
		
		
		//�����������
		task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
	
		System.out.println("��ǰ���̽ڵ㣺"+task.getName());
		taskService.complete(task.getId());
		
		task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		System.out.println("���̽����ˣ�"+task);
		
		engine.close();
		System.exit(0);
	}

}
