package com.desafio.itau_islno.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Estatistica {
    private long count;
    private BigDecimal sum;
    private BigDecimal avg;
    private BigDecimal min;
    private BigDecimal max;

    public Estatistica() {
        this.count = 0L;
        this.sum = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.avg = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.min = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.max = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    public Estatistica(long count, BigDecimal sum, BigDecimal avg, BigDecimal min, BigDecimal max) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }

    public long getCount() { return count; }
    public void setCount(long count) { this.count = count; }
    public BigDecimal getSum() { return sum; }
    public void setSum(BigDecimal sum) { this.sum = sum; }
    public BigDecimal getAvg() { return avg; }
    public void setAvg(BigDecimal avg) { this.avg = avg; }
    public BigDecimal getMin() { return min; }
    public void setMin(BigDecimal min) { this.min = min; }
    public BigDecimal getMax() { return max; }
    public void setMax(BigDecimal max) { this.max = max; }
}