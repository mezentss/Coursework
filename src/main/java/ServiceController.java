
import java.util.List;

public class ServiceController {
    private ServiceDAO _dao;

    public ServiceController(ServiceDAO dao) {
        _dao = dao;
    }

    public Service getServiceById(int id) {
        return _dao.getServiceById(id);
    }

    public List<Service> getAllServices() {
        return _dao.getAllServices();
    }

    public void addService(Service service) {
        _dao.addService(service);
    }

    public void deleteService(int id) {
        _dao.deleteService(id);
    }
}
