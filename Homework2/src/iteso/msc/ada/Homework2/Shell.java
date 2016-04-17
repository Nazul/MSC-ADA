package iteso.msc.ada.Homework2;


public class Shell {
	enum Field {
		ID,
		Name,
		Age,
		Program	
	}
	
	public static void Sort(Student[] array, Field field) {
		int N = array.length;
		int h = 1;
		
		// 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
		while (h < N / 3)
			h = 3 * h + 1;
		
		while (h >= 1) {
			// h-sort the array
			for (int i = h; i < N; i++) {
				switch(field) {
				case ID:
					for (int j = i; j >= h && array[j].ID < array[j - h].ID; j -= h) {
						Utils.swap(array, j, j - h);
					}
					break;
				case Name:
					for (int j = i; j >= h && array[j].Name.compareTo(array[j - h].Name) < 0; j -= h) {
						Utils.swap(array, j, j - h);
					}
					break;
				case Age:
					for (int j = i; j >= h && array[j].Age < array[j - h].Age; j -= h) {
						Utils.swap(array, j, j - h);
					}
					break;
				case Program:
					for (int j = i; j >= h && array[j].Program.compareTo(array[j - h].Program) < 0; j -= h) {
						Utils.swap(array, j, j - h);
					}
					break;
				}
			}
			h /= 3;
		}
	}
	
	public static void Show(Student[] students) {
		System.out.println("{");
		for(Student student : students)
			System.out.println("Student ID: " + student.ID + "\tName: '" + student.Name + "'\tAge: " + student.Age + "\tProgram: '" + student.Program + "'");
		System.out.println("}");
	}

	public static void main(String[] args) {
        Student[] students = {new Student(1, "John", 21, "Program 1"),
        		new Student(2, "Joel", 21, "Program 2"),
        		new Student(3, "Susan", 21, "Program 3"),
        		new Student(4, "Alex", 21, "Program 4"),
        		new Student(5, "Mark", 21, "Program 5"),
        		new Student(6, "Carl", 21, "Program 1"),
        		new Student(7, "Jenny", 21, "Program 2"),
        		new Student(8, "Lucy", 21, "Program 3"),
        		new Student(9, "Betty", 21, "Program 4"),
        		new Student(10, "Paul", 21, "Program 5"),
        		new Student(11, "Sarah", 21, "Program 1"),
        		new Student(12, "Bobby", 21, "Program 2"),
        		new Student(13, "Sophie", 21, "Program 3"),
        		new Student(14, "Gary", 21, "Program 4"),
        		new Student(15, "Kelly", 21, "Program 5")
        		};
        Shell.Sort(students, Field.Name);
        System.out.println("After sorting by Name: ");
        Show(students);
        Shell.Sort(students, Field.Program);
        System.out.println("After sorting by Program: ");
        Show(students);
    }
}
