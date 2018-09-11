package com.webpos.tools;

import java.util.List;

/**
 * 列表分页。包含list属性。
 */
public class Pagination extends SimplePage{

	/** serialVersionUID*/
	private static final long serialVersionUID = 53781381016018587L;

	public Pagination() {
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 */
	public Pagination(int pageNo, int pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
		
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 * @param list
	 *            分页内容
	 */
	public Pagination(int pageNo, int pageSize, int totalCount, List<?> list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}

	/**
	 * 第一条数据位置
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

	/**
	 * 当前页的数据
	 */
	private List<?> list;
	
	/**
	 * 当前页的分页样式
	 */
	private List<String> pageView;

	/**
	 * 获得分页内容
	 * 
	 * @return
	 */
	public List<?> getList() {
		return list;
	}

	/**
	 * 设置分页内容
	 * 
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	public void setList(List list) {
		this.list = list;
	}
	/**
	 * 获得分页样式
	 * 
	 * @return
	 */
	public List<String> getPageView() {
		return pageView;
	}
	/**
	 * 设置分页样式
	 * 
	 * @param list
	 */
	public void setPageView(List<String> pageView) {
		this.pageView = pageView;
	}

}
