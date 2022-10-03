package javafx_using_properties_and_binding.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Bill {
    private DoubleProperty amountDue = new SimpleDoubleProperty();

    public final Double getAmountDue(){
        return this.amountDue.get();
    }

    public final void setAmountDue(Double amountDue){
        this.amountDue.setValue(amountDue);
    }

    public DoubleProperty amountDueProperty(){
        return this.amountDue;
    }
}
