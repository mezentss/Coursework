import java.util.List;

public interface SaleDAO {
    Sale getSaleById(int id);
    List<Sale> getAllSales();
    void addSale(Sale sale);
    void deleteSale(int id);
    void getSum(int id);
}