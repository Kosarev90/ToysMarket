package ru.kosarev;

import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyQueue {
    private final PriorityQueue<Toy> toyQueue;

    public ToyQueue() {
        toyQueue = new PriorityQueue<>((t1, t2) -> t2.getFrequency() - t1.getFrequency());
    }

    public void addToy(Toy toy) {
        toyQueue.offer(toy);
    }

    public int getToys() {
        Random random = new Random();

        int totalFrequency = 0;
        for (Toy toy : toyQueue) {
            totalFrequency += toy.getFrequency();
        }

        int sumFrequency = 0;
        int randomIndex = random.nextInt(totalFrequency);

        for (Toy toy : toyQueue) {
            sumFrequency += toy.getFrequency();
            if (randomIndex < sumFrequency) {
                return toy.getId();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ToyQueue toyQueue = new ToyQueue();

        toyQueue.addToy(new Toy(1, "конструктор", 20));
        toyQueue.addToy(new Toy(2, "робот", 20));
        toyQueue.addToy(new Toy(3, "кукла", 60));

        try (FileWriter writer = new FileWriter("output.txt", true)) {
            for (int i = 0; i < 10; i++) {
                int randomToyId = toyQueue.getToys();
                writer.write(Integer.toString(randomToyId));
            }
            writer.write(" Розыгыш окончен");
            writer.write(System.lineSeparator());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}