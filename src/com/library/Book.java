package com.library;

/**
 * Kelas Book merepresentasikan sebuah buku dalam sistem perpustakaan.
 * Kelas ini menyimpan informasi dasar tentang buku seperti judul, pengarang,
 * ISBN, tahun publikasi, dan status ketersediaan.
 * 
 * @author Tim Developer Perpustakaan
 * @version 1.0
 * @since 2024-11-09
 */
public class Book {
    
    /** ID unik untuk setiap buku */
    private int bookId;
    
    /** Judul buku */
    private String title;
    
    /** Nama pengarang buku */
    private String author;
    
    /** Nomor ISBN buku */
    private String isbn;
    
    /** Tahun publikasi buku */
    private int publicationYear;
    
    /** Status ketersediaan buku (true jika tersedia, false jika dipinjam) */
    private boolean isAvailable;
    
    /** Kategori atau genre buku */
    private String category;
    
    /**
     * Constructor untuk membuat objek Book dengan semua parameter.
     * 
     * @param bookId ID unik untuk buku, harus berupa angka positif
     * @param title judul buku, tidak boleh null atau kosong
     * @param author nama pengarang buku, tidak boleh null atau kosong
     * @param isbn nomor ISBN buku dalam format standar
     * @param publicationYear tahun publikasi buku, harus antara 1000-2024
     * @param category kategori atau genre buku
     * @throws IllegalArgumentException jika parameter tidak valid
     */
    public Book(int bookId, String title, String author, String isbn, 
                int publicationYear, String category) {
        if (bookId <= 0) {
            throw new IllegalArgumentException("Book ID harus berupa angka positif");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Judul buku tidak boleh kosong");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pengarang tidak boleh kosong");
        }
        if (publicationYear < 1000 || publicationYear > 2024) {
            throw new IllegalArgumentException("Tahun publikasi tidak valid");
        }
        
