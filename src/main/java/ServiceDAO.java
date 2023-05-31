import java.util.List;

public interface ServiceDAO {
    Service getServiceById(int id);
    List<Service> getAllServices();
    void addService(Service service);
    void updateService(Service service);
    void deleteService(int id);
}
