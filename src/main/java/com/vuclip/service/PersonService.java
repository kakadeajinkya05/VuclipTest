package com.vuclip.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import com.vuclip.domain.Person;

public class PersonService {

	public double finadAverageAgeOfPerson(List<Person> personList) {
		return personList
		          .parallelStream()
		          .mapToInt(Person::getAge)
		          .average()
		          .getAsDouble();
		
	}

	public double findMedianAgeOfPerson(List<Person> personList) {
		DoubleStream ages = personList.parallelStream().mapToDouble(Person::getAge).distinct().sorted();
		double age = ages.count();
		return age % 2 == 0 ? age / 2
				: age + 1 / 2;
	}

	public List<Map.Entry<String, Long>> find10MostCommonCharsFromPersonList(List<Person> personList) {

		char[] namesChars = personList.parallelStream().map(Person::getName).collect(Collectors.joining()).toLowerCase()
				.toCharArray();
		List<String> names = new ArrayList<>();
		for (char c : namesChars) {
			names.add(Character.toString(c));
		}
		Map<String, Long> charFrequency = names.parallelStream()
				.collect(Collectors.groupingBy(c -> c, Collectors.counting()));

		return charFrequency.entrySet().parallelStream().sorted(Map.Entry.<String, Long> comparingByValue().reversed())
				.limit(10).collect(Collectors.toList());

	}

	public double findAverageLengthOfNames(List<Person> personList) {
		List<String> names = personList.parallelStream().map(Person::getName).collect(Collectors.toList());
		return names.parallelStream().mapToInt(String::length).average().getAsDouble();

	}

	public String findLongestName(List<Person> personList) {
		List<String> names = personList.parallelStream().map(Person::getName).collect(Collectors.toList());
		return  names.parallelStream().sorted((e1, e2) -> e1.length() > e2.length() ? -1 : 1)
				.findFirst().get();
	}

	public Optional<Entry<String, Integer>> findYoungestPerson(List<Person> personList) {
		Map<String, Integer> result2 = personList.parallelStream()
				.sorted((e1, e2) -> e1.getAge().compareTo(e2.getAge()))
				.collect(Collectors.toMap(Person::getName, Person::getAge));
		return result2.entrySet().parallelStream().sorted(Map.Entry.<String, Integer> comparingByValue()).findFirst();
	}

	public Optional<Entry<String, Integer>> findOldestPerson(List<Person> personList) {
		Map<String, Integer> result2 = personList.parallelStream()
				.sorted((e1, e2) -> e1.getAge().compareTo(e2.getAge()))
				.collect(Collectors.toMap(Person::getName, Person::getAge));
		return result2.entrySet().parallelStream().sorted(Map.Entry.<String, Integer> comparingByValue().reversed())
				.findFirst();
	}
	
	public Map<String, Integer> findCountBetweenAgeGroup(List<Person> personList,int LOWER_AGE_LIMIT, int UPPER_AGE_LIMIT) {
		return personList.parallelStream().filter(e -> e.getAge() >=LOWER_AGE_LIMIT).filter(e -> e.getAge() <=60).collect(
                Collectors.toMap(Person::getName, Person::getAge));
	}
	
	
}
