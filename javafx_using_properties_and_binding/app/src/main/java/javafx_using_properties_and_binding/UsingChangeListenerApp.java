package javafx_using_properties_and_binding;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx_using_properties_and_binding.model.Bill;

public class UsingChangeListenerApp implements App.Runnable {
    public void run(){
        Bill eletricBill = new Bill();

        eletricBill.amountDueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.printf("The value of amountDue changed from %.2f to %.2f.%n", oldValue.doubleValue(), newValue.doubleValue());
            }
        });

        eletricBill.setAmountDue(200.0);
        eletricBill.setAmountDue(250.0);
        eletricBill.setAmountDue(500.0);
    }
}
