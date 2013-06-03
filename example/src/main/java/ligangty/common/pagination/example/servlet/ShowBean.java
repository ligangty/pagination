package ligangty.common.pagination.example.servlet;

/**
 * Created with IntelliJ IDEA. User: gli Date: 6/3/13 Time: 6:02 PM To change this template use File | Settings | File
 * Templates.
 */
public class ShowBean {
    private Integer id;
    private String userName;
    private String email;

    public ShowBean() {
        // do nothing
    }

    public ShowBean(Integer id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
