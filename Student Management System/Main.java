import java.util.*;

class Student {
    private String id, name;
    private int marks;

    public Student(String id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public int getMarks() {
        return marks;
    }

    public String getrole() {
        return "undergraduate";
    }

    @Override
    public String toString() {
        return id + " - " + name + " (" + marks + ") " + " - " + getrole();
    }
}

class GraduateStudent extends Student {
    private String area;

    public GraduateStudent(String id, String name, int marks, String area) {
        super(id, name, marks);
        this.area = area;
    }

    public String getrole() {
        return "Graduate in (" + area + ")";
    }
}

class HonorStudent extends Student {
    private int bonusmarks;

    public HonorStudent(String id, String name, int marks, int bonusmarks) {
        super(id, name, marks);
        this.bonusmarks = bonusmarks;
    }

    public String bonusgiven() {
        return "Bonus Marks given to student: (" + bonusmarks + ")";
    }
}

class Repository<T> {
    private Map<String, T> data = new HashMap<>();

    public void save(String id, T obj) {
        data.put(id, obj);
    }

    public T find(String id) {
        return data.get(id);
    }

    public void delete(String id) {
        data.remove(id);
    }
}

public class Main {

    public static List<Student> topper(List<Student> list) {
        if (list.isEmpty()) return Collections.emptyList();

        int maxMarks = list.stream().mapToInt(Student::getMarks).max().orElse(0);

        List<Student> toppers = new ArrayList<>();
        for (Student s : list) {
            if (s.getMarks() == maxMarks) {
                toppers.add(s);
            }
        }
        return toppers;
    }
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("S1", "Ronak", 92));
        list.add(new Student("S2", "Anish", 32));
        list.add(new Student("S3", "Ayush", 52));

        list.add(new GraduateStudent("G1", "David", 88, "Comp Eng"));

        Repository<Student> repo = new Repository<>();
        for (Student s : list) {
            repo.save(s.getId(), s);
        }

        System.out.println("All : ");
        list.forEach(System.out::println);

        System.out.println("\nLOOKUP S2: ");
        Student s = repo.find("S2");
        System.out.println(s != null ? s : "Not Found");

        Iterator<Student> it = list.iterator();

        while (it.hasNext()) {
            Student st = it.next();
            if (st.getMarks() < 80) {
                it.remove();
                repo.delete(st.getId());
            }
        }
        
        System.out.println("\nTOPPER(S): ");
        List<Student> toppers = topper(list);
        toppers.forEach(System.out::println);

        System.out.println("\n AFTER REMOVAL: ");
        list.forEach(System.out::println);

        System.out.println("\nTOPPER(S): ");
        toppers.forEach(System.out::println);
    }
}
