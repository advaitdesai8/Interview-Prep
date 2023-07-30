package com.advait.interviewprep.coding;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamAPIQuestions {

    public static void main(String[] args) {

        System.out.println("Count Empty String");
        List<String> list = List.of("", "advait", "messi","is", "the", "goat", "", "");
        System.out.println(list.stream().filter(String::isEmpty).count());

        System.out.println("----------------");
        System.out.println("Square numbers in list");
        List<Integer> numList = new ArrayList<>(Arrays.asList(1, 10 , 4, 100));
        numList.stream().map(c -> c * c).toList().forEach(System.out::println); //can also use Math.pow()

        System.out.println("----------------");
        System.out.println("Duplicate in a list using stream using hashmap");
        numList.add(10);
        numList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().forEach(System.out::println);

        System.out.println("Duplicate in a list using stream using set");
        Set<Integer> set = new HashSet<>();
        numList.stream().filter(c -> !set.add(c)).toList().forEach(System.out::println);


        System.out.println("----------------");
        System.out.println("Second highest element");
        numList.add(10);
        System.out.println(numList.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().get());

        System.out.println("Sort a employee list based on a age and return employee list");
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("john", 35));
        employees.add(new Employee("advait", 25));
        employees.add(new Employee("beetle", 25));
        employees.add(new Employee("jack", 27));

        //ASC order
        employees.sort(Comparator.comparing(Employee::getAge));
        employees.stream().map(Employee::getAge).forEach(System.out::println);

        System.out.println("----------------");

        //desc order
        employees.sort(Comparator.comparing(Employee::getAge, Comparator.reverseOrder()));
        employees.stream().map(Employee::getAge).forEach(System.out::println);

        System.out.println("Sort a employee list based on a age and return age list");
        //asc order
        employees.stream().map(Employee::getAge).sorted().forEach(System.out::println);

        System.out.println("----------------");
        //desc order
        employees.stream().flatMap(e -> Stream.of(e.getAge())).sorted(Comparator.reverseOrder()).forEach(System.out::println);

        System.out.println("Create hashmap from a list and separate grouped values by ,");

        Map<Integer, String> map = employees.stream().collect(Collectors.groupingBy(Employee::getAge, Collectors.mapping(Employee::getName, Collectors.joining(","))));

        //commented is lengthy
                //employees.stream().collect(Collectors.groupingBy(Employee::getAge, Collectors.mapping(Employee::getName, Collectors.toList())))
                //.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, v -> String.join(",", v.getValue())));
        System.out.println(map);

        System.out.println("----------------");
        System.out.println("Sort keys in hashmap");
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("a", 2);
        map1.put("c", 1);
        map1.put("b", 6);
        map1.put("d", 4);

        map1 = map1.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                 LinkedHashMap::new));

        System.out.println(map1);

        System.out.println("Sort values in hashmap");

        map1 = map1.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                LinkedHashMap::new));
        System.out.println(map1);

        System.out.println("----------------");
        System.out.println("Sort entity values in hashmap");
        Map<String, Employee> employeeMap = new HashMap<>();

        employeeMap.put("c", new Employee("advait", 25));
        employeeMap.put("a", new Employee("john", 35));
        employeeMap.put("d", new Employee("beetle", 25));
        employeeMap.put("e", new Employee("reece", 45));
        employeeMap.put("b", new Employee("jack", 27));

        System.out.println(employeeMap);
        employeeMap = employeeMap.entrySet().stream().sorted((Map.Entry.comparingByValue(Comparator.comparing(Employee::getAge)))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        System.out.println(employeeMap);


        //create hashmap by using a grouping func (sum, avg, etc)
        employees.add(new Employee("beetle", 30));
        employees.stream().collect(Collectors.groupingBy(Employee::getName, Collectors.summingInt(Employee::getAge))).entrySet().forEach(System.out::println);
        System.out.println("----------------");
        employees.stream().collect(Collectors.groupingBy(Employee::getName, Collectors.averagingInt(Employee::getAge))).entrySet().forEach(System.out::println);
        System.out.println("----------------");
        employees.stream().collect(Collectors.groupingBy(Employee::getName, Collectors.minBy(Comparator.comparing(Employee::getAge)))).values().stream().map(c -> c.get().getAge()).forEach(System.out::println);
        System.out.println("----------------");
        employees.stream().collect(Collectors.groupingBy(Employee::getName, Collectors.maxBy(Comparator.comparing(Employee::getAge)))).values().stream().map(employee -> employee.get().getAge()).forEach(System.out::println);

    }

    static class Employee {//implements Comparator<Employee>{
        String name;
        int age;

        public Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }


        public int compare(Employee o1, Employee o2) {
            return o1.getAge() - o2.getAge();
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
