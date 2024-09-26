import java.util.ArrayList; 
import java.util.List;

// Observer interface
interface DepartmentObserver {
    void update(String message);
}

// Concrete Observer: Department
class Department implements DepartmentObserver {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received project update: " + message);
    }
}

// Subject class: Project
class Project {
    private List<DepartmentObserver> departments = new ArrayList<>();

    public void addObserver(DepartmentObserver observer) {
        departments.add(observer);
    }

    public void removeObserver(DepartmentObserver observer) {
        departments.remove(observer);
    }

    public void notifyDepartments(String message) {
        for (DepartmentObserver department : departments) {
            department.update(message);
        }
    }
}

// Main to test
public class Observer {
    public static void main(String[] args) {
        Project project = new Project();

        DepartmentObserver hrDepartment = new Department("HR");
        DepartmentObserver itDepartment = new Department("IT");

        project.addObserver(hrDepartment);
        project.addObserver(itDepartment);

        project.notifyDepartments("The project is moving to the implementation phase!");
    }
}
