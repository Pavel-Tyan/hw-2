package org.example.services.implementations;

import lombok.AllArgsConstructor;
import org.example.models.Profit;
import org.example.repositories.ProfitRepository;
import org.example.services.ProfitService;

@AllArgsConstructor
public class ProfitServiceImplementation implements ProfitService {
    private ProfitRepository profitRepository;

    @Override
    public Profit getProfit() {
        return profitRepository.getProfit();
    }

    @Override
    public void updateProfit(Profit updatedProfit) {
        profitRepository.setProfit(updatedProfit);
    }

    @Override
    public void saveProfit() {
        profitRepository.serializeProfit();
    }


}
