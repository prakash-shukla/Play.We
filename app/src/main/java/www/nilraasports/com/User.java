package www.nilraasports.com;

public class User {

    private String id;
    private String userName;
    private String imageLink;
    private String age;
    private String status;
    private String sex;
    private String level;
    private String discription;
    private String latitude;
    private String longitude;
    private String game;

    public User() {
    }

    public User(String id, String userName, String imageLink, String age, String status, String sex, String level, String discription, String latitude, String longitude, String game) {
        this.id = id;
        this.userName = userName;
        this.imageLink = imageLink;
        this.age = age;
        this.status = status;
        this.sex = sex;
        this.level = level;
        this.discription = discription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.game = game;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
