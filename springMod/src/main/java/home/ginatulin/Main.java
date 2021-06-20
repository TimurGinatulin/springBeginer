package home.ginatulin;

import home.ginatulin.configurations.Configuration;
import home.ginatulin.models.Product;
import home.ginatulin.services.ClientProductService;
import home.ginatulin.services.ManagerProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
public class Main {
    private static ManagerProductService managerProductService;
    private static ClientProductService clientProductService;
    private static AnnotationConfigApplicationContext context;
    private static Scanner scanner;
    private static boolean exit;
    private static int wallet;
    private static Random randomizer;
    private static final String[] productArr = {"Kiwi", "Banana", "Lemon", "Cucumber", "Potatoes"};

    public static void main(String[] args) {
        initSettings();
        start();
        close();
    }

    private static void close() {
        System.out.println("Closing Application...");
        context.close();
        scanner.close();
        System.out.println("Bye!");
    }

    private static void start() {
        System.out.println("Welcome in trade simulator...");
        String userAnswer;
        while (!exit) {
            userAnswer = scanner.nextLine();
            answerEncoder(userAnswer);
            mangerRandomAction();
        }

    }

    private static void mangerRandomAction() {
        int randomInt = randomizer.nextInt(100);
        if (randomInt > 60) {
            if (randomInt > 80) {
                System.out.println("Manager add product!");
                addProduct();
            } else {
                delProduct();
                System.out.println("Manager del product!");
            }
        }
    }

    private static void addProduct() {
        String title = productArr[randomizer.nextInt(5)];
        managerProductService
                .addProduct(title, 3 + randomizer.nextInt(15));
        managerProductService.addCountProduct(title, 1 + randomizer.nextInt(10));
    }

    private static void delProduct() {
        managerProductService.deleteProductByTitle(productArr[randomizer.nextInt(5)]);
    }

    private static void answerEncoder(String userAnswer) {
        // Подскажите плиз что работает быстрее свич или комбинация из if?
        if (userAnswer.toLowerCase().contains("--help")) getHelp();
        else if (userAnswer.toLowerCase().contains("exit")) exit = true;
        else if (userAnswer.toLowerCase().contains("get list")) {
            List<Product> productList = clientProductService.getAllProductList();
            System.out.println("ID  Title   Cost    Count");
            for (Product p : productList) {
                System.out.println
                        (p.getId() + "    "
                                + p.getTitle() + "   "
                                + p.getCost() + "    "
                                + p.getCount() + "    ");
            }
        } else if (userAnswer.toLowerCase().contains("bye")) bye(userAnswer);
        else if (userAnswer.toLowerCase().contains("sell")) sell(userAnswer);
        else if (userAnswer.toLowerCase().contains("my shopping cart")) {
            List<Product> productList = clientProductService.getShoppingCart();
            System.out.println("ID  Title   Cost    Count");
            for (Product p : productList) {
                System.out.println
                        (p.getId() + "    "
                                + p.getTitle() + "   "
                                + p.getCost() + "    "
                                + p.getCount() + "    ");
            }
        } else if (userAnswer.toLowerCase().contains("show my wallet")) {
            int sum = 0;
            List<Product> list = clientProductService.getShoppingCart();
            for (Product p : list) {
                sum += (p.getCost() * p.getCount());
            }
            System.out.println((wallet - sum) + " RUB.");
        } else if (userAnswer.toLowerCase().contains("get cart count")) {
            System.out.println("Shopping cart contain " + clientProductService.getCartCount() + " product.");
        } else if (userAnswer.toLowerCase().contains("average price at cart"))
            System.out.println("Average price at cart" + clientProductService.averagePriceAtCart());
        else System.out.println("Unknown command! Send \"--help\" -for manual");
    }

    private static void bye(String userAnswer) {
        String[] answerArr = userAnswer.toLowerCase().split(" ");
        try {
            Integer.parseInt(answerArr[1]);
            byeOnId(answerArr);
        } catch (NumberFormatException e) {
            byeOnString(answerArr);
        }
    }

    private static void byeOnString(String[] answerArr) {
        if (clientProductService.addToShoppingCart(answerArr[1], Integer.parseInt(answerArr[2])))
            System.out.println("Ok");
        else System.out.println("STW");
    }

    private static void byeOnId(String[] answerArr) {
        if (clientProductService.addToShoppingCart(Integer.parseInt(answerArr[1]), Integer.parseInt(answerArr[2])))
            System.out.println("Ok");
        else System.out.println("STW");
    }

    private static void sell(String userAnswer) {
        String[] answerArr = userAnswer.toLowerCase().split(" ");
        try {
            Integer.parseInt(answerArr[1]);
            sellById(answerArr);
        } catch (NumberFormatException e) {
            sellByString(answerArr);
        }
    }

    private static void sellByString(String[] answerArr) {
        if (answerArr.length > 2) {
            if (clientProductService.deleteFromShoppingCart(answerArr[1], Integer.parseInt(answerArr[2]))) {
                System.out.println("Ok");
            } else System.out.println("STW");
        } else {
            if (clientProductService.deleteFromShoppingCart(answerArr[1])) {
                System.out.println("Ok");
            } else System.out.println("STW");
        }
    }

    private static void sellById(String[] answerArr) {
        if (answerArr.length > 2) {
            if (clientProductService.deleteFromShoppingCart
                    (Integer.parseInt(answerArr[1]), Integer.parseInt(answerArr[2]))) {
                System.out.println("Ok");
            } else System.out.println("STW");
        } else {
            if (clientProductService.deleteFromShoppingCart(Integer.parseInt(answerArr[1]))) {
                System.out.println("Ok");
            } else System.out.println("STW");
        }
    }

    private static void getHelp() {
        System.out.println("<------------Manual------------>\n" +
                "\"get list\"- return product list.\n" +
                "\"exit\"- close application");
    }

    private static void initSettings() {
        System.out.println("Initialization...");
        context = new AnnotationConfigApplicationContext(Configuration.class);
        managerProductService =
                context.getBean("managerProductService", ManagerProductService.class);
        clientProductService =
                context.getBean("clientProductService", ClientProductService.class);
        randomizer = new Random();
        scanner = new Scanner(System.in);
        exit = false;
        wallet = 100;
        System.out.println("Initialization done!");
    }
}
