import java.util.List;

public class SaleController {
    private SaleDAOImpl _dao;

    public SaleController(SaleDAOImpl dao) {
        _dao = dao;
    }

    public Sale getSaleById(int id) {
        return _dao.getSaleById(id);
    }

    public List<Sale> getAllSales() {
        return _dao.getAllSales();
    }

    public void addService(Sale sale) {
        _dao.addSale(sale);
    }

    public void deleteSale(int id) {
        _dao.deleteSale(id);
    }
    public void getSum(int id) {_dao.getSum(id);}
}
