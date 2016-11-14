package net.zhxm.frame.base.dao;

/**
 * 分页工具类
 * 项目名称：xyk   
 * 类名称：Page   
 * 类描述：   
 * 创建人：zhengxingmiao   
 * 创建时间：2014年8月25日 下午8:07:05   
 * 修改人：zhengxingmiao   
 * 修改时间：2014年8月25日 下午8:07:05   
 * 修改备注：   
 * @version
 */
public class Page {

	public static final int DEFAULT_PAGE_SIZE = 15;
	
	public static final int[] ARR_PAGE_SIZE = new int[] {10, 15, 20};
	
	/** 页码,从1开始 */
	private int pageIndex;
	
	/** 每页多少行 */
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	/** 数据总行数 */
	private int totalCount = 0;
	
	/** 总共可以分多少页 */
	private int pageCount;
	
	/** 排序方式 desc asc */
	private String sort = "";
	
	/** 排序字段 */
	private String order = "";
	
	private boolean hasPageSize = true;
	
	/** 第一页 */
	// private boolean firstPage=false;
	
	/** 上一页 */
	private boolean hasPrev = false;

	/** 下一页 */
	// private boolean hasNext=false;
	
	/** 最末页 */
	// private boolean lastPage=false;
	
	private int first;// 首页索引
	
	private int last;// 尾页索引
	
	private int prev;// 上一页索引
	
	private int next;// 下一页索引
	
	private static final int length = 7;// 显示页面长度
	
	private static final int slider = 1;// 前后显示页面长度
	
	private String funcName = "_gotPage"; // 设置点击页码调用的js函数名称，默认为page，在一页有多个分页对象时使用。

	/**
	 * 分页信息,默认每页20行数据
	 * 
	 * @param pageIndex
	 *            页码,从1开始
	 */
	public Page(int pageIndex) {
		this(pageIndex, DEFAULT_PAGE_SIZE);
	}

