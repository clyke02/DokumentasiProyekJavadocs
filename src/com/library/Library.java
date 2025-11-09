package com.library;

import com.library.exceptions.BookNotFoundException;
import com.library.exceptions.DuplicateBookException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Kelas Library merepresentasikan sistem manajemen perpustakaan yang mengelola
 * koleksi buku-buku. Kelas ini menyediakan fungsionalitas untuk menambah, mencari,
 * meminjam, dan mengembalikan buku.
 * 
 * <p>Kelas ini menggunakan ArrayList untuk menyimpan koleksi buku dan menyediakan
 * berbagai metode pencarian berdasarkan ID, judul, pengarang, dan kategori.
 * 
 * @author Tim Developer Perpustakaan
 * @version 1.2
 * @since 2024-11-09
 * @see Book
 * @see BookNotFoundException
 * @see DuplicateBookException
 */
public class Library {
    
    /** Nama perpustakaan */
    private String libraryName;
    
    /** Koleksi buku dalam perpustakaan */
    private List<Book> books;
    
    /** Kapasitas maksimum buku yang dapat disimpan */
    private int maxCapacity;
    
    /** Counter untuk generate ID buku otomatis */
    private int nextBookId;
    
    /**
     * Constructor untuk membuat objek Library dengan nama dan kapasitas maksimum.
     * 
     * @param libraryName nama perpustakaan, tidak boleh null atau kosong
     * @param maxCapacity kapasitas maksimum buku, harus lebih dari 0
     * @throws IllegalArgumentException jika parameter tidak valid
     */
    public Library(String libraryName, int maxCapacity) {
        if (libraryName == null || libraryName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama perpustakaan tidak boleh kosong");
        }
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Kapasitas maksimum harus lebih dari 0");
        }
        
