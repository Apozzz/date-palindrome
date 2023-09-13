import java.util.ArrayList;
import java.util.List;

public class PalindromeDateFinder {

    public static void main(String[] args) {
        List<String> palindromeDates = findPalindromeDates(2010, 2015);
        palindromeDates.forEach(System.out::println);
    }

    public static List<String> findPalindromeDates(int startYear, int endYear) {
        if (startYear < 0 || endYear < 0 || startYear > endYear) {
            throw new IllegalArgumentException("Please provide valid year range.");
        }

        List<String> palindromeDates = new ArrayList<>();

        for (int year = startYear; year <= endYear; year++) {
            String yearStr = String.format("%04d", year);
            String reversedYear = new StringBuilder(yearStr).reverse().toString();

            String monthStr = reversedYear.substring(0, 2);
            String dayStr = reversedYear.substring(2);

            if (isValidDate(year, monthStr, dayStr)) {
                palindromeDates.add(yearStr + "-" + monthStr + "-" + dayStr);
            }
        }

        return palindromeDates;
    }

    private static boolean isValidDate(int year, String month, String day) {
        int monthInt = Integer.parseInt(month);
        int dayInt = Integer.parseInt(day);
    
        return switch (monthInt) {
            case 2 -> isLeapYear(year) ? dayInt >= 1 && dayInt <= 29 : dayInt >= 1 && dayInt <= 28;
            case 4, 6, 9, 11 -> dayInt >= 1 && dayInt <= 30;
            case 1, 3, 5, 7, 8, 10, 12 -> dayInt >= 1 && dayInt <= 31;
            default -> false;
        };
    }

    private static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
    }
}