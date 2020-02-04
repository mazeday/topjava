package ru.javawebinar.topjava.util;

import com.sun.javafx.collections.MappingChange;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

       //System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles

        List<UserMealWithExcess> mealsShort = new ArrayList<UserMealWithExcess>();
        Map<LocalDate, Integer> mapExcess = new HashMap<>();
        //mea.getDateTime().toLocalDate()
        LocalDate time;
        Integer caloriesLocal = 0;
        time = meals.get(0).getDateTime().toLocalDate();
        for (int i=0; i < meals.size(); i++){
            if (time.equals(meals.get(i).getDateTime().toLocalDate())) {
                caloriesLocal+=meals.get(i).getCalories();

            }else{
                mapExcess.put(time, caloriesLocal);
                time = meals.get(i).getDateTime().toLocalDate();
                caloriesLocal = 0;
                caloriesLocal+=meals.get(i).getCalories();
            }
            if(i==meals.size()-1){
                mapExcess.put(time, caloriesLocal);
            }


        }
        //System.out.println(mapExcess);


        for (UserMeal meal : meals){
            if(meal.getDateTime().toLocalTime().isAfter(startTime) && meal.getDateTime().toLocalTime().isBefore( endTime) ){
                //System.out.println(meal.getDateTime().getHour());
                Boolean excess = false;
                if (mapExcess.get(meal.getDateTime().toLocalDate())>caloriesPerDay){
                    excess = true;
                }
                mealsShort.add(
                        new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess)
                );
            }
            //System.out.println(meal.getDateTime().toLocalTime());
        }

        //meals.forEach(meal -> meals.getClass());
        //System.out.println(meals.size());

        return mealsShort;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}
