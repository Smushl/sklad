package dao;

import model.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Created by roman on 19.03.16.
 */
public class DbService {
    final static Logger logger = Logger.getLogger(DbService.class);
    private Connection con;

    public DbService(Connection con) {
        this.con = con;
    }

    public ArrayList<FurnitureDataSet> getFurnitureList(){

        String queryString = "SELECT * FROM sklad.furniture ORDER BY `name`;";
        ArrayList<FurnitureDataSet> furnitureList = null;
        try {
            furnitureList =  Executor.execQuery(con, queryString, resultSet -> {

                ArrayList<FurnitureDataSet> furnitureList1 = new ArrayList<>();

                while (resultSet.next())
                    furnitureList1.add(new FurnitureDataSet(resultSet.getInt("id"), resultSet.getString("name"),
                            resultSet.getInt("inventory"), resultSet.getInt("group_id")));
                return furnitureList1;
            });
        } catch (SQLException e) {
            logger.error("SQL Exception", e);
        }
        return furnitureList;

    }

    public int addNewFurniture(String name, String group_id){

        String insertTableSQL = String.format("INSERT INTO sklad.furniture (name, group_id) VALUES ('%s', %d);", name, Integer.parseInt(group_id));
        logger.info(insertTableSQL);
        int updated = -1; //значение fail
        try {
            updated =  Executor.execUpdate(this.con, insertTableSQL);
        } catch (SQLException e) {
            logger.error("Creating new furniture failed: ", e);
        }

        return updated;

    }

    public ArrayList<FurnitureGroupDataSet> getFurnitureGroups(){
        String queryString = "SELECT * FROM sklad.furniture_groups ORDER BY `name`;";
        ArrayList<FurnitureGroupDataSet> furnitureGroupDataSets = null;

        try {
            furnitureGroupDataSets =  Executor.execQuery(con, queryString, resultSet -> {

                ArrayList<FurnitureGroupDataSet> furnitureGroupDataSets1 = new ArrayList<>();

                while (resultSet.next())
                    furnitureGroupDataSets1.add(new FurnitureGroupDataSet(resultSet.getInt("id"), resultSet.getString("name")));
                return furnitureGroupDataSets1;
            });
        } catch (SQLException e) {
            logger.error("SQL Exception", e);
        }

        return furnitureGroupDataSets;
    }

    public int addNewFurnitureGroup(String name){

        String insertTableSQL = String.format("INSERT INTO sklad.furniture_groups (name) VALUES ('%s');", name);
        logger.info(insertTableSQL);
        int updated = -1; //значение fail
        try {
            updated =  Executor.execUpdate(this.con, insertTableSQL);
        } catch (SQLException e) {
            logger.error("Creating new furniture failed: ", e);
        }

        return updated;

    }

    public ArrayList<Order> getOrders(int i){   //
        ArrayList<Order> orders = null;
        String queryString = (i ==0) ? "SELECT * FROM sklad.orders;" : "SELECT * FROM sklad.orders ORDER BY `id` DESC limit " + i + ";";

        try{
            orders = Executor.execQuery(con, queryString, resultSet -> {
                ArrayList<Order> orders_ = new ArrayList<>();
                while (resultSet.next())
                    orders_.add(new Order(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getDate("date_start"),
                            resultSet.getDate("date_end"),
                            resultSet.getInt("project_price"),
                            resultSet.getInt("manager_id"),
                            resultSet.getString("comment")));
                return orders_;
            });
        } catch (SQLException e) {
            logger.error("SQL Exception", e);
        }
        return orders;
    }

    public int addOrder(String name, Date dateStart, Date dateEnd, int projectPrice, int managerId, String comment){
        String insertTableSQL = "INSERT INTO sklad.orders (name, date_start, date_end, project_price, comment) VALUES ('" + name + "', '" +
                dateStart;

        return 1;
    }

    public List<Manager> getManagers(){
        List<Manager> managers = null;
        String queryString = "SELECT * FROM sklad.managers ORDER BY name;";

        try{
            managers = Executor.execQuery(con, queryString, resultSet -> {
                List<Manager> managers_ = new ArrayList<>();
                while (resultSet.next()) {
                    Manager manager = new Manager();
                    manager.setId(resultSet.getInt("id"));
                    manager.setName(resultSet.getString("name"));
                    manager.setActive(resultSet.getBoolean("active"));
                    managers_.add(manager);
                }
                return managers_;
            });
            logger.info("Managers from DB received");
        }
        catch (SQLException e){
            logger.error("SQL Exception ", e);
        }

        return managers;
    }