        this.libraryName = libraryName.trim();
        this.maxCapacity = maxCapacity;
        this.books = new ArrayList<>();
        this.nextBookId = 1;
    }
    
    /**
     * Constructor default untuk membuat perpustakaan dengan kapasitas standar 1000 buku.
     * 
     * @param libraryName nama perpustakaan
     */
    public Library(String libraryName) {
        this(libraryName, 1000);
    }
    
    /**
     * Mendapatkan nama perpustakaan.
     * 
     * @return nama perpustakaan dalam bentuk String
     */
    public String getLibraryName() {
        return libraryName;
    }
    
    /**
     * Mengatur nama perpustakaan dengan validasi.
     * 
     * @param libraryName nama baru untuk perpustakaan
     * @throws IllegalArgumentException jika nama kosong atau null
     */
    public void setLibraryName(String libraryName) {
        if (libraryName == null || libraryName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama perpustakaan tidak boleh kosong");
        }
        this.libraryName = libraryName.trim();
    }
    
    /**
     * Mendapatkan kapasitas maksimum perpustakaan.
     * 
     * @return kapasitas maksimum dalam bentuk integer
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }
    
    /**
     * Mendapatkan jumlah buku yang saat ini ada di perpustakaan.
     * 
     * @return jumlah total buku dalam perpustakaan
     */
    public int getTotalBooks() {
        return books.size();
    }
    
    /**
     * Mendapatkan jumlah buku yang tersedia untuk dipinjam.
     * 
     * @return jumlah buku yang statusnya tersedia
     */
    public int getAvailableBooks() {
        return (int) books.stream().filter(Book::isAvailable).count();
    }
    
    /**
     * Mengecek apakah perpustakaan sudah mencapai kapasitas maksimum.
     * 
     * @return true jika perpustakaan sudah penuh, false jika masih ada tempat
     */
    public boolean isFull() {
        return books.size() >= maxCapacity;
    }
    
    /**
     * Menambahkan buku baru ke perpustakaan dengan ID otomatis.
     * 
     * @param title judul buku yang akan ditambahkan
     * @param author pengarang buku
     * @param isbn nomor ISBN buku (opsional)
     * @param publicationYear tahun publikasi buku
     * @param category kategori buku
     * @return objek Book yang berhasil ditambahkan
     * @throws IllegalStateException jika perpustakaan sudah penuh
     * @throws IllegalArgumentException jika parameter tidak valid
     */
    public Book addBook(String title, String author, String isbn, 
                       int publicationYear, String category) {
        if (isFull()) {
            throw new IllegalStateException("Perpustakaan sudah mencapai kapasitas maksimum");
        }
        
        Book newBook = new Book(nextBookId++, title, author, isbn, publicationYear, category);
        books.add(newBook);
        return newBook;
    }
    
    /**
     * Menambahkan objek buku yang sudah ada ke perpustakaan.
     * 
     * @param book objek Book yang akan ditambahkan
     * @return true jika berhasil ditambahkan
     * @throws IllegalStateException jika perpustakaan sudah penuh
     * @throws DuplicateBookException jika buku dengan ID yang sama sudah ada
     * @throws IllegalArgumentException jika book adalah null
     */
    public boolean addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Objek book tidak boleh null");
        }
        if (isFull()) {
            throw new IllegalStateException("Perpustakaan sudah mencapai kapasitas maksimum");
        }
        if (findBookById(book.getBookId()) != null) {
            throw new DuplicateBookException("Buku dengan ID " + book.getBookId() + " sudah ada");
        }
        
        books.add(book);
        // Update nextBookId jika perlu
        if (book.getBookId() >= nextBookId) {
            nextBookId = book.getBookId() + 1;
        }
        return true;
    }
    
    /**
     * Mencari buku berdasarkan ID.
     * 
     * @param bookId ID buku yang dicari
     * @return objek Book jika ditemukan, null jika tidak ditemukan
     */
    public Book findBookById(int bookId) {
        return books.stream()
                   .filter(book -> book.getBookId() == bookId)
                   .findFirst()
                   .orElse(null);
    }
    
    /**
     * Mencari buku berdasarkan judul (pencarian tidak case-sensitive).
     * 
     * @param title judul buku yang dicari (sebagian atau lengkap)
     * @return List berisi buku-buku yang judulnya mengandung kata kunci
     * @throws IllegalArgumentException jika title null atau kosong
     */
    public List<Book> findBooksByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Judul pencarian tidak boleh kosong");
        }
        
        String searchTitle = title.trim().toLowerCase();
        return books.stream()
                   .filter(book -> book.getTitle().toLowerCase().contains(searchTitle))
                   .collect(Collectors.toList());
    }
    
    /**
     * Mencari buku berdasarkan pengarang (pencarian tidak case-sensitive).
     * 
     * @param author nama pengarang yang dicari (sebagian atau lengkap)
     * @return List berisi buku-buku karya pengarang tersebut
     * @throws IllegalArgumentException jika author null atau kosong
     */
    public List<Book> findBooksByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pengarang tidak boleh kosong");
        }
        
        String searchAuthor = author.trim().toLowerCase();
        return books.stream()
                   .filter(book -> book.getAuthor().toLowerCase().contains(searchAuthor))
                   .collect(Collectors.toList());
    }
    
    /**
     * Mencari buku berdasarkan kategori.
     * 
     * @param category kategori buku yang dicari
     * @return List berisi buku-buku dalam kategori tersebut
     * @throws IllegalArgumentException jika category null atau kosong
     */
    public List<Book> findBooksByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Kategori tidak boleh kosong");
        }
        
        String searchCategory = category.trim().toLowerCase();
        return books.stream()
                   .filter(book -> book.getCategory().toLowerCase().equals(searchCategory))
                   .collect(Collectors.toList());
    }
    
    /**
     * Mendapatkan semua buku yang tersedia untuk dipinjam.
     * 
     * @return List berisi buku-buku yang statusnya tersedia
     */
    public List<Book> getAvailableBooksList() {
        return books.stream()
                   .filter(Book::isAvailable)
                   .collect(Collectors.toList());
    }
    
    /**
     * Mendapatkan semua buku yang sedang dipinjam.
     * 
     * @return List berisi buku-buku yang sedang dipinjam
     */
    public List<Book> getBorrowedBooks() {
        return books.stream()
                   .filter(book -> !book.isAvailable())
                   .collect(Collectors.toList());
    }
    
    /**
     * Meminjam buku berdasarkan ID buku.
     * 
     * @param bookId ID buku yang akan dipinjam
     * @return objek Book yang berhasil dipinjam
     * @throws BookNotFoundException jika buku dengan ID tersebut tidak ditemukan
     * @throws IllegalStateException jika buku sedang tidak tersedia
     */
    public Book borrowBook(int bookId) throws BookNotFoundException {
        Book book = findBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Buku dengan ID " + bookId + " tidak ditemukan");
        }
        
        if (!book.isAvailable()) {
            throw new IllegalStateException("Buku '" + book.getTitle() + "' sedang dipinjam");
        }
        
        book.borrowBook();
        return book;
    }
    
    /**
     * Mengembalikan buku yang sudah dipinjam berdasarkan ID buku.
     * 
     * @param bookId ID buku yang akan dikembalikan
     * @return objek Book yang berhasil dikembalikan
     * @throws BookNotFoundException jika buku dengan ID tersebut tidak ditemukan
     * @throws IllegalStateException jika buku sudah dalam status tersedia
     */
    public Book returnBook(int bookId) throws BookNotFoundException {
        Book book = findBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Buku dengan ID " + bookId + " tidak ditemukan");
        }
        
        if (book.isAvailable()) {
            throw new IllegalStateException("Buku '" + book.getTitle() + "' sudah dalam status tersedia");
        }
        
        book.returnBook();
        return book;
    }
    
    /**
     * Menghapus buku dari perpustakaan berdasarkan ID.
     * 
     * @param bookId ID buku yang akan dihapus
     * @return true jika berhasil dihapus
     * @throws BookNotFoundException jika buku tidak ditemukan
     * @throws IllegalStateException jika buku sedang dipinjam
     */
    public boolean removeBook(int bookId) throws BookNotFoundException {
        Book book = findBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Buku dengan ID " + bookId + " tidak ditemukan");
        }
        
        if (!book.isAvailable()) {
            throw new IllegalStateException("Tidak dapat menghapus buku yang sedang dipinjam");
        }
        
        return books.remove(book);
    }
    
    /**
     * Mendapatkan semua buku dalam perpustakaan.
     * 
     * @return List berisi semua buku (copy untuk mencegah modifikasi eksternal)
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
    
    /**
     * Mengecek apakah perpustakaan kosong (tidak ada buku).
     * 
     * @return true jika tidak ada buku, false jika ada buku
     */
    public boolean isEmpty() {
        return books.isEmpty();
    }
    
    /**
     * Menghitung persentase kapasitas yang telah terpakai.
     * 
     * @return persentase kapasitas terpakai (0.0 - 100.0)
     */
    public double getCapacityUsagePercentage() {
        return (double) books.size() / maxCapacity * 100.0;
    }
    
    /**
     * Menghasilkan laporan statistik perpustakaan dalam format yang mudah dibaca.
     * 
     * @return String berisi statistik lengkap perpustakaan
     */
    public String getLibraryStatistics() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== STATISTIK PERPUSTAKAAN ===\n");
        stats.append("Nama: ").append(libraryName).append("\n");
        stats.append("Total Buku: ").append(getTotalBooks()).append("/").append(maxCapacity).append("\n");
        stats.append("Buku Tersedia: ").append(getAvailableBooks()).append("\n");
        stats.append("Buku Dipinjam: ").append(getTotalBooks() - getAvailableBooks()).append("\n");
        stats.append("Kapasitas Terpakai: ").append(String.format("%.1f%%", getCapacityUsagePercentage())).append("\n");
        
        // Statistik per kategori
        var categoryStats = books.stream()
            .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
        
        if (!categoryStats.isEmpty()) {
            stats.append("\n=== BUKU PER KATEGORI ===\n");
            categoryStats.forEach((category, count) -> 
                stats.append(category).append(": ").append(count).append(" buku\n"));
        }
        
        return stats.toString();
    }
    
    /**
     * Membersihkan semua buku dari perpustakaan.
     * Hanya buku yang tersedia yang akan dihapus, buku yang dipinjam akan tetap ada.
     * 
     * @return jumlah buku yang berhasil dihapus
     */
    public int clearAvailableBooks() {
        List<Book> availableBooks = getAvailableBooksList();
        books.removeIf(Book::isAvailable);
        return availableBooks.size();
    }
    
    /**
     * Menghasilkan representasi string dari objek Library.
     * 
     * @return String berisi informasi singkat tentang perpustakaan
     */
    @Override
    public String toString() {
        return String.format("Library{Name='%s', Books=%d/%d, Available=%d}", 
                           libraryName, getTotalBooks(), maxCapacity, getAvailableBooks());
    }
}