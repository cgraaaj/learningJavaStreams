package lectures;


import static org.assertj.core.api.Assertions.assertThat;

import beans.Car;
import beans.Person;
import beans.PersonDTO;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import mockdata.MockData;
import org.junit.Test;

public class Lecture5 {

  @Test
  public void understandingFilter() throws Exception {
    ImmutableList<Car> cars = MockData.getCars();
    List<Car> carsList = cars.stream()
            .filter(carPredicate)
            .collect(Collectors.toList());
    carsList.forEach(System.out::println);

  }
  final Predicate<Car> carPredicate = car -> car.getPrice() < 10000;

  @Test
  public void ourFirstMapping() throws Exception {
    // transform from one data type to another
    List<Person> people = MockData.getPeople();
    //Function<Person, PersonDTO> personPersonDTOFunction = person -> new PersonDTO(person.getId(), person.getFirstName(), person.getAge());
    List<PersonDTO> collectPersonDTO = people.stream()
            //.map(person -> new PersonDTO(person.getId(), person.getFirstName(), person.getAge()))
            //.map(personPersonDTOFunction)
            .map(PersonDTO::map)
            .collect(Collectors.toList());
    collectPersonDTO.forEach(System.out::println);
    assertThat(collectPersonDTO).hasSize(1000);
  }

  @Test
  public void averageCarPrice() throws Exception {
    // calculate average of car prices
    List<Car> cars = MockData.getCars();
    double ave = cars.stream()
            .mapToDouble(Car::getPrice)
            .average()
            .orElse(0);
    System.out.println(ave);
  }

  @Test
  public void test() throws Exception {
    MockData.getCars().forEach(System.out::println);
  }
}



