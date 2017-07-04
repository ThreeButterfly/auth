package com.cdrundle.cdc.file.common.orm;

import java.io.Serializable;

public class BaseQuery  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5598780819299718694L;

	private int page;//第几页
	
	private Integer start;//当前页第一个数据索引
	
	private int limit;//每页个数
	
	private Boolean hasPaging = true;//是否分页
	
	private Boolean isUsed;
	
	private String query;
	
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	public Boolean getHasPaging() {
		if(start==null){
			return false;
		}else{
			return hasPaging;
		}
	}

	public void setHasPaging(Boolean hasPaging) {
		this.hasPaging = hasPaging;
	}

	public int getPage() {
		return page > 0 ? page : 1;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit > 0 ? limit : 20;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseQuery [page=").append(page).append(", start=").append(start).append(", limit=")
				.append(limit).append(", hasPaging=").append(hasPaging).append(", isUsed=").append(isUsed)
				.append(", query=").append(query).append("]");
		return builder.toString();
	}
	
}
