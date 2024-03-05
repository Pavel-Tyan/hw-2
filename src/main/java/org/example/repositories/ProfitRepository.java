package org.example.repositories;

import lombok.Getter;
import lombok.Setter;
import org.example.models.Profit;

import java.io.*;

public class ProfitRepository {
    private final String fileName = "profit.dat";

    @Getter
    @Setter
    Profit profit = new Profit();

    public ProfitRepository() {
        Profit savedProfit;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            profit = ((Profit) inputStream.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Десериализация информации о прибыли не удалась");
        } catch (Exception ex) {
            System.out.println("Десериализация информации о прибыли не удалась");
        }
    }

    public void serializeProfit() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(profit);
        } catch (IOException ex) {
            System.out.println("Сериализация информации о прибыли не удалась");
        }
    }
}
