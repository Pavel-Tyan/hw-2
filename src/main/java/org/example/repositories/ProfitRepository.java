package org.example.repositories;

import lombok.Getter;
import lombok.Setter;
import org.example.models.Profit;

public class ProfitRepository {
    final String fileName = "profit.dat";

    @Getter
    @Setter
    Profit profit;


}
