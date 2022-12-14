package adminForm;

public class AddUserController {
    private String name;
    private String pwd;
    private static AddUserController[] defaultUser =new AddUserController[20];
    static {
    defaultUser[0]=new AddUserController("admin","password");
    defaultUser[1]=new AddUserController("Dilshan","1234");

    }
    public AddUserController(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public static AddUserController[] getDefaultUser() {
        return defaultUser;
    }

    public static void setDefaultUser(AddUserController[] defaultUser) {
        AddUserController.defaultUser = defaultUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
