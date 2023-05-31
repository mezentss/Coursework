
import java.util.List;
import java.util.Scanner;

public class PartView {
    private PartController _controller;

    public PartView(PartController controller) {
        _controller = controller;
    }

    public void displayPart(Part part) {
        System.out.println("ID: " + part.getId());
        System.out.println("Категория: " + part.getCategory());
        System.out.println("Модель: " + part.getModel());
        System.out.println("Серийный номер: " + part.getSerialNumber());
        System.out.println("Модель: " + part.getPrice());
    }

    public void displayAllParts(List<Part> parts) {
        for (Part part : parts) {
            displayPart(part);
        }
    }

    public void addPart() {
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Введите ID детали: ");
        int id = scanner1.nextInt();
        System.out.print("Введите категорию детали: ");
        String category = scanner2.nextLine();
        System.out.print("Введите модель детали: ");
        String model = scanner2.nextLine();
        System.out.print("Введите серийный номер детали: ");
        String serialNumber = scanner2.nextLine();
        System.out.print("Введите цену детали: ");
        int price = scanner1.nextInt();
        Part part = new Part(id, category, model, serialNumber, price);
        _controller.addPart(part);
    }

    public void updatePart() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID детали, данные о которой следует обновить: ");
        int id = scanner.nextInt();
        Part part = _controller.getPartById(id);

        if (part != null) {
            System.out.print("Введите категорию детали: ");
            String category = scanner.nextLine();
            System.out.print("Введите модель детали: ");
            String model = scanner.nextLine();
            System.out.print("Введите серийный номер детали: ");
            String serialNumber = scanner.nextLine();
            System.out.print("Введите цену детали: ");
            int price = scanner.nextInt();

            part.setCategory(category);
            part.setModel(model);
            part.setSerialNumber(serialNumber);
            part.setPrice(price);

            _controller.updatePart(part);
        } else {
            System.out.println("Деталь с ID " + id + " не найдена.");
        }
    }

    public void deletePart() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID детали, которую требуется удалить: ");
        int id = scanner.nextInt();
        _controller.deletePart(id);
    }
}