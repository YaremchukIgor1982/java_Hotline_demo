public class Product {
    private Integer amount;
    private String name;
    private String link;

    Integer getAmount(){
        return this.amount;
    }

    void setAmount(Integer amount){
        this.amount = amount;
    }
    String getName(){
        return this.name;
    }

    void setName(String name){
        this.name = name;
    }
    String getLink(){
        return this.link;
    }

    void setLink(String link){
        this.link = link;
    }
    public static int compareProductsByPrice(int x, int y) {
        return Integer.compare(x, y);
    }
}




