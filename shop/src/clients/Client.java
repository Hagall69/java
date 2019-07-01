package clients;

public class Client {

    private int id;
    private String name;
    private int points;

    public Client(int id, String name, int bonuses){
        this.id = id;
        this.name = name;
        this.points = bonuses;
    }

    public Client(){}

    public void copyClient(Client client){
        this.id = client.id;
        this.name = client.name;
        this.points = client.points;
    }

    // Выводим информацию по сотруднику
    @Override
    public String toString() {
        return String.format("ID: %10s | Имя: %30s | Бонусы: %10s",
                this.id, this.name, this.points);
    }

//    int getID(){
//        return id;
//    }
//
//    String getName(){
//        return name;
//    }
//
//    int getBonuses(){
//        return bonuses;
//    }
//
    public void setName(String name){
        this.name = name;
    }

    public void setPoints(int bonuses){
        this.points = bonuses;
    }
}
