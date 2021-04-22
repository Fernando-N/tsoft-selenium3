package dev.fneira.business;

import dev.fneira.util.LoggerUtil;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class UserData {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    public UserData(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> actual: data){
            switch (actual.get("key")) {
                case "firstName":
                    this.firstName = actual.get("value");
                    break;
                case "lastName":
                    this.lastName = actual.get("value");
                    break;
                case "userName":
                    this.userName = actual.get("value");
                    break;
                case "password":
                    this.password = actual.get("value");
                    break;
                default:
                    LoggerUtil.error(false,"Llave {%s} no aceptada en objeto UserData.");
                    break;
            }
        }

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
