package be.pxl.ja.exercise1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentDao {

	public static List<Student> createStudents() {
		List<Student> students = new ArrayList<>();
		students.add(new Student("Alice", 2018, 82, LocalDate.of(1998,5,4)));
		students.add(new Student("Bob", 2017, 90, LocalDate.of(1998,1,12)));
		students.add(new Student("Carol", 2018, 67, LocalDate.of(1997, 12,1)));
		students.add(new Student("David", 2018, 80, LocalDate.of(1997, 3,23)));
		students.add(new Student("Eric", 2017, 55, LocalDate.of(1998, 4,15)));
		students.add(new Student("Frank", 2018, 49, LocalDate.of(1996, 12,1)));
		students.add(new Student("Gary", 2017,88, LocalDate.of(1996, 1,12)));
		students.add(new Student("Henry", 2017, 98, LocalDate.of(1997, 12,1)));
		students.add(new Student("Ivan", 2018, 66, LocalDate.of(1999, 3,21)));
		students.add(new Student("John", 2017, 52, LocalDate.of(1997, 1,3)));
		return students;
	}

	public static void main(String[] args){
		List<Student> students = createStudents();

		System.out.println("Welke studenten zijn er vandaag jarig. Toon hun naam. Je verandert best een geboortedata van de studenten om je stream uit te testen.");
		students.stream()
				.filter(s -> s.getDateOfBirth().getDayOfMonth() == 1 && s.getDateOfBirth().getMonthValue() == 12)
				.map(Student::getName)
				.forEach(System.out::println);
		// met peek gaat dat ook
		// .peek(s -> System.out.println("Filtered value: " + Student::getName))

		System.out.println("Toon alle gegevens van de student met de naam Carol.");
		students.stream()
				.filter(s -> s.getName().equals("Carol"))
				.forEach(s -> System.out.println(s.getName() + " " + s.getScore() + " " + s.getGraduationYear() + " " +s.getDateOfBirth()));


		System.out.println("Hoeveel studenten studeerden af in 2017?");
		System.out.println(students.stream()
				.filter(s -> s.getGraduationYear() == 2017)
				.count());



		System.out.println("Wat is de hoogste score ooit behaald? Wie behaalde deze hoogste score ooit?");
		students.stream()
				.sorted((student1, student2) -> student2.getScore() - student1.getScore())
				.limit(1)
				.forEach(s -> System.out.println(s.getScore() + " " + s.getName()));


		System.out.println("Wie is de oudste persoon in de lijst?");
		students.stream()
				.sorted(Comparator.comparing(Student::getDateOfBirth))
				.limit(1)
				.forEach(s -> System.out.println(s.getName() + " " + ChronoUnit.YEARS.between(s.getDateOfBirth(), LocalDate.now())));


		System.out.println("Geef de namen van alle studenten gescheiden door komma (,) in een String");
		System.out.println(students.stream()
				.map(Student::getName)
				.collect(Collectors.joining(",")));

		System.out.println("Wie is de jongste student van afstudeerjaar 2018?");

		
	}
}
