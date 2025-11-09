package com.library.exceptions;

/**
 * Exception yang dilempar ketika buku yang dicari tidak ditemukan dalam perpustakaan.
 * 
 * <p>Exception ini merupakan checked exception yang harus ditangani oleh
 * kode yang memanggilnya. Biasanya dilempar pada operasi pencarian, peminjaman,
 * atau pengembalian buku ketika ID buku yang diminta tidak ada dalam sistem.
 * 
 * @author Tim Developer Perpustakaan
 * @version 1.0
 * @since 2024-11-09
 * @see Exception
 */
public class BookNotFoundException extends Exception {
    
    /** Serial version UID untuk serialization */
    private static final long serialVersionUID = 1L;
    
    /** ID buku yang tidak ditemukan */
    private int bookId;
    
    /**
     * Constructor default untuk BookNotFoundException.
     * Membuat exception dengan pesan default.
     */
    public BookNotFoundException() {
        super("Buku yang dicari tidak ditemukan dalam perpustakaan");
        this.bookId = -1;
    }
    
    /**
     * Constructor untuk BookNotFoundException dengan pesan kustom.
     * 
     * @param message pesan error yang akan ditampilkan
     */
    public BookNotFoundException(String message) {
        super(message);
        this.bookId = -1;
    }
    
    /**
     * Constructor untuk BookNotFoundException dengan pesan dan ID buku.
     * 
     * @param message pesan error yang akan ditampilkan
     * @param bookId ID buku yang tidak ditemukan
     */
    public BookNotFoundException(String message, int bookId) {
        super(message);
        this.bookId = bookId;
    }
    
    /**
     * Constructor untuk BookNotFoundException dengan pesan dan cause.
     * 
     * @param message pesan error yang akan ditampilkan
     * @param cause exception yang menyebabkan error ini
     */
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.bookId = -1;
    }
    
    /**
     * Constructor lengkap untuk BookNotFoundException.
     * 
     * @param message pesan error yang akan ditampilkan
     * @param cause exception yang menyebabkan error ini
     * @param bookId ID buku yang tidak ditemukan
     */
    public BookNotFoundException(String message, Throwable cause, int bookId) {
        super(message, cause);
        this.bookId = bookId;
    }
    
    /**
     * Mendapatkan ID buku yang tidak ditemukan.
     * 
     * @return ID buku, atau -1 jika tidak diketahui
     */
    public int getBookId() {
        return bookId;
    }
    
    /**
     * Mengatur ID buku yang tidak ditemukan.
     * 
     * @param bookId ID buku yang tidak ditemukan
     */
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    /**
     * Menghasilkan pesan error yang detail.
     * 
     * @return String berisi pesan error lengkap dengan ID buku jika tersedia
     */
    @Override
    public String getMessage() {
        String baseMessage = super.getMessage();
        if (bookId > 0) {
            return baseMessage + " (ID Buku: " + bookId + ")";
        }
        return baseMessage;
    }
    
    /**
     * Menghasilkan representasi string dari exception.
     * 
     * @return String berisi nama class dan pesan error
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + getMessage();
    }
}