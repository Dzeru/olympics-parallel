package com.dzeru.olympicsparallel.service;

import com.dzeru.olympicsparallel.model.InfinityItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InfinityService {

    private static final Random RANDOM = new Random();
    private final ConcurrentLinkedQueue<InfinityItem> infinityItems = new ConcurrentLinkedQueue<>();

    public static int THREAD_COUNT = 10;
    public static int BATCH_STEP = 100;

    public ConcurrentLinkedQueue<InfinityItem> getInfinityItems() {
        return infinityItems;
    }

    private List<Integer> generateNumbers() {
        return RANDOM.ints(10000).boxed().collect(Collectors.toList());
    }

    /*
    Написать программу, которая в реальном времени будет обрабатывать очень длинный список чисел,
    находя числа, удовлетворяющие некоторому условию
    (например, числа делящиеся на 3, или простые числа, и т.д.).
    Обработку списка производить в нескольких потоках,
    каждому потоку выделить свой диапазон.
    Найденные числа выводить в реальном времени в таблицу на форме.
    В таблице должно быть число, позиция числа в списке, и номер (или имя) потока, нашедшего число.
    Сделать возможность в самой программе задавать число потоков и диапазоны.
     */

    public void infinity() {
        log.info("Start generate numbers for infinity");
        var numbers = generateNumbers();
        log.info("Finish generate numbers for infinity");
        try {
            for (int i = 0; i < THREAD_COUNT; i++) {
                var startIndex = i * BATCH_STEP;
                var endIndex = startIndex + BATCH_STEP;
                var sublist = numbers.subList(startIndex, endIndex);
                new Thread(() -> {
                    for (int number : sublist) {
                        if (number % 2 == 0) {
                            var infinityItem = new InfinityItem(
                                    numbers.indexOf(number),
                                    Thread.currentThread().getName(),
                                    number);
                            log.info(infinityItem.toString());
                            infinityItems.add(infinityItem);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("QUEUE size: " + infinityItems.size());
        }
    }
}
