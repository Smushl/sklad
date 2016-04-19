package model;

import dao.DbService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 20.03.16.
 */
public class Model {
    private DbService dbService;
    private ArrayList<FurnitureDataSet> furnitureList;
    private ArrayList<FurnitureGroupDataSet> furnitureGroupDataSets;
    private ArrayList<Order> orders;
    private List<Manager> managers;
    final static Logger logger = Logger.getLogger(Model.class);

    public Model(DbService dbService) {
        this.dbService = dbService;
        furnitureList = dbService.getFurnitureList();
        furnitureGroupDataSets = dbService.getFurnitureGroups();
        orders = dbService.getOrders(0);
        managers = dbService.getManagers();
        logger.info("Model initialized");
    }

    public ArrayList<FurnitureDataSet> getFurnitureList(){
        return furnitureList;
    }

    public ArrayList<FurnitureGroupDataSet> getFurnitureGroups() {
        return furnitureGroupDataSets;
    }

    public void addNewFurniture(String name, String group_id){
        if (dbService.addNewFurniture(name, group_id) > 0) { //или сделать возвращаемое значение типа int, чтоб сервлет мог перенаправить пользователя в случае ошибки добавления
            furnitureList = dbService.getFurnitureList();  //если добавление прошло успешно, по-новой спрашиваем список фурнитуры
            logger.info("new furniture added successfully");
        }


/*
        if (id != 0){
            furnitureList.add(new FurnitureDataSet(id, name, 0, Integer.parseInt(group_id))); //тут еще нужно будет Id у DBService спросить!
            logger.info("new furniture added, id = " + id);
        }
*/
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public int addNewManager(String name){
        //проверка существования имени в самой модельке
        for (Manager manager : managers){
            if (manager.getName().equals(name)) {
                logger.error("Manager already exists");
                return -1;
            }
        }
        int result = dbService.addNewManager(name);
        if (result > 0){
            managers = dbService.getManagers();
            logger.info("New manager added: " + name);
        }
        return result;
    }

    public int removeFurniture(int id){
        int removed = -1;
        if((removed = dbService.removeFurniture(id)) > 0) {
//            furnitureList = dbService.getFurnitureList();   //переделать: не обращаться к DBService, а просто удалить из ArrayList
            FurnitureDataSet fds = null;
            for (FurnitureDataSet furniture: furnitureList){
                if (furniture.getId() == id)
                    fds = furniture;
            }
            furnitureList.remove(fds);
            logger.info("furniture removed!");
        }
        return removed;
    }

    public int renameFurniture(int id, String newName){
        int itemsRenamed = -1;
        if((itemsRenamed = dbService.renameFurniture(id, newName)) > 0){
            for (FurnitureDataSet furniture: furnitureList){
                if (furniture.getId() == id)
                    furniture.setName(newName);
            }
            logger.info("furniture successfully renamed!");
        }
        return itemsRenamed;
    }

    public int moveFurnitureToGroup(int id, int groupId){
        int itemsMoved = -1;
        if ((itemsMoved = dbService.moveFurnitureToGroup(id, groupId)) > 0){
            for (FurnitureDataSet furniture: furnitureList){
                if (furniture.getId() == id)
                    furniture.setGroup(groupId);
            }
            logger.info("furniture successfully moved to another group!");
        }
        return itemsMoved;
    }

    public FurnitureDataSet getFurnitureById(int id){

        for (FurnitureDataSet fds : furnitureList){
            if (fds.getId() == id){
                return fds;
            }
        }
        return null;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void addOrder(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public int writeOff(FurnitureDataSet furnitureItem, int amount, int orderId){
        int result = dbService.writeOff(furnitureItem.getId(), amount, furnitureItem.getInventory()-amount, orderId);
        if (result > 0)
            furnitureList = dbService.getFurnitureList();
        return result;
    }
}

