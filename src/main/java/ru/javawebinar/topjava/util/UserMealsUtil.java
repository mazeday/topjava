package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        LocalDateTime time;

        for (UserMeal meal : meals){
            time = meal.getDateTime();
            int caloriesExcess = 0;
            for (UserMeal mea : meals) {
                if (mea.getDateTime().toLocalDate().isEqual(time.toLocalDate())) {
                    caloriesExcess += meal.getCalories();
                    System.out.println(caloriesExcess);
                }
            }



            if(meal.getDateTime().toLocalTime().isAfter(startTime) && meal.getDateTime().toLocalTime().isBefore( endTime) ){
                //System.out.println(meal.getDateTime().getHour());
                Boolean excess = false;
                if (meal.getCalories()>caloriesPerDay){
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
