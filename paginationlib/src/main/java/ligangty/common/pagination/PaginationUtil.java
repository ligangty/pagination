package ligangty.common.pagination;

import javax.servlet.http.HttpServletRequest;

/**
 * This utility class is used to fetch the page bean from web context or init it.
 * 
 * @author gli@redhat.com
 * 
 */
public final class PaginationUtil {

    private PaginationUtil() {
        // hide the constructor of util class
    }

    /**
     * Fetches page bean from request, or initialize a new one if there is none.
     * 
     * @param request
     * @return
     */
    public static PageBean getPageBean(HttpServletRequest request) {
        PageBean pageBean = null;
        if (request.getAttribute(PageBean.PAGE_BEAN_ATTR) != null) {
            pageBean = (PageBean) request.getAttribute(PageBean.PAGE_BEAN_ATTR);
        } else {
            pageBean = new PageBean();
        }

        return pageBean;
    }

    /**
     * Initializes a page bean with a initial total records in the database search, and stores it in request.
     * 
     * @param request
     * @param totalRecords
     */
    public static void initPageBean(HttpServletRequest request, Long totalRecords) {
        PageBean pageBean = getPageBean(request);
        pageBean.setTotalRecords(totalRecords);
        pageBean.setCurrentPage(1);
        if (null != request) {
            request.setAttribute(PageBean.PAGE_BEAN_ATTR, pageBean);
        }
    }

}
