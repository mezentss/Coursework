
import java.util.List;

public interface CarDAO {
    Car getCarById(int id);
    List<Car> getAllCars();
    void addCar(Car car);
    void updateCar(Car car);
    void deleteCar(int id);
}