import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 * Задание. Реализуйте структуру телефонной книги с помощью HashMap. Программа
 * также должна учитывать, что во входной
 * структуре будут повторяющиеся имена с разными телефонами, их необходимо
 * считать, как одного человека с разными телефонами.
 * Вывод должен быть отсортирован по убыванию числа телефонов.
 * 
 * Пример ввода:
 * Иванов 234234
 * Иванов 32523
 * Иванов 5687
 * Иванов: 234234, 32523, 5687
 * 
 * Варианты Map:
 * Map<String, ArrayList>
 * Map<String, String>
 * 
 * Пример меню:
 * 1. Добавить контакт
 * 2. Вывести всех
 * 3. Выход
 */
public class PhoneBook {
    private static Map<String, ArrayList<Integer>> contacts = new HashMap<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            System.out.println("Меню телефонной книги:");
            System.out.println("1 - добавить контакт");
            System.out.println("2 - показать все контакты");
            System.out.println("3 - выход из меню");
            System.out.println();
            System.out.print("Введите номер операции: ");

            try {
                int n = scan.nextInt();
                scan.nextLine(); // Очистка буфера после считывания числа
                if (n == 1) {
                    System.out.println();
                    addContact(scan);
                    System.out.println();
                } else if (n == 2) {
                    System.out.println();
                    showContacts();
                    System.out.println();
                } else if (n == 3) {
                    System.out.println();
                    System.out.println("/ Выход из меню. До свидания! /");
                    isExit = true;
                } else {
                    System.out.println();
                    System.out.println("/ Неверный выбор операции. Повторите! /");
                    System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("/ Неверный выбор операции. Повторите! /");
                System.out.println();
                scan.nextLine(); // Очистить буфер ввода
            }
        }

        scan.close();
    }

    public static void addContact(Scanner scan) {
        System.out.print("Введите фамилию: ");
        String lastName = scan.nextLine();

        if (contacts.containsKey(lastName)) {
            ArrayList<Integer> phoneNumbers = contacts.get(lastName);
            boolean validInput = false;
            int phoneNumber = 0;

            while (!validInput) {
                System.out.println("/ Такой контакт уже есть! /");
                System.out.print("Добавьте номер телефона: ");
                String input = scan.nextLine();

                try {
                    phoneNumber = Integer.parseInt(input);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("/ Некорректный ввод. Введите числа! /");
                }
            }
            phoneNumbers.add(phoneNumber);
        } else {
            ArrayList<Integer> phoneNumbers = new ArrayList<>();
            boolean validInput = false;
            int phoneNumber = 0;

            while (!validInput) {
                System.out.print("Введите номер телефона: ");
                String input = scan.nextLine();

                try {
                    phoneNumber = Integer.parseInt(input);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("/ Некорректный ввод. Введите числа! /");
                }
            }
            phoneNumbers.add(phoneNumber);
            contacts.put(lastName, phoneNumbers);
        }
    }

    public static void showContacts() {
        List<Map.Entry<String, ArrayList<Integer>>> contactsList = new ArrayList<>(contacts.entrySet());
        // Сортируем контакты по убыванию количества номеров телефонов
        contactsList.sort(Comparator.comparingInt(entry -> ((Entry<String, ArrayList<Integer>>) entry).getValue().size()).reversed());
        // Выводим отсортированные контакты
        for (Map.Entry<String, ArrayList<Integer>> entry : contactsList) {
            String lastName = entry.getKey();
            ArrayList<Integer> phoneNumbers = entry.getValue();
            System.out.println("/ Фамилия: " + lastName + " / Номера телефонов: " + phoneNumbers + " /");
        }
    }
}