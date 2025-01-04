import java.io.*;
import java.util.*;

public class ContactManagementSystem {
    private static final String FILE_NAME = "contacts.txt";
    private static List<Contact> contactList = new ArrayList<>();

    public static void main(String[] args) {
        loadContactsFromFile();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nContact Management System");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Edit Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addContact(scanner);
                case 2 -> viewContacts();
                case 3 -> editContact(scanner);
                case 4 -> deleteContact(scanner);
                case 5 -> saveContactsToFile();
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void addContact(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        contactList.add(new Contact(name, phone, email));
        System.out.println("Contact added successfully!");
    }

    private static void viewContacts() {
        if (contactList.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }
        System.out.println("\nContact List:");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println((i + 1) + ". " + contactList.get(i));
        }
    }

    private static void editContact(Scanner scanner) {
        viewContacts();
        if (contactList.isEmpty()) return;

        System.out.print("Enter the number of the contact to edit: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (index >= 0 && index < contactList.size()) {
            Contact contact = contactList.get(index);
            System.out.println("Editing Contact: " + contact);

            System.out.print("Enter New Name (leave blank to keep unchanged): ");
            String name = scanner.nextLine();
            if (!name.isBlank()) contact.setName(name);

            System.out.print("Enter New Phone Number (leave blank to keep unchanged): ");
            String phone = scanner.nextLine();
            if (!phone.isBlank()) contact.setPhone(phone);

            System.out.print("Enter New Email (leave blank to keep unchanged): ");
            String email = scanner.nextLine();
            if (!email.isBlank()) contact.setEmail(email);

            System.out.println("Contact updated successfully!");
        } else {
            System.out.println("Invalid contact number!");
        }
    }

    private static void deleteContact(Scanner scanner) {
        viewContacts();
        if (contactList.isEmpty()) return;

        System.out.print("Enter the number of the contact to delete: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < contactList.size()) {
            contactList.remove(index);
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Invalid contact number!");
        }
    }

    private static void loadContactsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    contactList.add(new Contact(data[0], data[1], data[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing contacts found. Starting fresh.");
        }
    }

    private static void saveContactsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contact contact : contactList) {
                bw.write(contact.toCsv());
                bw.newLine();
            }
            System.out.println("Contacts saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    // Contact class
    static class Contact {
        private String name;
        private String phone;
        private String email;

        public Contact(String name, String phone, String email) {
            this.name = name;
            this.phone = phone;
            this.email = email;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
        }

        public String toCsv() {
            return name + "," + phone + "," + email;
        }
    }
}
