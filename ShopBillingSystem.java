import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

class Product {

    String name;
    double price;
    int quantity;
    double total;
    Product next;

    Product(String name,double price,int quantity){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.total=price*quantity;
        this.next=null;
    }
}

public class ShopBillingSystem {

    static Product head=null;
    static Stack<Double> billHistory = new Stack<>();
    static Queue<String> customerQueue = new LinkedList<>();

    static void addProduct(String name,double price,int qty){

        Product newProduct = new Product(name,price,qty);

        if(head==null){
            head=newProduct;
        }
        else{

            Product temp=head;

            while(temp.next!=null){
                temp=temp.next;
            }

            temp.next=newProduct;
        }
    }

    static void displayBill(){

        Product temp=head;
        double grandTotal=0;

        System.out.println("\n--- BILL DETAILS ---");

        while(temp!=null){

            System.out.println(temp.name+"  "+temp.price+"  "+temp.quantity+"  "+temp.total);

            grandTotal += temp.total;

            temp=temp.next;
        }

        System.out.println("Grand Total = "+grandTotal);

        billHistory.push(grandTotal);
    }

    static void searchProduct(String key){

        Product temp=head;

        while(temp!=null){

            if(temp.name.equals(key)){
                System.out.println("Product Found : "+temp.name+" Price: "+temp.price);
                return;
            }

            temp=temp.next;
        }

        System.out.println("Product Not Found");
    }

    static void sortProducts(){

        for(Product i=head;i!=null;i=i.next){

            for(Product j=i.next;j!=null;j=j.next){

                if(i.price > j.price){

                    String tname=i.name;
                    double tprice=i.price;
                    int tqty=i.quantity;
                    double ttotal=i.total;

                    i.name=j.name;
                    i.price=j.price;
                    i.quantity=j.quantity;
                    i.total=j.total;

                    j.name=tname;
                    j.price=tprice;
                    j.quantity=tqty;
                    j.total=ttotal;

                }

            }

        }

        System.out.println("Products Sorted by Price");
    }

    public static void main(String[] args){

        Scanner sc=new Scanner(System.in);
        int choice;

        do{

            System.out.println("\n1 Add Product");
            System.out.println("2 Display Bill");
            System.out.println("3 Search Product");
            System.out.println("4 Sort Products");
            System.out.println("5 Add Customer to Queue");
            System.out.println("6 Serve Customer");
            System.out.println("7 Exit");

            choice=sc.nextInt();

            switch(choice){

                case 1:

                    System.out.print("Enter Name: ");
                    String name=sc.next();

                    System.out.print("Enter Price: ");
                    double price=sc.nextDouble();

                    System.out.print("Enter Quantity: ");
                    int qty=sc.nextInt();

                    addProduct(name,price,qty);
                    break;

                case 2:
                    displayBill();
                    break;

                case 3:

                    System.out.print("Enter product to search: ");
                    String key=sc.next();
                    searchProduct(key);
                    break;

                case 4:
                    sortProducts();
                    break;

                case 5:

                    System.out.print("Enter customer name: ");
                    String cname=sc.next();
                    customerQueue.add(cname);
                    break;

                case 6:

                    if(!customerQueue.isEmpty())
                        System.out.println("Serving "+customerQueue.remove());
                    else
                        System.out.println("No customers");

                    break;

            }

        }while(choice!=7);

    }
}