    public int removeFurniture(int id){
        String query = String.format("DELETE FROM sklad.furniture WHERE id=%d;", id);
        logger.info(query);
        int updated = -1; //значение fail
        try {
            updated =  Executor.execUpdate(this.con, query);
            logger.info(updated + " row(s) affected");
        } catch (SQLException e) {
            logger.error("Removing furniture failed: ", e);
        }

        return updated;

    }

    public int renameFurniture(int id, String newName){
        String query = String.format("UPDATE sklad.furniture SET name='%s' WHERE id=%d;", newName, id);
        logger.info(query);
        int updated = -1; //значение fail
        try {
            updated =  Executor.execUpdate(this.con, query);
            logger.info(updated + " row(s) affected");
        } catch (SQLException e) {
            logger.error("Renaming furniture failed: ", e);
        }
        return updated;
    }

    public int moveFurnitureToGroup(int id, int groupId){
        String query = String.format("UPDATE sklad.furniture SET group_id=%d WHERE id=%d;", groupId, id);
        logger.info(query);
        int updated = -1; //значение fail
        try {
            updated =  Executor.execUpdate(this.con, query);
            logger.info(updated + " row(s) affected");
        } catch (SQLException e) {
            logger.error("Renaming furniture failed: ", e);
        }
        return updated;
    }

    public int addNewManager(String name){
        String insertTableSQL = String.format("INSERT INTO sklad.managers (name) VALUES ('%s');", name);
        logger.info(insertTableSQL);
        int updated = -1; //значение fail
        try {
            updated =  Executor.execUpdate(this.con, insertTableSQL);
        } catch (SQLException e) {
            logger.error("Adding new manager failed: ", e);
        }

        return updated;
    }

    public int writeOff(int furnitureId, int amount, int newInventory, int orderId){
        //возможно тут всё корявенько, может потом переделаю
        //нужно получить результат транзакции
        int updated = -1; //значение fail
        try {
            con.setAutoCommit(false);
            String update1 = "UPDATE sklad.furniture SET inventory='" + newInventory + "' WHERE id=" + furnitureId + ";";
            logger.info(update1);

            try {
                updated =  Executor.execUpdate(this.con, update1);
                logger.info(updated + " row(s) affected");
            } catch (SQLException e) {
                logger.error("Write-Off furniture failed: ", e);
            }
            java.util.Date dateNow = new java.util.Date();
            Date date = new Date(dateNow.getTime());
            String update2 = String.format("INSERT INTO sklad.minus VALUES (%d, %d, %d, '%s');", orderId, furnitureId, amount, date.toString());
            logger.info(update2);
            updated = -1; //значение fail
            try {
                updated =  Executor.execUpdate(this.con, update2);
                logger.info(updated + " row(s) affected");
            } catch (SQLException e) {
                logger.error("Write-Off furniture failed: ", e);
            }
            con.commit();
            con.setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return updated;
    }

    public List<Seller> getSellers(){
        List<Seller> sellers = null;
        String queryString = "SELECT * FROM sklad.sellers ORDER BY name;";
        try{
            sellers = Executor.execQuery(con, queryString, resultSet -> {
                List<Seller> sellers_ = new ArrayList<>();
                while (resultSet.next()) {
                    sellers_.add(new Seller(resultSet.getInt("id"), resultSet.getString("name")));
                }
                return sellers_;
            });
            logger.info("Sellers from DB received");
        }
        catch (SQLException e){
            logger.error("SQL Exception ", e);
        }

        return sellers;
    }
}












/*
public ArrayList<FurnitureDataSet> getFurnitureList(){
        ArrayList<FurnitureDataSet> furnitureList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String queryString = "SELECT * FROM sklad.furniture ORDER BY `name`;";


        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery(queryString);

            while (resultSet.next()) {
                furnitureList.add(new FurnitureDataSet(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("inventory"), resultSet.getInt("group_id")));
            }
        } catch (SQLException e) {
            logger.error("SQL Exception", e);
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQL Exception", e);
            }
        }

        return furnitureList;
 */