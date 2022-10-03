package javafx_using_properties_and_binding;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class UsingTheHighLevelBindingAPIApp implements App.Runnable{

    @Override
    public void run() {
        usingFluentAPI();
    }

    void combiningBothApproachhes(){
        IntegerProperty number1 = new SimpleIntegerProperty(10);
        IntegerProperty number2 = new SimpleIntegerProperty(2);
        IntegerProperty number3 = new SimpleIntegerProperty(20);
        IntegerProperty number4 = new SimpleIntegerProperty(4);

        //(10 * 2) + (20 / 4) = 20 + 5 = 25
        NumberBinding sum = Bindings.add(number1.multiply(number2), number3.divide(number4));

        System.out.printf("Sum: %d.%n", sum.getValue().intValue());

        //(30 * 2) + (20 / 4) = 60 + 5 = 65
        number1.setValue(30);
        System.out.printf("Sum: %d.%n", sum.getValue().intValue());
    }

    void usingTheBindingClass(){
        IntegerProperty number1 = new SimpleIntegerProperty();
        IntegerProperty number2 = new SimpleIntegerProperty();
        NumberBinding sum = Bindings.add(number1, number2);

        System.out.printf("Sum: %d.%n", sum.getValue().intValue());
        number1.setValue(30);
        System.out.printf("Sum: %d.%n", sum.getValue().intValue());
    }

    void usingFluentAPI(){
        IntegerProperty number1 = new SimpleIntegerProperty();
        IntegerProperty number2 = new SimpleIntegerProperty();
        NumberBinding sum = number1.add(number2);

        System.out.printf("Sum: %d.%n", sum.getValue().intValue());
        number1.setValue(30);
        System.out.printf("Sum: %d.%n", sum.getValue().intValue());
    }

}
