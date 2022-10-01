package introduction_to_javafx_by_baeldung.model;

import com.devskiller.jfairy.Fairy;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.*;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Person {
    private Integer id;
    private String name;
    private Boolean isEmployed;

    public String getEmployedStatus(){
        return this.isEmployed ? "Sim" : "Não";
    }

    public static Person getFAKEInstance(){
        Fairy fairy = Fairy.create(Locale.ENGLISH);
        return Person.builder()
                .id(ThreadLocalRandom.current().nextInt())
                .name(fairy.person().getFirstName())
                .isEmployed(fairy.person().isMale())
                .build();
    }
}
