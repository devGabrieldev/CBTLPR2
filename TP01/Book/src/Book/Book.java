package Book;		//GABRIEL RIBEIRO CB3021726
public class Book {   
    private String name;
    private Author[] authors; 
    private double price;
    private int qty = 0;

    public Book(String name, Author[] authors, double price) {
        this.name = name;
        this.authors = authors;
        this.price = price;
    }

    public Book(String name, Author[] authors, double price, int qty) {
        this.name = name;
        this.authors = authors;
        this.price = price;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getAuthorNames() {
        String names = "";
        for (int i = 0; i < authors.length; i++) {
            names += authors[i].getName();
            if (i < authors.length - 1) {
                names += ", "; 
            }
        }
        return names;
    }

    @Override
    public String toString() {
        String authorsString = "{";
        for (int i = 0; i < authors.length; i++) {
            authorsString += authors[i].toString();
            if (i < authors.length - 1) {
                authorsString += ",";
            }
        }
        authorsString += "}";

        return "Book[name=" + name + ",authors=" + authorsString + ",price=" + price + ",qty=" + qty + "]";
    }
}
