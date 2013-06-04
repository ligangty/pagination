package ligangty.common.pagination.web.tag;

import ligangty.common.pagination.PageBean;

/**
 * Strategy pattern interface to generate page content block
 * 
 * @author gli@redhat.com
 * 
 */
public interface PageGenStrategy {

    /**
     * Strategy method
     * 
     * @param pageBean
     * @param showPages
     * @param actionUrl 
     * @param cssClass 
     * @return
     */
    String generatePaging(PageBean pageBean, int showPages, String actionUrl, String cssClass);

}
