package com.SoftwareFactoryCustomer.comparator;


import com.SoftwareFactoryCustomer.model.Estimate;

import java.util.Comparator;
import java.util.Date;

public class EstimateByDateComparator implements Comparator<Estimate> {

    @Override
    public int compare(Estimate estimate1, Estimate estimate2) {
        Date messageDate1 = estimate1.getDateRequest();
        Date messageDate2 = estimate2.getDateRequest();
        return -messageDate1.compareTo(messageDate2);
    }
}