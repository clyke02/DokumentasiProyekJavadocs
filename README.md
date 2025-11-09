
<<<<<<< HEAD
Proyek Java untuk demonstrasi penggunaan JavaDocs dalam dokumentasi kode. Sistem ini menyediakan fungsionalitas dasar untuk mengelola koleksi buku dalam perpustakaan.

## 📋 Deskripsi Proyek

Sistem Manajemen Perpustakaan yang memungkinkan pengguna untuk:

- ✅ Menambahkan buku baru ke koleksi
- 🔍 Mencari buku berdasarkan ID, judul, pengarang, atau kategori
- 📚 Meminjam dan mengembalikan buku
- 📊 Melihat statistik perpustakaan
- 🗑️ Menghapus buku dari koleksi

## 🎯 Tujuan Tugas JavaDocs

1. **Pembelajaran JavaDocs**: Implementasi dokumentasi kode menggunakan JavaDocs
2. **Praktik Best Practices**: Menerapkan standar dokumentasi yang konsisten
3. **Demonstrasi Tag JavaDocs**: Menggunakan tag `@param`, `@return`, `@throws`, `@author`, `@version`, dll.
4. **Generate HTML Documentation**: Dokumentasi HTML dari komentar JavaDocs

## 📁 Struktur Proyek

```
Root/
├── 🏠 index.html                         # Halaman utama di root (landing page)
├── 📂 docs/                              # Dokumentasi HTML JavaDoc lengkap
│   ├── 📄 index.html                     # Dokumentasi JavaDoc asli
│   ├── 📄 allclasses-index.html         # Index semua kelas
│   └── 📂 com/library/                   # Dokumentasi per kelas
│       ├── Book.html
│       ├── Library.html
│       ├── LibraryManager.html
│       └── exceptions/
│           ├── BookNotFoundException.html
│           └── DuplicateBookException.html
├── 📂 src/                               # Source code Java
│   └── com/
│       └── library/
│           ├── 📄 Book.java              # Kelas representasi buku
│           ├── 📄 Library.java           # Kelas manajemen koleksi buku
│           ├── 📄 LibraryManager.java    # Kelas utama dengan interface pengguna
│           └── exceptions/
│               ├── 📄 BookNotFoundException.java    # Exception buku tidak ditemukan
│               └── 📄 DuplicateBookException.java   # Exception duplikasi buku
└── 📄 README.md                          # File ini
```

## 📖 Akses Dokumentasi

### 🎯 **Untuk Akses Mudah (Dosen/User):**

**[Buka index.html](index.html)** - Landing page di root directory

### 📚 **Untuk Dokumentasi Lengkap:**

**[Buka docs/index.html](docs/index.html)** - Dokumentasi JavaDoc complete

**Keuntungan struktur ini:**

- ✅ **index.html di root** mudah diakses dari GitHub Pages
- ✅ **Landing page** yang informatif dan user-friendly
- ✅ **Auto-redirect** ke dokumentasi lengkap dalam 10 detik
- ✅ **Dokumentasi lengkap** tetap terorganisir di folder `docs/`

## 🏗️ Fitur Utama

### Kelas Book

- **Atribut**: ID, judul, pengarang, ISBN, tahun publikasi, kategori, status ketersediaan
- **Validasi**: Input validation untuk semua field penting
- **Metode**: Getter/setter, peminjaman, pengembalian, info lengkap

### Kelas Library

- **Kapasitas**: Manajemen kapasitas maksimum perpustakaan
- **Pencarian**: Berdasarkan ID, judul, pengarang, kategori
- **Operasi**: Tambah, hapus, pinjam, kembalikan buku
- **Statistik**: Laporan lengkap status perpustakaan

### Kelas LibraryManager

- **Interface**: Menu interaktif berbasis console
- **Validasi**: Input validation dan error handling
- **Data Sample**: Inisialisasi data contoh untuk demonstrasi

### Exception Classes

- **BookNotFoundException**: Checked exception untuk buku tidak ditemukan
- **DuplicateBookException**: Runtime exception untuk duplikasi buku

## 📝 Tag JavaDocs yang Digunakan

- `@param` - Deskripsi parameter metode
- `@return` - Deskripsi nilai kembalian
- `@throws` - Deskripsi exception yang mungkin dilempar
- `@author` - Nama pembuat kelas
- `@version` - Versi kelas
- `@since` - Versi sejak kapan fitur tersedia
- `@see` - Referensi ke kelas/metode lain

## 🎓 Pembelajaran JavaDocs

Proyek ini mendemonstrasikan:

1. **Konsistensi**: Semua kelas dan metode public memiliki dokumentasi lengkap
2. **Kelengkapan**: Setiap parameter, return value, dan exception terdokumentasi
3. **Kualitas**: Deskripsi yang jelas dan informatif
4. **Standar**: Mengikuti konvensi JavaDoc yang berlaku
5. **HTML Generation**: Menghasilkan dokumentasi HTML yang profesional

## 📊 Statistik Dokumentasi

| Component           | Methods Documented | Classes Documented |
| ------------------- | ------------------ | ------------------ |
| Book.java           | 25+ methods        | 1 class            |
| Library.java        | 20+ methods        | 1 class            |
| LibraryManager.java | 15+ methods        | 1 class            |
| Exception Classes   | 10+ methods        | 2 classes          |
| **TOTAL**           | **70+ methods**    | **5 classes**      |

## 👥 Tim Developer

- **Author**: Mochammad Rizky Firdaus
- **Version**: 1
- **Created**: November 9, 2025---

_Dokumentasi ini dibuat sebagai bagian dari tugas JavaDocs - CCDP Semester 7_
=======
>>>>>>> 0c1999fb058b799dbeaa0609c80b5894a42a1a6e
