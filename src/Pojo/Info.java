package Pojo;

public class Info {
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private int ID;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRight_eye() {
        return Right_eye;
    }

    public void setRight_eye(String right_eye) {
        Right_eye = right_eye;
    }

    public String getLeft_eye() {
        return Left_eye;
    }

    public void setLeft_eye(String left_eye) {
        Left_eye = left_eye;
    }

    public Integer getDistance() {
        return Distance;
    }

    public void setDistance(Integer distance) {
        Distance = distance;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getLens() {
        return Lens;
    }

    public void setLens(String lens) {
        Lens = lens;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getXwjwgd() {
        return Xwjwgd;
    }

    public void setXwjwgd(String xwjwgd) {
        Xwjwgd = xwjwgd;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }
    private String Store;
    private String Name;
    private String Right_eye;
    private String Left_eye;
    private Integer Distance;
    private String Size;

    public String getStore() {
        return Store;
    }

    public void setStore(String store) {
        Store = store;
    }
    private String Lens;

    @Override
    public String toString() {
        return "InfoDao{" +
                "ID=" + ID +
                ", Store='" + Store + '\'' +
                ", Name='" + Name + '\'' +
                ", Right_eye='" + Right_eye + '\'' +
                ", Left_eye='" + Left_eye + '\'' +
                ", Distance=" + Distance +
                ", Size='" + Size + '\'' +
                ", Lens='" + Lens + '\'' +
                ", price='" + price + '\'' +
                ", phone='" + phone + '\'' +
                ", Xwjwgd='" + Xwjwgd + '\'' +
                ", Pass='" + Pass + '\'' +
                ", DATA='" + DATA + '\'' +
                ", person='" + person + '\'' +
                ", Height='" + Height + '\'' +
                '}';
    }

    private String price;
    private String phone;
    private String Xwjwgd;
    private String Pass;
    private String DATA;
    private String person;
    private String Height;

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
