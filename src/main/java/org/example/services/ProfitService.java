package org.example.services;

import org.example.models.Profit;

public interface ProfitService {
    public Profit getProfit();
    public void updateProfit(Profit updatedProfit);

    public void saveProfit();
}
