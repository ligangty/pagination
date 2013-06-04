/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ligangty.common.pagination.web.tag;

import ligangty.common.pagination.PageBean;

/**
 *
 * @author gli
 */
public abstract class AbstractPageGenStrategy implements PageGenStrategy {

    public String generatePaging(PageBean pageBean, int showPages, String actionUrl, String cssClass) {
        StringBuilder htmlBuf = new StringBuilder();

        if (cssClass != null && !cssClass.trim().equals("")) {
            htmlBuf.append("<div class=\"" + cssClass + "\">");
        } else {
            htmlBuf.append("<div>");
        }

        htmlBuf.append("<form action=\"" + actionUrl + "\">");

        htmlBuf.append(generateInnerPaging(pageBean, showPages));

        htmlBuf.append("</form>");

        htmlBuf.append("</div>");

        return htmlBuf.toString();
    }

    protected abstract String generateInnerPaging(PageBean pageBean, int showPages);

}
