package com.library;

import com.library.exceptions.BookNotFoundException;
import com.library.exceptions.DuplicateBookException;
import java.util.List;
import java.util.Scanner;

/**
 * Kelas LibraryManager merupakan kelas utama yang menyediakan interface pengguna
 * untuk sistem manajemen perpustakaan. Kelas ini menggunakan Scanner untuk 
 * berinteraksi dengan pengguna melalui console dan mengintegrasikan semua
 * fungsionalitas dari kelas Book dan Library.
 * 
 * <p>Kelas ini menyediakan menu interaktif yang memungkinkan pengguna untuk:
 * <ul>
 * <li>Menambahkan buku baru</li>
 * <li>Mencari buku berdasarkan berbagai kriteria</li>
 * <li>Meminjam dan mengembalikan buku</li>
 * <li>Melihat statistik perpustakaan</li>
 * <li>Mengelola koleksi buku</li>
 * </ul>
 * 
 * @author Tim Developer Perpustakaan
 * @version 1.5
 * @since 2024-11-09
 * @see Book
 * @see Library
 * @see BookNotFoundException
 * @see DuplicateBookException
 */
public class LibraryManager {
    
    /** Objek Library untuk mengelola koleksi buku */
    private Library library;
    
    /** Scanner untuk input dari pengguna */
    private Scanner scanner;
    
    /** Flag untuk mengontrol loop menu utama */
    private boolean isRunning;
    
    /**
     * Constructor untuk LibraryManager dengan nama perpustakaan kustom.
     * 
     * @param libraryName nama perpustakaan yang akan dikelola
     * @param maxCapacity kapasitas maksimum buku dalam perpustakaan
     * @throws IllegalArgumentException jika parameter tidak valid
     */
    public LibraryManager(String libraryName, int maxCapacity) {
        if (libraryName == null || libraryName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama perpustakaan tidak boleh kosong");
        }
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Kapasitas maksimum harus lebih dari 0");
        }
        
