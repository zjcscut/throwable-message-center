package org.throwable.amqp.context.impl;


/**
 * @author zhangjinci
 * @version 2017/1/14 16:51
 * @function
 */
public class UserService {

    private String name;

    private UserDAO userDAO;

    public void test(){
        System.out.println("test方法被调用 --- " + userDAO.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
