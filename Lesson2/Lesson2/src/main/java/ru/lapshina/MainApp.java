package ru.lapshina;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.lapshina.cart.Cart;
import ru.lapshina.product.ProductRepository;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductRepository repository = context.getBean("productRepository", ProductRepository.class);
        Cart cart = context.getBean("cart", Cart.class);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Чтобы добавить товар в корзину введите \"ADD\"");
        System.out.println("Чтобы удалить товар из корзины введите \"REMOVE\"");
        System.out.println("Доступны товары:");
        repository.show();
        while (true) {
            System.out.println("Введите команду");
            String s = scanner.nextLine().toUpperCase();

            switch (s) {
                case "ADD":
                    System.out.println("Введите id продукта, который хотите добавить в корзину:");
                    repository.show();
                    int id = Integer.parseInt(scanner.nextLine());
                    if (repository.findById(id) != null) {
                        cart.add(repository.findById(id), 1);
                        System.out.println("Ваша корзина:");
                        cart.show();
                    }
                    else {
                        System.out.println("Вы ввели неверный id товара. Товара с таким id не существует!");
                    }
                    break;
                case "REMOVE":
                    if (cart.findAll().size() == 0) {
                        System.out.println("Вы не можете удалить товар из корзины. Ваша корзина пуста!");
                    } else {
                        System.out.println("Введите id продукта, который хотите удалить из корзины:");
                        System.out.println("Ваша корзина:");
                        cart.show();
                        int idCart = Integer.parseInt(scanner.nextLine());
                        if (cart.findById(idCart) != null) {
                            cart.delete(cart.findById(idCart), 1);
                            System.out.println("Ваша корзина:");
                            cart.show();
                        }
                        else {
                            System.out.println("Товара с таким id нет в вашей корзине");
                        }
                    }
                    break;
                case "END":
                    return;
                default:
                    System.out.println("Вы ввели неверную команду");
            }

        }

    }
}
