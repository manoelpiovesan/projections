package org.acme.utils;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.entities.Product;
import org.acme.repositories.ProductRepository;

import java.util.List;

@ApplicationScoped
public class ScheduledTasks {
    @Inject
    ProductRepository productRepository;

    /*
     * The @Scheduled annotation is used to schedule a method to be executed at a
     * specific time or interval. The every attribute is used to specify the interval
     * at which the method should be executed.
     * */
    @Scheduled(every = "10s")
    @Transactional
    void updatePrices() {
        System.out.println("Updating prices");
        List<Product> products = productRepository.listAll();
        for (Product product : products) {
            product.price = product.price + 1;
            productRepository.persist(product);
        }
    }

    /*
     * Using cron expressions to schedule tasks.
     * The cron attribute is used to specify a cron expression that defines when the
     * method should be executed. The cron expression is a string that consists of six
     * or seven fields separated by white space. The fields represent the following:
     *
     * 1. Seconds (0-59)
     * 2. Minutes (0-59)
     * 3. Hours (0-23)
     * 4. Day of month (1-31)
     * 5. Month (1-12)
     * 6. Day of week (0-7) (0 or 7 is Sunday)
     * 7. Year (optional field)
     *
     * The cron expression can also include special characters such as * (all values),
     * ? (no specific value), - (range of values), and / (increment). For example,
     * the cron expression "0 0 0 * * ?" specifies that the method should be executed
     * at midnight every day.
     * */
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    void updatePricesWithCron() {
        System.out.println("Updating prices");
        List<Product> products = productRepository.listAll();
        for (Product product : products) {
            product.price = product.price + 1;
            productRepository.persist(product);
        }
    }
}
