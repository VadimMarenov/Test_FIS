import java.util.Calendar;

public class Credit {
    static final double creditValue = 100_000;  // in rubles
    static double amountDebt = creditValue;
    static final double creditRate = 13;  // %
    static final int creditTerm = 12; // in months
    static Calendar calendar = Calendar.getInstance();

    public static void main(String[] args) {

        System.out.println("Сумма кредита: " + creditValue + " рублей");
        System.out.println("Ставка: " + creditRate + " %");
        System.out.println("Срок: " + creditTerm + " месяцев");
        System.out.println("Месяц | Ежемесячный платеж | Основной долг | Долг по процетам | Остаток основного долга");
        for (int i = 1; i <= creditTerm ; i++) {
            System.out.print(i + "     |");
            System.out.printf("%.2f      | " , getPaimentPerMonth());
            System.out.printf("%.2f      | " , getcreditRateDebt());
            System.out.printf("%.2f      | " , getMainDebt());
            System.out.printf("%.2f       " ,getAmountOfThePrincipalDebt());
            System.out.println("");

            calendar.add(Calendar.MONTH, 1);
        }

    }
    /*Получем фиксированный ежемесячный платеж*/
    public static double getPaimentPerMonth() {
        double creditRatePerMonth = creditRate / creditTerm / 100;
        double koef = (creditRatePerMonth * Math.pow(1 + creditRatePerMonth, creditTerm)) /
                (Math.pow(1 + creditRatePerMonth, creditTerm) - 1);
        double paimentPerMonth = creditValue * koef;
        return paimentPerMonth;

    }
    /*Получаем долг по процентам*/
    public static double getcreditRateDebt(){
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        double creditRateDebt = amountDebt * creditRate / 100 * daysInMonth / 365;
        return creditRateDebt;
    }

    /*Получаем выплаты по основному долгу*/
    public static double getMainDebt(){
        double mainDebt = getPaimentPerMonth() - getcreditRateDebt();
        return mainDebt;
    }

    /*Получаем остаток основного счета*/
    public static double getAmountOfThePrincipalDebt(){
        amountDebt = amountDebt - getMainDebt();
        return amountDebt;
    }

}
