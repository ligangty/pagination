package ligangty.common.pagination.web.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import ligangty.common.pagination.PageBean;
import ligangty.common.pagination.PaginationUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This servlet filter is used to get the page parameters from request and inject them to the page bean.
 * 
 * @author gli@redhat.com
 * 
 */
public class PageSetFilter implements Filter {

    private static final Log log = LogFactory.getLog(PageSetFilter.class);

    /**
     * just a stub
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        String showStyleRegisterFile = config.getServletContext().getInitParameter("showStyleRegisterFile");

    }

    /**
     * just a stub
     */
    @Override
    public void destroy() {
        // do nothing
    }

    /**
     * Filter method to do the inject stuff.
     * 
     * @param request
     * @param response
     * @param chain
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String curPage = httpRequest.getParameter(PageBean.PAGE_ATTR);
        String pageSize = httpRequest.getParameter(PageBean.PAGESIZE_ATTR);
        String totalRecords = httpRequest.getParameter(PageBean.TOTALRECS_ATTR);
        PageBean pageBean = PaginationUtil.getPageBean(httpRequest);

        if (curPage == null || curPage.trim().equals("")) {
            pageBean.setCurrentPage(1);
        } else {
            pageBean.setCurrentPage(Integer.parseInt(curPage));
        }
        if (pageSize == null || pageSize.trim().equals("")) {
            pageBean.setPageSize(PageBean.DEFAULT_PAGE_SIZE);
        } else {
            pageBean.setPageSize(Integer.parseInt(pageSize));
        }

        if (totalRecords != null && Long.parseLong(totalRecords) > 0) {
            pageBean.setTotalRecords(Long.parseLong(totalRecords));
        }

        for (Object param : httpRequest.getParameterMap().keySet()) {
            pageBean.addSearchingParams((String) param, httpRequest.getParameterMap().get(param));
        }

        httpRequest.setAttribute(PageBean.PAGE_BEAN_ATTR, pageBean);

        log.debug(pageBean);

        chain.doFilter(request, response);

    }

}
