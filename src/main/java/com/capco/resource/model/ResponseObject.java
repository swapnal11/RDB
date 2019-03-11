
package com.capco.resource.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Component
@JsonInclude(Include.NON_NULL)
public class ResponseObject {

    private Status status;
    private Result result;
    
    private FilterResult filterResult;
    
    private List<FilterResult> filterlist;
    
    
    
	public List<FilterResult> getFilterlist() {
		return filterlist;
	}
	public void setFilterlist(List<FilterResult> filterlist) {
		this.filterlist = filterlist;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public FilterResult getFilterResult() {
		return filterResult;
	}
	public void setFilterResult(FilterResult filterResult) {
		this.filterResult = filterResult;
	}
	
	
}
