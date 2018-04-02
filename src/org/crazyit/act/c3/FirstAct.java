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
		/*存储服务*/
		RepositoryService rs= engine.getRepositoryService();
		/*运行时服务*/
		RuntimeService runService= engine.getRuntimeService();
		/*任务服务*/
		TaskService taskService=engine.getTaskService();
		
		rs.createDeployment().addClasspathResource("first.bpmn").deploy();
		//获取实例
		ProcessInstance pi=runService.startProcessInstanceByKey("myProcess");
		
		System.out.println(pi.getId());
		//普通员工完成请假任务
		//根据流程实例来查询当前流程实例的ID，使用任务id,获取任务对象，获取流程实例id
		Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		System.out.println("当前流程节点："+task.getName());
		taskService.complete(task.getId());
		
		/*//指定activiti引擎配置文件
		ProcessEngineConfiguration config=
				ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");*/
		
		
		//经理审核任务
		task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
	
		System.out.println("当前流程节点："+task.getName());
		taskService.complete(task.getId());
		
		task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
		System.out.println("流程结束了："+task);
		
		engine.close();
		System.exit(0);
	}

}
