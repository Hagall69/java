package products;

public class Product {

    private int id;
    private String barcode;
    private String name;
    private Double price;
    private int discount;

    public Product(int id, String barcode, String name, Double price, int discount){
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public void copyProduct(Product product){
        this.id = product.id;
        this.name = product.name;
        this.barcode = product.barcode;
        this.price = product.price;
        this.discount = product.discount;
    }

    public Product(){}

    // Выводим информацию по сотруднику
    @Override
    public String toString() {
        return String.format("ID: %10s | Штрихкод: %10s | Наименование: %10s | Цена: %10s | Скидка: %10s",
                this.id, this.barcode.substring(1), this.name, this.price, this.discount);
    }

//    int getID(){
//        return id;
//    }
//
//    String getBarcode() {
//        return barcode.substring(1);
//    }
//
//    String getName(){
//        return name;
//    }
//
//    Double getPrice() {
//        return price;
//    }
//
//    int getDiscount(){
//        return discount;
//    }
//
    public void setBarcode(String barcode){
        this.barcode = barcode;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public void setDiscount(int discount){
        this.discount = discount;
    }
}