        this.bookId = bookId;
        this.title = title.trim();
        this.author = author.trim();
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.category = category;
        this.isAvailable = true; // default tersedia
    }
    
    /**
     * Constructor sederhana untuk membuat objek Book dengan parameter minimal.
     * Status ketersediaan secara default adalah true (tersedia).
     * 
     * @param bookId ID unik untuk buku
     * @param title judul buku
     * @param author nama pengarang buku
     */
    public Book(int bookId, String title, String author) {
        this(bookId, title, author, "", 0, "Umum");
    }
    
    /**
     * Mendapatkan ID unik buku.
     * 
     * @return ID buku dalam bentuk integer
     */
    public int getBookId() {
        return bookId;
    }
    
    /**
     * Mengatur ID buku dengan validasi.
     * 
     * @param bookId ID baru untuk buku, harus berupa angka positif
     * @throws IllegalArgumentException jika bookId tidak valid
     */
    public void setBookId(int bookId) {
        if (bookId <= 0) {
            throw new IllegalArgumentException("Book ID harus berupa angka positif");
        }
        this.bookId = bookId;
    }
    
    /**
     * Mendapatkan judul buku.
     * 
     * @return judul buku dalam bentuk String
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Mengatur judul buku dengan validasi.
     * 
     * @param title judul baru untuk buku, tidak boleh null atau kosong
     * @throws IllegalArgumentException jika title tidak valid
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Judul buku tidak boleh kosong");
        }
        this.title = title.trim();
    }
    
    /**
     * Mendapatkan nama pengarang buku.
     * 
     * @return nama pengarang dalam bentuk String
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * Mengatur nama pengarang buku dengan validasi.
     * 
     * @param author nama pengarang baru, tidak boleh null atau kosong
     * @throws IllegalArgumentException jika author tidak valid
     */
    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pengarang tidak boleh kosong");
        }
        this.author = author.trim();
    }
    
    /**
     * Mendapatkan nomor ISBN buku.
     * 
     * @return nomor ISBN dalam bentuk String, bisa kosong jika tidak tersedia
     */
    public String getIsbn() {
        return isbn;
    }
    
    /**
     * Mengatur nomor ISBN buku.
     * 
     * @param isbn nomor ISBN baru untuk buku
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    /**
     * Mendapatkan tahun publikasi buku.
     * 
     * @return tahun publikasi dalam bentuk integer, 0 jika tidak diketahui
     */
    public int getPublicationYear() {
        return publicationYear;
    }
    
    /**
     * Mengatur tahun publikasi buku dengan validasi.
     * 
     * @param publicationYear tahun publikasi baru, harus antara 1000-2024
     * @throws IllegalArgumentException jika tahun tidak valid
     */
    public void setPublicationYear(int publicationYear) {
        if (publicationYear < 1000 || publicationYear > 2024) {
            throw new IllegalArgumentException("Tahun publikasi tidak valid");
        }
        this.publicationYear = publicationYear;
    }
    
    /**
     * Mengecek apakah buku sedang tersedia untuk dipinjam.
     * 
     * @return true jika buku tersedia, false jika sedang dipinjam
     */
    public boolean isAvailable() {
        return isAvailable;
    }
    
    /**
     * Mengatur status ketersediaan buku.
     * 
     * @param available status ketersediaan baru (true = tersedia, false = dipinjam)
     */
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
    
    /**
     * Mendapatkan kategori atau genre buku.
     * 
     * @return kategori buku dalam bentuk String
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * Mengatur kategori atau genre buku.
     * 
     * @param category kategori baru untuk buku
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * Meminjam buku jika tersedia.
     * Metode ini akan mengubah status ketersediaan menjadi false.
     * 
     * @return true jika berhasil meminjam, false jika buku sudah dipinjam
     * @throws IllegalStateException jika buku sudah dalam status dipinjam
     */
    public boolean borrowBook() {
        if (!isAvailable) {
            throw new IllegalStateException("Buku sedang tidak tersedia untuk dipinjam");
        }
        this.isAvailable = false;
        return true;
    }
    
    /**
     * Mengembalikan buku yang sudah dipinjam.
     * Metode ini akan mengubah status ketersediaan menjadi true.
     * 
     * @return true jika berhasil mengembalikan buku
     * @throws IllegalStateException jika buku sudah dalam status tersedia
     */
    public boolean returnBook() {
        if (isAvailable) {
            throw new IllegalStateException("Buku sudah dalam status tersedia");
        }
        this.isAvailable = true;
        return true;
    }
    
    /**
     * Menampilkan informasi lengkap tentang buku dalam format yang mudah dibaca.
     * 
     * @return String berisi informasi lengkap buku termasuk status ketersediaan
     */
    public String getBookInfo() {
        StringBuilder info = new StringBuilder();
        info.append("=== INFORMASI BUKU ===\n");
        info.append("ID: ").append(bookId).append("\n");
        info.append("Judul: ").append(title).append("\n");
        info.append("Pengarang: ").append(author).append("\n");
        info.append("ISBN: ").append(isbn.isEmpty() ? "Tidak tersedia" : isbn).append("\n");
        info.append("Tahun Publikasi: ").append(publicationYear == 0 ? "Tidak diketahui" : publicationYear).append("\n");
        info.append("Kategori: ").append(category).append("\n");
        info.append("Status: ").append(isAvailable ? "Tersedia" : "Dipinjam").append("\n");
        return info.toString();
    }
    
    /**
     * Membandingkan objek Book berdasarkan ID buku.
     * 
     * @param obj objek yang akan dibandingkan
     * @return true jika kedua objek memiliki ID buku yang sama
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return bookId == book.bookId;
    }
    
    /**
     * Menghasilkan hash code berdasarkan ID buku.
     * 
     * @return hash code dari objek Book
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(bookId);
    }
    
    /**
     * Menghasilkan representasi string dari objek Book.
     * 
     * @return String berisi informasi singkat tentang buku
     */
    @Override
    public String toString() {
        return String.format("Book{ID=%d, Title='%s', Author='%s', Available=%s}", 
                           bookId, title, author, isAvailable ? "Ya" : "Tidak");
    }
}