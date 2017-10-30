package com.sfdo.ngp.po.graphql;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class GraphQLResponse<T> {

    public T data;
    public List errors;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List getErrors() {
		return errors;
	}
	public void setErrors(List errors) {
		this.errors = errors;
	}
	
	public GraphQLResponse() {
		
	}
	
	public GraphQLResponse(T data, List errors) {
		super();
		this.data = data;
		this.errors = errors;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((errors == null) ? 0 : errors.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GraphQLResponse other = (GraphQLResponse) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (errors == null) {
			if (other.errors != null)
				return false;
		} else if (!errors.equals(other.errors))
			return false;
		return true;
	}
    
    
}


