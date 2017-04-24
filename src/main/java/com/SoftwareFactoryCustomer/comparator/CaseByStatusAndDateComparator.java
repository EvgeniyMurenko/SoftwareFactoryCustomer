package com.SoftwareFactoryCustomer.comparator;

import com.SoftwareFactoryCustomer.model.Case;

import java.util.Comparator;

public class CaseByStatusAndDateComparator implements Comparator<Case> {
    @Override
    public int compare(Case aCase1, Case aCase2) {
        int result = aCase1.getStatus().compareTo(aCase2.getStatus());
        if(result!=0) return -1*(int)(result/Math.abs(result));

        result = aCase1.getCreationDate().compareTo(aCase2.getCreationDate());
        return (result != 0) ? -1*(int)(result/Math.abs(result)) : 0;
    }
}
