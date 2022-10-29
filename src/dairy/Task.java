package dairy;

import exceptions.NoDescException;
import exceptions.NoNameException;
import exceptions.NoRepeatException;
import exceptions.NoTypeException;

import java.time.LocalDate;
import java.util.Objects;

//Каждая задача обязательно имеет заголовок. У каждой задачи может быть поле для описания.
// Также все задачи обязательно нужно делить по типу: личные или рабочие задачи.
// У каждой задачи есть дата и время, которые были присвоены при создании.
//Также для более гибкого управления задачами рекомендуется завести поле id, которое нужно проставлять через генератор
//Кроме того, в нашем ежедневнике задачи имеют разную повторяемость, которую можно указать при создании задачи
public class Task {
    private String name;
    private String describe;
    private TypeOfTasks type;
    private LocalDate creationTimeDate;
    private Repeatable repeatable;
    private int ID;
    private static int counter;



    public Task(String name, TypeOfTasks type)
            throws NoNameException, NoTypeException {
        if (name != null && !name.isEmpty() && !name.isBlank()) {
            this.name = name;
        } else {
            throw new NoNameException("У задачи отсутствует заголовок!");
        }
        this.describe = "";//можно не указывать, тогда поле пустое
        if (type != null) {
            this.type = type;
        } else {
            throw new NoTypeException("У задачи отсутствует тип личная/рабочая!");
        }
        this.creationTimeDate = LocalDate.now();
        this.ID = counter+1;
        this.repeatable = Repeatable.ONCE;//по умолчанию однократно
        System.out.println("Задача " + name + " создана!");
    }

    public Task(String name, String describe, TypeOfTasks type)
            throws NoNameException, NoTypeException, NoDescException {
        new Task(name, type);
        if (describe != null && !describe.isBlank() && !describe.isEmpty()) {
            this.describe = describe;
        } else {
            throw new NoDescException("У задачи нет описания!");
        }
    }

    public Task(String name, String describe, TypeOfTasks type, Repeatable repeatable)
            throws NoNameException, NoTypeException, NoDescException, NoRepeatException {
        new Task(name, describe, type);
        if (repeatable != null) {
            this.repeatable = repeatable;
        } else {
            throw new NoRepeatException("У задачи не признака повторяемости!");
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDescribe() {
        return describe;
    }

    public TypeOfTasks getType() {
        return type;
    }

    public LocalDate getCreationTimeDate() {
        return creationTimeDate;
    }

    public Repeatable getRepeatable() {
        return repeatable;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Задача: " +
                " Заголовок - " + name +
                ", описание - " + describe +
                ", тип - " + type +
                ", время создания" + creationTimeDate.getDayOfMonth() + "." + creationTimeDate.getMonthValue() + "." + creationTimeDate.getYear() +
                ", повторяется - " + repeatable.getMessage() +
                ", ID " + ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return ID == task.ID && Objects.equals(name, task.name) &&
                Objects.equals(describe, task.describe) &&
                type == task.type && Objects.equals(creationTimeDate, task.creationTimeDate) &&
                repeatable == task.repeatable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, describe, type, creationTimeDate, repeatable, ID);
    }
}
