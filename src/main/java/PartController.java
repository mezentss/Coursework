
import java.util.List;

public class PartController {
    private PartDAO _dao;

    public PartController(PartDAO dao) {
        _dao = dao;
    }

    public Part getPartById(int id) {
        return _dao.getPartById(id);
    }

    public List<Part> getAllParts() {
        return _dao.getAllParts();
    }

    public void addPart(Part part) {
        _dao.addPart(part);
    }

    public void updatePart(Part part) {
        _dao.updatePart(part);
    }

    public void deletePart(int id) {
        _dao.deletePart(id);
    }
}
