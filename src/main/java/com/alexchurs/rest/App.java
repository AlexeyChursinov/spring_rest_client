package com.alexchurs.rest;

import com.alexchurs.rest.configuration.AppConfig;
import com.alexchurs.rest.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main( String[] args ) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

        System.out.println("============");
        System.out.println("REST Client");
        System.out.println("============");
        System.out.println("Методы REST:");
        System.out.println("1 - Получить всех сотрудников");
        System.out.println("2 - Получить сотрудника по ID");
        System.out.println("3 - Создать/обновить сотрудника");
        System.out.println("4 - Удалить сотрудника");
        System.out.println("--------------------------------");
        System.out.print("Введите номер метода: ");

        try {

            Scanner scanner = new Scanner(System.in);
            int numbMethod = scanner.nextInt();

            if (numbMethod == 1) {
                System.out.println("Выбрано: получение всех сотрудников");
                List<Employee> allEmployees = communication.getAllEmployees();
                System.out.println(allEmployees);
            }

            if (numbMethod == 2) {
                System.out.println("Выбрано: получение сотрудника по ID");
                System.out.print("Введите ID сотрудника: ");
                int id = scanner.nextInt();
                Employee empById = communication.getEmployee(id);
                System.out.println(empById);
            }

            if (numbMethod == 3) {
                System.out.println("Выбрано: Создать/обновить сотрудника");
                System.out.print("Введите \"1\" - для создания, \"2\" - для обновления: ");

                try {

                    int choise = scanner.nextInt();
                    if (choise == 1) {
                        Employee emp = saveUpdEmp();
                        communication.saveEmployee(emp);
                    }
                    if (choise == 2) {
                        System.out.print("Введите ID сотрудника, которого хотите изменить: ");
                        int id = scanner.nextInt();
                        Employee emp = saveUpdEmp();
                        emp.setId(id);
                        communication.saveEmployee(emp);
                    } else {
                        System.out.println("Выбран неверный вариант!");
                    }

                } catch (Exception e) {
                    throw new RuntimeException("Что-то пошло не так... Проверьте правильность ввода");
                }
            }

            if (numbMethod == 4) {
                System.out.println("Выбрано: Удалить сотрудника");
                System.out.print("Введите ID сотрудника: ");
                int id = scanner.nextInt();
                communication.deleteEmployee(id);
            }

            if (numbMethod > 4) {
                System.out.println("Что-то пошло не так... Проверьте правильность ввода");
            }

        } catch (Exception e) {
            System.out.println("Что-то пошло не так... Проверьте правильность ввода");
        }
    }

    public static Employee saveUpdEmp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные сотрудника");
        System.out.print("Фамилия: ");
        String surname = scanner.next();
        System.out.print("Имя: ");
        String name = scanner.next();
        System.out.print("Департамент: ");
        String dep = scanner.next();
        System.out.print("Зарплата: ");
        int salary = scanner.nextInt();
        Employee emp = new Employee(surname, name, dep, salary);

        return emp;
    }

}
