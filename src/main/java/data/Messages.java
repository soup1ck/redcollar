package data;

public enum Messages {
    WINNING_MSG ("Ура! Я угадал! Это "),
    NO("нет"),
    YES("да"),
    MISTAKE("Я не угадал\n" + "Введите название животного"),
    ANIMAL_CONTAINS_EXC("Данное животное уже есть в Базе знаний"),
    DIFF("Чем отличается от "),
    START_MESSAGE("живет на суше? (да/нет)");

    public final String label;

    private Messages (String label){
        this.label = label;
    }
}
