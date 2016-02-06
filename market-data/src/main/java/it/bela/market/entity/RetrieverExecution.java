package it.bela.market.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import it.bela.market.utils.RetrieverExecutorStatus;
import it.bela.market.utils.RetrieverExecutorType;

@Entity
@Table(name="retriever_executions")
public class RetrieverExecution extends BaseEntity {

	private Date startExecution;
	private Date endExecution;
	private String type;
	private String status;
	private String errorMessage;
	
	public RetrieverExecution() {
		super();
	}

	public RetrieverExecution(Date startExecution, RetrieverExecutorType retrieverExecutorType, RetrieverExecutorStatus retrieverExecutorStatus) {
		super();
		this.startExecution = startExecution;
		this.type = retrieverExecutorType.toString();
		this.status = retrieverExecutorStatus.toString();
	}

	public Date getStartExecution() {
		return startExecution;
	}
	public void setStartExecution(Date startExecution) {
		this.startExecution = startExecution;
	}
	public Date getEndExecution() {
		return endExecution;
	}
	public void setEndExecution(Date endExecution) {
		this.endExecution = endExecution;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
