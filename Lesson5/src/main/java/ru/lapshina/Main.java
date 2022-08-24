package ru.lapshina;


import ru.lapshina.model.Product;
import ru.lapshina.model.ProductRepository;

public class Main {
    public static void main(String[] args) {

        //Создаем таблицу и сразу грузим несколько продуктов туда для теста работы методов.
        ProductRepository repository = new ProductRepository();


        //провера метода вставки значения в таблицу
        repository.insertOrUpdate(new Product("Kiwi",300));
        System.out.println("*********************************************************");

        //вывод на экран всех товаров в таблице
        System.out.println(repository.findAll());
        System.out.println("*********************************************************");



        //проверка удаления и вывод на экран, чтобы убедиться, что товар был действительно удален из таблицы
        repository.delete(repository.findById(1));
        System.out.println(repository.findAll());
        System.out.println("*********************************************************");

        //поиск товара по id
        System.out.println(repository.findById(2));
    }
}
