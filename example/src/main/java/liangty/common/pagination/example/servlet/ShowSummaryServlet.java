package liangty.common.pagination.example.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ligangty.common.pagination.PageBean;
import ligangty.common.pagination.PaginationUtil;

/**
 * @author: ligangty Date: 6/3/13 Time: 2:16 PM
 */
public class ShowSummaryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        PageBean pageBean = PaginationUtil.getPageBean(request);
        List<ShowBean> showList = generateBeanList();

        if (pageBean.getTotalRecords() <= 0) {
            PaginationUtil.initPageBean(request, Integer.valueOf(showList.size()).longValue());
        }

        request.setAttribute("showList", findShowBeanList(pageBean,showList));
    }

    private List<ShowBean> generateBeanList() {
        List<ShowBean> list = new LinkedList<ShowBean>();
        for (int i = 0; i < 500; i++) {
            list.add(new ShowBean(i + 1, "user" + i + 1, "email" + i + 1 + "@test.com"));
        }
        return list;
    }

    private List<ShowBean> findShowBeanList(PageBean pageBean, List<ShowBean> allShowBeans){
        List<ShowBean> findedBeans = new LinkedList<ShowBean>();
        int curPage = pageBean.getCurrentPage();
        for(int i=0; i<pageBean.getPageSize();i++){
            int index = pageBean.getCurrentPage()*pageBean.getPageSize()+i;
            if(index<=pageBean.getTotalRecords()-1){
                findedBeans.add(allShowBeans.get(index));
            }else{
                break;
            }
        }
        return findedBeans;
    }
}
