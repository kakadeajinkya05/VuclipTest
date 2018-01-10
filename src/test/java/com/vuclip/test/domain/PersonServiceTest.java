package com.vuclip.test.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.vuclip.domain.Person;
import com.vuclip.service.PersonService;

public class PersonServiceTest {
	
	private List<Person> personList;
	private PersonService personImpl;
	
	private final double AVG_AGE = 3.6333333333333333;
	private final double MEDIAN_AGE = 40.0;
	private final String COMMON_CHAR = "a";
	private final int COMMON_CHAR_SIZE = 10;
	private final double AGE_LENGTH = 7.0775;
	private final String YOUNGEST_PERSON = "Name937";
	private final int YOUNGEST_PERSONS_AGE = 1;
	private final String OLDEST_PERSON = "Name80";
	private final int OLDEST_PERSONS_AGE = 80;
	private final String LONGEST_NAME = "Name1000";
	private final int LOWER_AGE_LIMIT = 18;
	private final int UPPER_AGE_LIMIT = 60;
	
	
	@Before
	public final void setup(){
		personList = createPersonObjects();
		personImpl = new PersonService();
	}

	@Test
	public void Test_to_find_average_age_from_personList(){
		double average = personImpl.finadAverageAgeOfPerson(personList);
		assertThat(average, is(AVG_AGE));
	}
	
	@Test
	public void Test_to_find_median_age_from_personList(){
		double median = personImpl.findMedianAgeOfPerson(personList);
		assertThat(median, is(MEDIAN_AGE));
	}
	
	@Test
	public void Test_to_find_10Most_common_chars_from_personList(){
		List<Map.Entry<String, Long>> result = personImpl.find10MostCommonCharsFromPersonList(personList);
		assertThat(result.size(), is(COMMON_CHAR_SIZE));
		assertThat(result.get(0).getKey(), is(COMMON_CHAR));
		assertThat(result.get(0).getValue(), is(1200L));
	}
	
	@Test
	public void Test_to_find_average_length_of_names(){
		double avgLength = personImpl.findAverageLengthOfNames(personList);
		assertThat(avgLength, is(AGE_LENGTH));
	}
	
	@Test
	public void Test_to_find_longest_name(){
		String longestName = personImpl.findLongestName(personList);
		assertThat(longestName, is(LONGEST_NAME));
	}
	
	
	@Test
	public void Test_to_find_youngest_person(){
		Optional<Entry<String, Integer>> youngestNameEntry = personImpl.findYoungestPerson(personList);
		assertThat(youngestNameEntry.get().getKey(), is(YOUNGEST_PERSON));
		assertThat(youngestNameEntry.get().getValue(), is(YOUNGEST_PERSONS_AGE));
	}
	
	@Test
	public void Test_to_find_oldest_person(){
		Optional<Entry<String, Integer>> oldestNameEntry  = personImpl.findOldestPerson(personList);
		assertThat(oldestNameEntry.get().getKey(), is(OLDEST_PERSON));
		assertThat(oldestNameEntry.get().getValue(), is(OLDEST_PERSONS_AGE));
	}
	
	@Test
	public void Test_to_find_the_count_between_ageGroup(){
		Map<String, Integer> oldestNameEntry  = personImpl.findCountBetweenAgeGroup(personList, LOWER_AGE_LIMIT, UPPER_AGE_LIMIT);
		assertThat(oldestNameEntry.size(), is(43));
	}
	
	private List<Person> createPersonObjects() {
		List<Person> list = new ArrayList<>();
		for(int i=1;i<=1200;i++){
			int age = i;
			if(i>80){
				age=1;
			}
			list.add(new Person("Name"+i,age));
		}
		return Collections.unmodifiableList(list);
	}
}
