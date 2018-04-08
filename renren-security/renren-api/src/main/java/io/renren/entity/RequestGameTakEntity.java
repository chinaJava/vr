package io.renren.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/** 
 * 类名称: RequestGameTakEntity 用户任务完成时请求参数实体
 * 描述: TODO
 * @create 2017年11月10日 下午2:26:35 
 * @author llh  
 * @version 1.0.0 
 */
public class RequestGameTakEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long taskId;
	
	private List<MultipartFile> files;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

}
