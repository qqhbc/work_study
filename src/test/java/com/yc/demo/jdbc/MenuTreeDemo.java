package com.yc.demo.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yinchao
 * @date 2019/7/17
 */
class Menu {
    private Long menuId;
    private String menuName;
    private Long parentId;
    private List<Menu> childrenList;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Menu> childrenList) {
        this.childrenList = childrenList;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", parentId=" + parentId +
                ", childrenList=" + childrenList +
                '}';
    }
}

public class MenuTreeDemo {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://10.240.36.211:3306/bas?characterEncoding=UTF-8&useSSL=false";
    static final String USER = "root";
    static final String PASSWORD = "Transsnet_123";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("连接出错啦！");
        }
    }

    public List<Menu> getMenu(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            String sql = "SELECT " +
                    "menu_id,menu_name,parent_menu_id " +
                    "FROM " +
                    "bas_menu a " +
                    "WHERE" +
                    " a.bind_permission_id IN (" +
                    " SELECT DISTINCT " +
                    " b.permission_id " +
                    "FROM " +
                    "bas_permission b, " +
                    "bas_group_permission c, " +
                    "bas_group_user d " +
                    "WHERE " +
                    "b.permission_id = c.permission_id " +
                    "AND c.group_id = d.group_id " +
                    "AND d.user_id = 1 " +
                    ") " +
                    "OR ( " +
                    "a.bind_permission_id IN ( " +
                    "SELECT DISTINCT " +
                    "b.permission_id " +
                    "FROM " +
                    "bas_permission b, " +
                    "bas_role_permission c " +
                    "WHERE " +
                    "b.permission_id = c.permission_id " +
                    "AND c.role_id = 1 " +
                    ") " +
                    ") " +
                    "UNION " +
                    " SELECT menu_id,menu_name,parent_menu_id FROM bas_menu WHERE public_flag = 'Y' ";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            List<Menu> list = new ArrayList<>();
            while (resultSet.next()) {
                Menu menu = new Menu();
                menu.setMenuId(resultSet.getLong(1));
                menu.setMenuName(resultSet.getString(2));
                menu.setParentId(resultSet.getLong(3));
                list.add(menu);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException("sql 报错啦！");
        } finally {
            try {
                if(connection != null) connection.close();
                if(preparedStatement != null) preparedStatement.close();
                if(resultSet != null) resultSet.close();

            } catch (SQLException e) {
                throw new RuntimeException("释放连接报错啦！");
            }
        }
    }

    public List<Menu> getAll(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            String sql = "SELECT menu_id,menu_name,parent_menu_id FROM bas_menu";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            List<Menu> list = new ArrayList<>();
            while (resultSet.next()) {
                Menu menu = new Menu();
                menu.setMenuId(resultSet.getLong(1));
                menu.setMenuName(resultSet.getString(2));
                menu.setParentId(resultSet.getLong(3));
                list.add(menu);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException("sql 报错啦！");
        } finally {
            try {
                if(connection != null) connection.close();
                if(preparedStatement != null) preparedStatement.close();
                if(resultSet != null) resultSet.close();

            } catch (SQLException e) {
                throw new RuntimeException("释放连接报错啦！");
            }
        }
    }

    public static void main(String[] args) {
        MenuTreeDemo demo = new MenuTreeDemo();
        System.out.println(demo.getAll().size());
        System.out.println(demo.getMenu().size());
    }
}