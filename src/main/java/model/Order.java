package model;

import java.sql.Date;

/**
 * Created by roman on 21.03.16.
 */
public class Order {
    private int id;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private int projectPrice;
    private int manager_id;
    private String comment;

    public Order(int id, String name, Date dateStart, Date dateEnd, int projectPrice, int manager_id, String comment) {
        this.id = id;
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.projectPrice = projectPrice;
        this.manager_id = manager_id;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(int projectPrice) {
        this.projectPrice = projectPrice;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }
}
