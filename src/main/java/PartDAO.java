
import java.util.List;

public interface PartDAO {
    Part getPartById(int id);
    List<Part> getAllParts();
    void addPart(Part part);
    void updatePart(Part part);
    void deletePart(int id);
}