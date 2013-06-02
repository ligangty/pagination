package ligangty.common.pagination;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Page java bean to hold the page relative meta data
 * 
 * @author gli@redhat.com
 * 
 */
public class PageBean implements Serializable {

	private static final long serialVersionUID = 5496067984636564294L;

	/** Default page size, set to 50 */
	public static final int DEFAULT_PAGE_SIZE = 50;

	/** Attribute to hold page index in web context */
	public static final String PAGE_ATTR = "page";

	/** Attribute to hold page size in web context */
	public static final String PAGESIZE_ATTR = "pagesize";

	/** Attribute to hold total records in web context */
	public static final String TOTALRECS_ATTR = "totalrecords";

	/** Attribute to hold page object in web context */
	public static final String PAGE_BEAN_ATTR = "com.redhat.tools.pagination.page.Bean";

	/** Current page index */
	private int currentPage = 1;

	/** Total page */
	private int totalPage;

	/** Page size */
	private int pageSize;

	/** Total records */
	private long totalRecords = 0;

	/**
	 * Map holds the web context params which may be used to be the searching
	 * params for db
	 */
	private Map<String, Object> searchingParamsMap = new ConcurrentHashMap<String, Object>();

	/**
	 * Default constructor, set page size to default page size(50)
	 */
	public PageBean() {
		setPageSize(DEFAULT_PAGE_SIZE);
	}

	/**
	 * Constructor with total records count, set page size to default page size(50)
	 * 
	 * @param totalRecords
	 */
	public PageBean(long totalRecords) {
		setPageSize(DEFAULT_PAGE_SIZE);
		setTotalPage((int) (totalRecords % pageSize == 0 ? totalRecords
				/ pageSize : totalRecords / pageSize + 1));
	}

	/**
	 * Constructor with total records count and page size
	 * 
	 * @param totalRecords
	 * @param pageSize
	 */
	public PageBean(long totalRecords, int pageSize) {
		setPageSize(pageSize);
		setTotalPage((int) (totalRecords % pageSize == 0 ? totalRecords
				/ pageSize : totalRecords / pageSize + 1));
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
		setTotalPage((int) (totalRecords % getPageSize() == 0 ? totalRecords
				/ getPageSize() : totalRecords / getPageSize() + 1));
	}

	public int getNextPage() {
		if (getCurrentPage() >= getTotalPage() - 1) {
			return getTotalPage();
		} else {
			return getCurrentPage() + 1;
		}
	}

	public int getPreviousPage() {
		if (getCurrentPage() <= 1) {
			return 1;
		} else {
			return getCurrentPage() - 1;
		}
	}

	public int getStartRecord() {
		return (getCurrentPage() - 1) * getPageSize();
	}

	@Override
	public String toString() {
		return "currentPage:" + getCurrentPage() + " pageSize:" + getPageSize();
	}

	public Map<String, Object> getSearchingParamsMap() {
		return searchingParamsMap;
	}

	public void addSearchingParams(String param, Object value) {
		if (!PAGE_ATTR.equals(param) && !"init".equals(param)
				&& !TOTALRECS_ATTR.equals(param)) {
			getSearchingParamsMap().put(param, value);
		}
	}

	public void clearSearchingParams() {
		getSearchingParamsMap().clear();
	}

}
