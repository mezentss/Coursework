import java.util.List;
import java.util.Scanner;

public class CarView {
    private CarController _controller;

    public CarView(CarController controller) {
        _controller = controller;
    }

    public void displayCar(Car car) {
        System.out.println("ID: " + car.getId());
        System.out.println("Номерной знак: " + car.getLicensePlate());
        System.out.println("Производитель: " + car.getBrand());
        System.out.println("Модель: " + car.getModel());
        System.out.println("Цвет: " + car.getColor());
        System.out.println("ID владельца: " + car.getOwnerID());
    }

    public void displayAllCars(List<Car> cars) {
        for (Car car : cars) {
            displayCar(car);
        }
    }

    public void addCar() {
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Введите ID автомобиля: ");
        int id = scanner1.nextInt();
        System.out.print("Введите номерной знак автомобиля: ");
        String licensePlate = scanner2.nextLine();
        System.out.print("Введите производителя автомобиля: ");
        String brand = scanner2.nextLine();
        System.out.print("Введите модель автомобиля: ");
        String model = scanner2.nextLine();
        System.out.print("Введите цвет автомобиля: ");
        String color = scanner2.nextLine();
        System.out.print("Введите ID владельца автомобиля: ");
        int ownerID = scanner1.nextInt();
        Car car = new Car(id,licensePlate, brand, model, color, ownerID);
        _controller.addCar(car);
    }

    public void updateCar() {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Введите ID автомобиля, данные о котором следует обновить: ");
        int id = scanner.nextInt();
        Car car = _controller.getCarById(id);

        if (car != null) {
            System.out.print("Введите номерной знак автомобиля: ");
            String licensePlate = scanner2.nextLine();
            System.out.print("Введите марку автомобиля: ");
            String brand = scanner2.nextLine();
            System.out.print("Введите модель автомобиля: ");
            String model = scanner2.nextLine();
            System.out.print("Введите цвет автомобиля: ");
            String color = scanner2.nextLine();

            car.setLicensePlate(licensePlate);
            car.setBrand(brand);
            car.setModel(model);
            car.setColor(color);

            _controller.updateCar(car);
        } else {
            System.out.println("Автомобиль с ID " + id + " не найден.");
        }
    }

    public void deleteCar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID автомобиля, который требуется удалить: ");
        int id = scanner.nextInt();
        _controller.deleteCar(id);
    }
}