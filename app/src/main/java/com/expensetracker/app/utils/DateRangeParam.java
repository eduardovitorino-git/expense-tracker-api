package com.expensetracker.app.utils;

import java.util.Date;

public class DateRangeParam {
    private Date initialDate;
    private Date finalDate;

    public DateRangeParam() {

    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
}
