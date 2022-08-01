package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        printRow(persons);

        long count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();

        List<String> recruits = persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() <= 27)
                .filter(x -> x.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        List<Person> workers = persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getSex() == Sex.WOMAN && x.getAge() <= 60 || x.getSex() == Sex.MAN && x.getAge() <= 65)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Количество несовершеннолетних :" + count);
        System.out.println("Призывники:");
        System.out.println(recruits);

        System.out.println("Рабочие с высшим образованием:");
        printRow(workers);
    }

    public static void printRow(List<Person> list) {
        System.out.printf(
                "| %10s | %10s | %7s | %5s | %10s |",
                "Фамилия",
                "Имя",
                "Возраст",
                "Пол",
                "Образование"
        );
        System.out.print("\n----------------------------------------------------------\n");
        for (Person person : list) {
            System.out.printf(
                    "| %10s | %10s | %7d | %5s | %10s |",
                    person.getFamily(),
                    person.getName(),
                    person.getAge(),
                    person.getSex(),
                    person.getEducation()
            );
            System.out.print("\n----------------------------------------------------------\n");
        }
    }

}