	public Page() {

	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * 分页信息
	 * 
	 * @param pageIndex
	 *            页码,从1开始
	 * @param pageSize
	 *            每页多少行,默认为 20
	 */
	public Page(int pageIndex, int pageSize) {
		if (pageIndex < 1)
			pageIndex = 1;
		if (pageSize < 1)
			pageSize = DEFAULT_PAGE_SIZE;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	/**
	 * 获取当前页页码
	 * 
	 * @return
	 */
	public int getPageIndex() {
		if (pageIndex < 1)
			pageIndex = 1;
		if (pageSize < 1)
			pageSize = DEFAULT_PAGE_SIZE;
		return pageIndex;
	}

	/**
	 * 获取每页多少行
	 * 
	 * @return
	 */
	public int getPageSize() {
		if (pageSize == 0)
			pageSize = DEFAULT_PAGE_SIZE;
		return pageSize;
	}

	/**
	 * 获取总共有多少页
	 * 
	 * @return
	 */
	public int getPageCount() {

		return pageCount;
	}

	/**
	 * 获取起始行数
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (pageIndex - 1) * pageSize;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		//System.out.println("setTotalCount totalCount="+totalCount);
		//System.out.println("setTotalCount pageSize="+pageSize);
		this.totalCount = totalCount;
		pageCount = totalCount / pageSize
				+ (totalCount % pageSize == 0 ? 0 : 1);
		//System.out.println("setTotalCount pageCount="+pageCount);
		
		/*
		 * // 调整页码信息,防止出界 if (totalCount == 0) { if (pageIndex != 1) pageIndex =
		 * 1; } else { if (pageIndex > pageCount) pageIndex = pageCount; }
		 */
	}

	/**
	 * 是否有数据
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return totalCount == 0;
	}

	/**
	 * 设置页面大小
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 排序方式 desc asc
	 * 
	 * @return String
	 */
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * 排序字段 例如 id
	 * 
	 * @return String
	 */
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * 是否是第一页
	 * 
	 * @return boolean
	 */
	public boolean getFirstPage() {
		if (pageCount > 0 && getPageIndex() != 1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 是否有上一页
	 * 
	 * @return boolean
	 */
	public boolean getHasPrev() {
		if (getPageIndex() > 1)
			return true;

		return hasPrev;
	}

	/**
	 * 是否是最后一页
	 * 
	 * @return boolean
	 */
	public boolean getLastPage() {
		if (pageIndex >= pageCount)
			return false;
		return true;
	}

	/**
	 * 是否有下一页
	 * 
	 * @return boolean
	 */
	public boolean getHasNext() {
		if (pageIndex > (pageCount - 1)) {
			return false;
		}
		return true;
	}

	
	/**
	 * 初始化参数
	 */
	public void initialize() {

		// 1
		this.first = 1;

		//System.out.println("initialize pageCount="+pageCount);
		//System.out.println("initialize pageSize="+pageSize);
		//System.out.println("initialize DEFAULT_PAGE_SIZE="+DEFAULT_PAGE_SIZE);
		//System.out.println("initialize first="+first);
		
		this.last = (int) (getTotalCount() / (this.pageSize < 1 ? DEFAULT_PAGE_SIZE : this.pageSize) + first - 1);

		//System.out.println("this.last="+last);
		
		if (this.getTotalCount() % this.pageSize != 0 || this.last == 0) {
			this.last++;
		}

		if (this.last < this.first) {
			this.last = this.first;
		}

		if (this.pageIndex <= 1) {
			this.pageIndex = this.first;
		}

		if (this.pageIndex >= this.last) {
			this.pageIndex = this.last;
		}

		if (this.pageIndex < this.last - 1) {
			this.next = this.pageIndex + 1;
		} else {
			this.next = this.last;
		}

		if (this.pageIndex > 1) {
			this.prev = this.pageIndex - 1;
		} else {
			this.prev = this.first;
		}

		// 2
		if (this.pageIndex < this.first) {// 如果当前页小于首页
			this.pageIndex = this.first;
		}

		if (this.pageIndex > this.last) {// 如果当前页大于尾页
			this.pageIndex = this.last;
		}

	}
	
	/**
	 * 默认输出当前分页标签 <div class="page">${page}</div>
	 */
	@Override
	public String toString() {

		initialize();

		StringBuilder sb = new StringBuilder();
		sb.append("");
		
//		<div class="pagination">
//			<ul>
//				<li><a href="#">首页</a></li>
//				<li><a href="#">上一页</a></li>
//				<li><a href="#">1</a></li>
//				<li><a href="#">2</a></li>
//				<li class="disabled"><a href="#">3</a></li>
//				<li class="active"><a href="#">4</a></li>
//				<li><a href="#">5</a></li>
//				<li><a href="#">下一页</a></li>
//				<li><a href="#">末页</a></li>
//			</ul>
//			<ul class="text">
//				<li>
//				共500条，
//				每页<select name="pageSize" onchange="page(1,this.value)">
//					<option value="10">10</option>
//					<option value="15">15</option>
//					<option value="20">20</option>
//				</select>条，
//				当前 5 /  100 页
//				<input id="page" name="page" type="hidden" value="5"/>
//				</li>
//			</ul>
//		</div>	

		sb.append("\n<div class=\"pagination\">");
		sb.append("\n<ul>");
		
		if ( !getFirstPage() ) {// 如果是首页
			sb.append("\n<li class=\"disabled\"><a href=\"javascript:;\">首页</a></li>");
			sb.append("\n<li class=\"disabled\"><a href=\"javascript:;\">上一页</a></li>");
			sb.append("");
			sb.append("");
			sb.append("");
		} else {
			sb.append("\n<li><a href=\"javascript:" + funcName + "(1," + pageSize + ");\">首页</a></li>");
			sb.append("\n<li><a href=\"javascript:" + funcName + "(" + prev + "," + pageSize + ");\">上一页</a></li>");
			sb.append("");
			sb.append("");
			sb.append("");
		}
		
		int begin = getPageIndex() - (length / 2);

		if (begin < first) {
			begin = first;
		}

		int end = begin + length - 1;

		if (end >= last) {
			end = last;
			begin = end - length + 1;
			if (begin < first) {
				begin = first;
			}
		}

		if (begin > first) {
			int i = 0;
			for (i = first; i < first + slider && i < begin; i++) {
				sb.append("\n<li><a href=\"javascript:" + funcName + "(" + i + "," + pageSize + ");\">" + (i + 1 - first) + "</a></li>");
				sb.append("");
				sb.append("");
			}
			if (i < begin) {
				sb.append("\n<li><a href=\"javascript:;\">...</a></li>");
			}
		}

		for (int i = begin; i <= end; i++) {
			if (i == getPageIndex()) {
				sb.append("\n<li class=\"active\"><a href=\"javascript:;\">" + (i + 1 - first) + "</a></li>");
				sb.append("");
				sb.append("");
				
			} else {
				sb.append("\n<li><a href=\"javascript:" + funcName + "(" + i + "," + pageSize + ");\">" + (i + 1 - first) + "</a></li>");
				sb.append("");
				sb.append("");
			}
		}

		if (last - end > slider) {
			sb.append("\n<li><a href=\"javascript:;\">...</a></li>");
			end = last - slider;
		}

		for (int i = end + 1; i <= last; i++) {
			sb.append("\n<li><a href=\"javascript:" + funcName + "(" + i + "," + pageSize + ");\">" + (i + 1 - first) + "</a></li>");
			sb.append("");
			sb.append("");
		}

		if (getPageIndex() == last) {
			sb.append("\n<li class=\"disabled\"><a href=\"javascript:;\">下一页</a></li>");
			sb.append("\n<li class=\"disabled\"><a href=\"javascript:;\">末页</a></li>");
			sb.append("");
			sb.append("");
			sb.append("");
		} else {
			sb.append("\n<li><a href=\"javascript:" + funcName + "(" + next + "," + pageSize + ");\">下一页</a></li>");
			sb.append("\n<li><a href=\"javascript:" + funcName + "(" + last + "," + pageSize + ");\">末页</a></li>");
			sb.append("");
			sb.append("");
			sb.append("");
		}
		sb.append("\n</ul>");
		
		// 信息展示
		sb.append("\n<ul class=\"text\">");
		sb.append("\n<li>");
		sb.append("\n共"+totalCount+"条");
		sb.append("，每页");
		
		if(isHasPageSize()){
			sb.append("\n<select name=\"page_item\" id=\"page_item\" onchange=\""+funcName+"(1,this.value)\">");
			for(int i=0;i<ARR_PAGE_SIZE.length;i++){
				String sel = "";
				if( ARR_PAGE_SIZE[i]==pageSize ){
					sel = "selected";
				}
				sb.append("\n<option value=\""+ARR_PAGE_SIZE[i]+"\" "+sel+">"+ARR_PAGE_SIZE[i]+"</option>");
			}
			sb.append("\n</select>");
		}else{
			sb.append(pageSize);
		}
		sb.append("\n条，当前 "+getPageIndex()+"/"+last+" 页");
		sb.append("");
		sb.append("\n</li>");
		sb.append("\n</ul>");

		sb.append("\n<input id=\"page\" name=\"page\" type=\"hidden\" value=\"" + getPageIndex() + "\"/>");
		sb.append("\n<input id=\"pageSize\" name=\"pageSize\" type=\"hidden\" value=\"" + pageSize + "\"/>");
		sb.append("\n<input id=\"orderProperty\" name=\"orderProperty\" type=\"hidden\" value=\""+getOrder()+"\"/>");
		sb.append("\n<input id=\"orderDirection\" name=\"orderDirection\" type=\"hidden\" value=\""+getSort()+"\"/>");
		sb.append("");
		sb.append("\n<script type='text/javascript'>");	
		sb.append("\nfunction "+funcName+"(n,s){");
		sb.append("\n$(\"#page\").val(n);");	
		sb.append("\n$(\"#pageSize\").val(s);");
		sb.append("\n$(\"form:last\").submit();");		
		sb.append("\n}");
		sb.append("\n</script>");
		
		sb.append("\n</div>");
		sb.append("");
		
		return sb.toString();
	}

	
	public boolean isHasPageSize() {
		return hasPageSize;
	}
	
	public void setHasPageSize(boolean hasPageSize) {
		this.hasPageSize = hasPageSize;
	}
	
	
	
}
