package com.library.exceptions;

/**
 * Exception yang dilempar ketika terjadi duplikasi buku dalam perpustakaan.
 * 
 * <p>Exception ini merupakan runtime exception yang dilempar ketika mencoba
 * menambahkan buku dengan ID yang sama dengan buku yang sudah ada dalam sistem.
 * Exception ini membantu menjaga integritas data perpustakaan.
 * 
 * @author Tim Developer Perpustakaan
 * @version 1.0
 * @since 2024-11-09
 * @see RuntimeException
 */
public class DuplicateBookException extends RuntimeException {
    
    /** Serial version UID untuk serialization */
    private static final long serialVersionUID = 1L;
    
    /** ID buku yang menyebabkan duplikasi */
    private int duplicateBookId;
    
    /**
     * Constructor default untuk DuplicateBookException.
     * Membuat exception dengan pesan default.
     */
    public DuplicateBookException() {
        super("Buku dengan ID yang sama sudah ada dalam perpustakaan");
        this.duplicateBookId = -1;
    }
    
    /**
     * Constructor untuk DuplicateBookException dengan pesan kustom.
     * 
     * @param message pesan error yang akan ditampilkan
     */
    public DuplicateBookException(String message) {
        super(message);
        this.duplicateBookId = -1;
    }
    
    /**
     * Constructor untuk DuplicateBookException dengan pesan dan ID buku.
     * 
     * @param message pesan error yang akan ditampilkan
     * @param duplicateBookId ID buku yang menyebabkan duplikasi
     */
    public DuplicateBookException(String message, int duplicateBookId) {
        super(message);
        this.duplicateBookId = duplicateBookId;
    }
    
    /**
     * Constructor untuk DuplicateBookException dengan pesan dan cause.
     * 
     * @param message pesan error yang akan ditampilkan
     * @param cause exception yang menyebabkan error ini
     */
    public DuplicateBookException(String message, Throwable cause) {
        super(message, cause);
        this.duplicateBookId = -1;
    }
    
    /**
     * Constructor lengkap untuk DuplicateBookException.
     * 
     * @param message pesan error yang akan ditampilkan
     * @param cause exception yang menyebabkan error ini
     * @param duplicateBookId ID buku yang menyebabkan duplikasi
     */
    public DuplicateBookException(String message, Throwable cause, int duplicateBookId) {
        super(message, cause);
        this.duplicateBookId = duplicateBookId;
    }
    
    /**
     * Mendapatkan ID buku yang menyebabkan duplikasi.
     * 
     * @return ID buku yang duplikat, atau -1 jika tidak diketahui
     */
    public int getDuplicateBookId() {
        return duplicateBookId;
    }
    
    /**
     * Mengatur ID buku yang menyebabkan duplikasi.
     * 
     * @param duplicateBookId ID buku yang menyebabkan duplikasi
     */
    public void setDuplicateBookId(int duplicateBookId) {
        this.duplicateBookId = duplicateBookId;
    }
    
    /**
     * Menghasilkan pesan error yang detail.
     * 
     * @return String berisi pesan error lengkap dengan ID buku jika tersedia
     */
    @Override
    public String getMessage() {
        String baseMessage = super.getMessage();
        if (duplicateBookId > 0) {
            return baseMessage + " (ID Buku Duplikat: " + duplicateBookId + ")";
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