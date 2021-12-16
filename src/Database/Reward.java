package Database;

public class Reward
{
    int id;
    String type;
    String name;

    public Reward(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Reward(int id)
    {
        this.id = id;
    }

    public Reward(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
