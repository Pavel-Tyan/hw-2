package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.models.Profit;
import org.example.services.ProfitService;

@AllArgsConstructor
public class ProfitController {
    private ProfitService profitService;
    public Profit getProfit() {
        return profitService.getProfit();
    }

    public void updateProfit(Profit updatedProfit) {
        return;
    }

    public void saveProfit() {
        profitService.saveProfit();
    }
}
