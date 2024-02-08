import javax.swing.*;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;


 class Main {

        private static int balance = 5000;   // başlangıç bakiyesi
        private static int withdrawLimit = 2000;   // günlük para çekme limiti
        private static int transactionCount = 0;   // günlük işlem sayısı
        private static int maxTransactionCount = 5;   // günlük işlem sayısı limiti
        private static boolean running = true;   // ATM çalışıyor mu?
        private static boolean cardInserted = false;   // kart takılı mı?
        private static String accountNumber = "123456789";   // hesap numarası
        private static String pin = "1234";   // PIN numarası

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            while (running) {

                if (!cardInserted) {
                    System.out.println("Lütfen kartınızı takın.");
                    System.out.println("1. Kart Tak");
                    System.out.println("2. ATM'yi Kapat");
                    System.out.print("Lütfen yapmak istediğiniz işlemi seçin: ");

                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            insertCard(scanner);
                            break;
                        case 2:
                            closeATM();
                            break;
                        default:
                            System.out.println("Geçersiz seçim!");
                    }
                } else {
                    System.out.println("1. Para Yatırma");
                    System.out.println("2. Para Çekme");
                    System.out.println("3. Bakiye Sorgulama");
                    System.out.println("4. Günlük Limitleri Sıfırla");
                    System.out.println("5. Kart Çıkart");
                    System.out.print("Lütfen yapmak istediğiniz işlemi seçin: ");

                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            deposit(scanner);
                            break;
                        case 2:
                            withdraw(scanner);
                            break;
                        case 3:
                            checkBalance();
                            break;
                        case 4:
                            resetDailyLimit();
                            break;
                        case 5:
                            ejectCard();
                            break;
                        default:
                            System.out.println("Geçersiz seçim!");
                    }
                }
            }
        }

        private static void insertCard(Scanner scanner) {
            System.out.print("Hesap numaranızı girin: ");
            String accountNumberInput = scanner.next();

            System.out.print("PIN numaranızı girin: ");
            String pinInput = scanner.next();

            if (accountNumberInput.equals(accountNumber) && pinInput.equals(pin)) {
                cardInserted = true;
                System.out.println("Hoş geldiniz!");
            } else {
                System.out.println("Hesap numaranız veya PIN numaranız yanlış.");
            }
        }

        private static void ejectCard() {
            cardInserted = false;
            System.out.println("Kartınız çıkarıldı.");
        }

        private static void deposit(Scanner scanner) {
            System.out.print("Yatırmak istediğiniz miktarı girin: ");
            int depositAmount = scanner.nextInt();
            if (depositAmount > 0) {

                balance += depositAmount;
                System.out.println("Yatırımınız başarıyla tamamlandı.");
                System.out.println("Güncel bakiyeniz: " + balance);
                transactionCount++;
            } else {
                System.out.println("Geçersiz miktar!");
            }
        }

        private static void withdraw(Scanner scanner) {
            if (transactionCount >= maxTransactionCount) {
                System.out.println("Günlük işlem limitinize ulaştınız.");
            } else {
                System.out.print("Çekmek istediğiniz miktarı girin: ");
                int withdrawAmount = scanner.nextInt();
                if (withdrawAmount > 0 && withdrawAmount <= balance && withdrawAmount <= withdrawLimit) {
                    balance -= withdrawAmount;
                    System.out.println("Çekiminiz başarıyla tamamlandı.");
                    System.out.println("Güncel bakiyeniz: " + balance);
                    transactionCount++;
                } else {
                    System.out.println("Geçersiz miktar veya limit aşıldı!");
                }
            }
        }

        private static void checkBalance() {
            System.out.println("Güncel bakiyeniz: " + balance);
        }

        private static void resetDailyLimit() {
            transactionCount = 0;
            System.out.println("Günlük limitler sıfırlandı.");
        }

        private static void closeATM() {
            running = false;
            System.out.println("ATM kapatılıyor..");
        }
    }
