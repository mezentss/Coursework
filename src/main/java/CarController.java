import java.util.List;

public class CarController {
    private CarDAO _dao;

    public CarController(CarDAO dao) {
        _dao = dao;
    }

    public Car getCarById(int id) {
        return _dao.getCarById(id);
    }

    public List<Car> getAllCars() {
        return _dao.getAllCars();
    }

    public void addCar(Car car) {
        _dao.addCar(car);
    }

    public void updateCar(Car car) {
        _dao.updateCar(car);
    }

    public void deleteCar(int id) {
        _dao.deleteCar(id);
    }
}