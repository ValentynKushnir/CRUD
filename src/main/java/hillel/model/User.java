package hillel.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {

    private int id;
    private String name;
    private Integer age;
    private Boolean isAdmin;
    private Date createdDate;

}
