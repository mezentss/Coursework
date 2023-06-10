package Sale;

import Sale.Sale;

import java.util.List;
import java.util.Scanner;

public class SaleView {
    private SaleController _controller;

    public SaleView(SaleController controller) {
        _controller = controller;
    }

    public void displaySale(Sale sale) {
        System.out.println("ID: " + sale.getID());
        System.out.println("ID услуги: " + sale.getServiceID());
        System.out.println("ID детали:" + sale.getPartID());

    }

    public void displayAllSales(List<Sale> sales) {
        for (Sale sale : sales) {
            displaySale(sale);
        }
    }

    public void addSale() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Введите ID: ");
        int ID  = scanner1.nextInt();
        System.out.print("Введите ID услуги: ");
        int ServiceID  = scanner1.nextInt();
        System.out.print("Введите ID детали: ");
        int PartID  = scanner1.nextInt();
        Sale sale = new Sale(ID, ServiceID, PartID);
        _controller.addService(sale);
    }

    public void  getSum(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID : ");
        int id = scanner.nextInt();
        _controller.getSum(id);
    }


    public void deleteSale() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID продажи, которого требуется удалить: ");
        int id = scanner.nextInt();
        _controller.deleteSale(id);
    }
}