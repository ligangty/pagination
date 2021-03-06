package ligangty.common.pagination.web.tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;

import ligangty.common.pagination.PageBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Tag class to generate the pagination html contents.
 * 
 * @author gli@redhat.com
 * 
 */
public class PaginationTag extends SimpleTagSupport {
    private static final Log log = LogFactory.getLog(PaginationTag.class);

    private PageGenStrategy pageGen;

    private String action = "";

    private Integer showpages = 5;

    private Integer pagesize = PageBean.DEFAULT_PAGE_SIZE;

    private PageBean pagebean;

    private String divCssClass;

    public PaginationTag() {
        this.pageGen = new DownListStylePageGenStrategy();
    }

    /**
     * actual tag handler method
     */
    @Override
    public void doTag() throws javax.servlet.jsp.JspException, java.io.IOException {

        if (getPagebean() == null) {
            setPagebean((PageBean) getJspContext().findAttribute(PageBean.PAGE_BEAN_ATTR));
        }

        if (getPagebean() == null) {
            log.error("pageBean cannot be found in page context", new Exception("pageBean cannot be found in page context"));
            return;
        }

        getPagebean().setPageSize(getPagesize());

        getJspContext().getOut().write(generatePaging(getPagebean()));

    }

    /**
     * Generates page numbers based on page generation strategy
     * 
     * @param pageBean
     * @return
     */
    protected String generatePaging(PageBean pageBean) {
        if (pageBean.getTotalPage() < getShowpages()) {
            setShowpages(pageBean.getTotalPage());
        }

        return pageGen.generatePaging(pageBean, getShowpages(), getAction(), getDivCssClass());
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getShowpages() {
        return showpages;
    }

    public void setShowpages(Integer showPages) {
        this.showpages = showPages;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public PageBean getPagebean() {
        return pagebean;
    }

    public void setPagebean(PageBean pageBean) {
        this.pagebean = pageBean;
    }

    public String getDivCssClass() {
        return divCssClass;
    }

    public void setDivCssClass(String divCssClass) {
        this.divCssClass = divCssClass;
    }

}
