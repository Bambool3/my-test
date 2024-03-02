//660510681 อนรรฆ ป้องกัน
package com.mycompany.test1;
import java.util.Scanner;
public class Test1 {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int i,M = sc.nextInt(), allSale=0;
	Food [] foodList = new Food[M];
	// รับรายการอาหาร M รายการ
        for(i = 0; i < M; i++) {
            foodList[i] = new Food(sc.next(), sc.nextShort());
        }
        int k, K = sc.nextInt();
	Order[] orderList = new Order[K];
	// รับ order จำนวน K รายการ
        for (k = 0; k < K; k++){    
            String orderId = sc.next();
            String tableNo = sc.next();
            int N = sc.nextInt();
            // create a corresponding Order instance รับ Order item  N รายการ สำหรับ แต่ละ order
            orderList[k] = new Order(orderId, tableNo, N);
            for(i = 0; i < N; i++) {
                Food food = new Food();
                String foodName = sc.next();
                byte numDish = (byte) sc.nextInt();
                int index = food.getFindIndex(foodList, foodName);
                orderList[k].setData(foodList[index], numDish);
            }
            // summary after summary this order
            orderList[k].showTotal();
        }
        // แสดงสรุปการขายแต่ละรายการอาหารให้ foodList
        for(Food food : foodList) {
            System.out.println(food.getFoodName() + " " + food.getotalSale());
            allSale += food.getotalSale();
        }
        System.out.println(allSale);
    }   
}   
class Order {
    // attributes
    private String orderId;
    private String tableNo;
    private int totalPrice = 0;
    private int N = 0;
    private OrderItem[] orderItems;
    // constructor
    public Order(String orderId, String tableNo, int n) {
        this.orderId = orderId;
        this.tableNo = tableNo;
        this.orderItems = new OrderItem[n];
    }
    // methods
    public void setData(Food food, byte numDish) {
        orderItems[N++] = new OrderItem(food, numDish);
    }
    public void sumPrice() {
        for (OrderItem orderitem : orderItems) {
            totalPrice += orderitem.getTotalPrice();
            orderitem.getFood().sumFoodPrice(orderitem.getTotalPrice());
        }
    }
    public void showTotal() {
        sumPrice();
        System.out.printf("%s %s %d\n", this.orderId, this.tableNo, this.totalPrice);
    }
}

class OrderItem {
    // attributes
    private Food food;
    private byte numDish;
    private int totalPrice;
    // constructor
    public OrderItem(Food food, byte numDish) {
        this.numDish = numDish;
        this.food = food;
    }
    // methods
    public int getTotalPrice() {
        totalPrice = food.getPrice()* numDish;
        return totalPrice;
    }
    public Food getFood() {
        return food;
    }
}
class Food {
    String foodName;
    short price;
    int totalSale = 0;
    // constructor
    public Food(){        
    }
    public Food(String foodName, short price) {
        this.foodName = foodName;
        this.price = price;
    }
    // methods
    public int getPrice() {
        return price;
    }
    public String getFoodName() {
        return foodName;
    }
    public void sumFoodPrice(int price) {
        totalSale += price;
    }
    public int getotalSale() {
        return totalSale;
    }
    public int getFindIndex(Food[] foodList, String foodName) {
        int i;
        for(i = 0; i < foodList.length; i++) {
            if (foodName.equals(foodList[i].getFoodName())) 
                return i;              
        }
        return -1;
    }
}
