package it.bela.market.entity;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseRetrievedEntity extends BaseEntity {

	@ManyToOne
	private RetrieverExecution retrieverExecution;

	private String retrievedEntityStatus;
	
	public RetrieverExecution getRetrieverExecution() {
		return retrieverExecution;
	}

	public void setRetrieverExecution(RetrieverExecution retrieverExecution) {
		this.retrieverExecution = retrieverExecution;
	}

	public String getRetrievedEntityStatus() {
		return retrievedEntityStatus;
	}

	public void setRetrievedEntityStatus(String retrievedEntityStatus) {
		this.retrievedEntityStatus = retrievedEntityStatus;
	}
}