        this.library = new Library(libraryName, maxCapacity);
        this.scanner = new Scanner(System.in);
        this.isRunning = false;
    }
    
    /**
     * Constructor default untuk LibraryManager.
     * Membuat perpustakaan dengan nama default dan kapasitas 1000 buku.
     */
    public LibraryManager() {
        this("Perpustakaan Digital", 1000);
    }
    
    /**
     * Memulai aplikasi manajemen perpustakaan.
     * Metode ini akan menampilkan menu utama dan memproses input pengguna
     * dalam loop hingga pengguna memilih untuk keluar.
     */
    public void start() {
        isRunning = true;
        printWelcomeMessage();
        initializeSampleData();
        
        while (isRunning) {
            try {
                showMainMenu();
                int choice = getUserChoice();
                processChoice(choice);
            } catch (Exception e) {
                System.err.println("Terjadi error: " + e.getMessage());
                System.out.println("Silakan coba lagi.\n");
            }
        }
        
        cleanup();
    }
    
    /**
     * Menampilkan pesan selamat datang kepada pengguna.
     */
    private void printWelcomeMessage() {
        System.out.println("=".repeat(60));
        System.out.println("    SELAMAT DATANG DI SISTEM MANAJEMEN PERPUSTAKAAN");
        System.out.println("=".repeat(60));
        System.out.println("Perpustakaan: " + library.getLibraryName());
        System.out.println("Kapasitas Maksimum: " + library.getMaxCapacity() + " buku");
        System.out.println("=".repeat(60));
        System.out.println();
    }
    
    /**
     * Menampilkan menu utama aplikasi kepada pengguna.
     */
    private void showMainMenu() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("             MENU UTAMA");
        System.out.println("=".repeat(40));
        System.out.println("1. Tambah Buku Baru");
        System.out.println("2. Cari Buku");
        System.out.println("3. Pinjam Buku");
        System.out.println("4. Kembalikan Buku");
        System.out.println("5. Lihat Semua Buku");
        System.out.println("6. Lihat Statistik Perpustakaan");
        System.out.println("7. Hapus Buku");
        System.out.println("8. Lihat Buku yang Dipinjam");
        System.out.println("0. Keluar");
        System.out.println("=".repeat(40));
        System.out.print("Pilih menu (0-8): ");
    }
    
    /**
     * Membaca dan memvalidasi pilihan pengguna dari menu.
     * 
     * @return nomor pilihan menu yang valid (0-8)
     * @throws IllegalArgumentException jika input bukan angka atau di luar range
     */
    private int getUserChoice() {
        try {
            String input = scanner.nextLine().trim();
            int choice = Integer.parseInt(input);
            
            if (choice < 0 || choice > 8) {
                throw new IllegalArgumentException("Pilihan harus antara 0-8");
            }
            
            return choice;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input harus berupa angka");
        }
    }
    
    /**
     * Memproses pilihan menu yang dipilih pengguna.
     * 
     * @param choice nomor pilihan menu yang akan diproses
     */
    private void processChoice(int choice) {
        System.out.println(); // Line break untuk readability
        
        switch (choice) {
            case 1:
                addNewBook();
                break;
            case 2:
                searchBooks();
                break;
            case 3:
                borrowBook();
                break;
            case 4:
                returnBook();
                break;
            case 5:
                showAllBooks();
                break;
            case 6:
                showLibraryStatistics();
                break;
            case 7:
                removeBook();
                break;
            case 8:
                showBorrowedBooks();
                break;
            case 0:
                exitApplication();
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
        
        if (isRunning && choice != 0) {
            waitForEnter();
        }
    }
    
    /**
     * Menangani proses penambahan buku baru ke perpustakaan.
     * Meminta input dari pengguna untuk semua atribut buku.
     */
    private void addNewBook() {
        System.out.println("=== TAMBAH BUKU BARU ===");
        
        try {
            System.out.print("Masukkan judul buku: ");
            String title = scanner.nextLine().trim();
            
            System.out.print("Masukkan nama pengarang: ");
            String author = scanner.nextLine().trim();
            
            System.out.print("Masukkan ISBN (opsional): ");
            String isbn = scanner.nextLine().trim();
            
            System.out.print("Masukkan tahun publikasi (0 jika tidak diketahui): ");
            int year = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Masukkan kategori: ");
            String category = scanner.nextLine().trim();
            
            Book newBook = library.addBook(title, author, isbn, year, category);
            System.out.println("\n✓ Buku berhasil ditambahkan!");
            System.out.println("ID Buku: " + newBook.getBookId());
            System.out.println("Detail: " + newBook.toString());
            
        } catch (NumberFormatException e) {
            System.err.println("✗ Error: Tahun publikasi harus berupa angka");
        } catch (Exception e) {
            System.err.println("✗ Error: " + e.getMessage());
        }
    }
    
    /**
     * Menangani proses pencarian buku berdasarkan berbagai kriteria.
     * Menyediakan submenu untuk pencarian berdasarkan ID, judul, pengarang, atau kategori.
     */
    private void searchBooks() {
        System.out.println("=== PENCARIAN BUKU ===");
        System.out.println("1. Cari berdasarkan ID");
        System.out.println("2. Cari berdasarkan Judul");
        System.out.println("3. Cari berdasarkan Pengarang");
        System.out.println("4. Cari berdasarkan Kategori");
        System.out.print("Pilih jenis pencarian (1-4): ");
        
        try {
            int searchType = Integer.parseInt(scanner.nextLine().trim());
            
            switch (searchType) {
                case 1:
                    searchById();
                    break;
                case 2:
                    searchByTitle();
                    break;
                case 3:
                    searchByAuthor();
                    break;
                case 4:
                    searchByCategory();
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } catch (NumberFormatException e) {
            System.err.println("✗ Error: Input harus berupa angka");
        }
    }
    
    /**
     * Mencari buku berdasarkan ID dan menampilkan hasilnya.
     */
    private void searchById() {
        System.out.print("Masukkan ID buku: ");
        try {
            int bookId = Integer.parseInt(scanner.nextLine().trim());
            Book book = library.findBookById(bookId);
            
            if (book != null) {
                System.out.println("\n✓ Buku ditemukan:");
                System.out.println(book.getBookInfo());
            } else {
                System.out.println("\n✗ Buku dengan ID " + bookId + " tidak ditemukan.");
            }
        } catch (NumberFormatException e) {
            System.err.println("✗ Error: ID buku harus berupa angka");
        }
    }
    
    /**
     * Mencari buku berdasarkan judul dan menampilkan hasilnya.
     */
    private void searchByTitle() {
        System.out.print("Masukkan judul buku (sebagian atau lengkap): ");
        String title = scanner.nextLine().trim();
        
        try {
            List<Book> books = library.findBooksByTitle(title);
            displaySearchResults(books, "judul '" + title + "'");
        } catch (Exception e) {
            System.err.println("✗ Error: " + e.getMessage());
        }
    }
    
    /**
     * Mencari buku berdasarkan pengarang dan menampilkan hasilnya.
     */
    private void searchByAuthor() {
        System.out.print("Masukkan nama pengarang (sebagian atau lengkap): ");
        String author = scanner.nextLine().trim();
        
        try {
            List<Book> books = library.findBooksByAuthor(author);
            displaySearchResults(books, "pengarang '" + author + "'");
        } catch (Exception e) {
            System.err.println("✗ Error: " + e.getMessage());
        }
    }
    
    /**
     * Mencari buku berdasarkan kategori dan menampilkan hasilnya.
     */
    private void searchByCategory() {
        System.out.print("Masukkan kategori: ");
        String category = scanner.nextLine().trim();
        
        try {
            List<Book> books = library.findBooksByCategory(category);
            displaySearchResults(books, "kategori '" + category + "'");
        } catch (Exception e) {
            System.err.println("✗ Error: " + e.getMessage());
        }
    }
    
    /**
     * Menampilkan hasil pencarian buku dalam format yang terstruktur.
     * 
     * @param books list buku hasil pencarian
     * @param searchCriteria kriteria pencarian yang digunakan
     */
    private void displaySearchResults(List<Book> books, String searchCriteria) {
        if (books.isEmpty()) {
            System.out.println("\n✗ Tidak ada buku yang ditemukan untuk " + searchCriteria);
        } else {
            System.out.println("\n✓ Ditemukan " + books.size() + " buku untuk " + searchCriteria + ":");
            System.out.println("-".repeat(80));
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                System.out.printf("%d. %s\n", (i + 1), book.toString());
            }
        }
    }
    
    /**
     * Menangani proses peminjaman buku berdasarkan ID.
     */
    private void borrowBook() {
        System.out.println("=== PINJAM BUKU ===");
        System.out.print("Masukkan ID buku yang akan dipinjam: ");
        
        try {
            int bookId = Integer.parseInt(scanner.nextLine().trim());
            Book book = library.borrowBook(bookId);
            
            System.out.println("\n✓ Buku berhasil dipinjam!");
            System.out.println("Detail: " + book.toString());
            
        } catch (NumberFormatException e) {
            System.err.println("✗ Error: ID buku harus berupa angka");
        } catch (BookNotFoundException e) {
            System.err.println("✗ Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("✗ Error: " + e.getMessage());
        }
    }
    
    /**
     * Menangani proses pengembalian buku berdasarkan ID.
     */
    private void returnBook() {
        System.out.println("=== KEMBALIKAN BUKU ===");
        System.out.print("Masukkan ID buku yang akan dikembalikan: ");
        
        try {
            int bookId = Integer.parseInt(scanner.nextLine().trim());
            Book book = library.returnBook(bookId);
            
            System.out.println("\n✓ Buku berhasil dikembalikan!");
            System.out.println("Detail: " + book.toString());
            
        } catch (NumberFormatException e) {
            System.err.println("✗ Error: ID buku harus berupa angka");
        } catch (BookNotFoundException e) {
            System.err.println("✗ Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("✗ Error: " + e.getMessage());
        }
    }
    
    /**
     * Menampilkan semua buku yang ada dalam perpustakaan.
     */
    private void showAllBooks() {
        System.out.println("=== SEMUA BUKU DALAM PERPUSTAKAAN ===");
        
        List<Book> allBooks = library.getAllBooks();
        if (allBooks.isEmpty()) {
            System.out.println("Perpustakaan masih kosong.");
        } else {
            System.out.println("Total: " + allBooks.size() + " buku");
            System.out.println("-".repeat(80));
            for (int i = 0; i < allBooks.size(); i++) {
                Book book = allBooks.get(i);
                System.out.printf("%d. %s\n", (i + 1), book.toString());
            }
        }
    }
    
    /**
     * Menampilkan statistik lengkap perpustakaan.
     */
    private void showLibraryStatistics() {
        System.out.println("=== STATISTIK PERPUSTAKAAN ===");
        System.out.println(library.getLibraryStatistics());
    }
    
    /**
     * Menangani proses penghapusan buku berdasarkan ID.
     */
    private void removeBook() {
        System.out.println("=== HAPUS BUKU ===");
        System.out.print("Masukkan ID buku yang akan dihapus: ");
        
        try {
            int bookId = Integer.parseInt(scanner.nextLine().trim());
            
            // Tampilkan detail buku sebelum menghapus
            Book book = library.findBookById(bookId);
            if (book != null) {
                System.out.println("\nBuku yang akan dihapus:");
                System.out.println(book.toString());
                System.out.print("Apakah Anda yakin? (y/n): ");
                
                String confirmation = scanner.nextLine().trim().toLowerCase();
                if (confirmation.equals("y") || confirmation.equals("yes")) {
                    boolean success = library.removeBook(bookId);
                    if (success) {
                        System.out.println("\n✓ Buku berhasil dihapus!");
                    }
                } else {
                    System.out.println("Penghapusan dibatalkan.");
                }
            } else {
                System.out.println("\n✗ Buku dengan ID " + bookId + " tidak ditemukan.");
            }
            
        } catch (NumberFormatException e) {
            System.err.println("✗ Error: ID buku harus berupa angka");
        } catch (Exception e) {
            System.err.println("✗ Error: " + e.getMessage());
        }
    }
    
    /**
     * Menampilkan semua buku yang sedang dipinjam.
     */
    private void showBorrowedBooks() {
        System.out.println("=== BUKU YANG SEDANG DIPINJAM ===");
        
        List<Book> borrowedBooks = library.getBorrowedBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println("Tidak ada buku yang sedang dipinjam.");
        } else {
            System.out.println("Total: " + borrowedBooks.size() + " buku sedang dipinjam");
            System.out.println("-".repeat(80));
            for (int i = 0; i < borrowedBooks.size(); i++) {
                Book book = borrowedBooks.get(i);
                System.out.printf("%d. %s\n", (i + 1), book.toString());
            }
        }
    }
    
    /**
     * Menangani proses keluar dari aplikasi.
     */
    private void exitApplication() {
        System.out.println("=== KELUAR DARI APLIKASI ===");
        System.out.println("Terima kasih telah menggunakan Sistem Manajemen Perpustakaan!");
        System.out.println("Statistik terakhir:");
        System.out.println(library.getLibraryStatistics());
        isRunning = false;
    }
    
    /**
     * Menginisialisasi data contoh untuk demonstrasi aplikasi.
     * Metode ini menambahkan beberapa buku sebagai data awal.
     */
    private void initializeSampleData() {
        try {
            library.addBook("Laskar Pelangi", "Andrea Hirata", "978-979-433-549-9", 2005, "Fiksi");
            library.addBook("Bumi Manusia", "Pramoedya Ananta Toer", "978-979-433-550-5", 1980, "Fiksi");
            library.addBook("Algoritma dan Pemrograman", "Rinaldi Munir", "978-979-433-551-2", 2019, "Komputer");
            library.addBook("Matematika Diskrit", "Kenneth Rosen", "978-979-433-552-9", 2018, "Matematika");
            library.addBook("Clean Code", "Robert Martin", "978-979-433-553-6", 2008, "Komputer");
            
            System.out.println("✓ Data contoh berhasil dimuat: " + library.getTotalBooks() + " buku");
        } catch (Exception e) {
            System.err.println("Warning: Gagal memuat data contoh - " + e.getMessage());
        }
    }
    
    /**
     * Menunggu pengguna menekan Enter untuk melanjutkan.
     * Digunakan untuk memberikan jeda sebelum kembali ke menu utama.
     */
    private void waitForEnter() {
        System.out.print("\nTekan Enter untuk kembali ke menu utama...");
        scanner.nextLine();
    }
    
    /**
     * Membersihkan resource yang digunakan aplikasi.
     * Metode ini dipanggil sebelum aplikasi berakhir.
     */
    private void cleanup() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    /**
     * Mendapatkan objek Library yang digunakan oleh manager.
     * 
     * @return objek Library yang sedang dikelola
     */
    public Library getLibrary() {
        return library;
    }
    
    /**
     * Mengecek apakah aplikasi sedang berjalan.
     * 
     * @return true jika aplikasi sedang berjalan, false jika sudah dihentikan
     */
    public boolean isRunning() {
        return isRunning;
    }
    
    /**
     * Metode main untuk menjalankan aplikasi.
     * Entry point dari program sistem manajemen perpustakaan.
     * 
     * @param args argumen command line (tidak digunakan)
     */
    public static void main(String[] args) {
        try {
            System.out.println("Memulai Sistem Manajemen Perpustakaan...\n");
            
            LibraryManager manager = new LibraryManager();
            manager.start();
            
            System.out.println("\nAplikasi berakhir. Sampai jumpa!");
            
        } catch (Exception e) {
            System.err.println("Fatal Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}