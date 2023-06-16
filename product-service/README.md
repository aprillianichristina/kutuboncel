# Kutu Boncel E-Commerce

Kutu Boncel E-Commerce adalah aplikasi e-commerce yang menyediakan berbagai macam makanan dan aksesori untuk kucing. Aplikasi ini memungkinkan pengguna untuk membeli produk-produk kucing secara online, melakukan pembayaran, dan mengatur pengiriman.

## Teknologi yang Digunakan
- Java
- Spring Boot
- MySQL
- HTML
- CSS
- JavaScript

## Database
- Nama Databse	: kutu_boncel
- username	: christina
- password	: #Chanchan88

## Prasyarat
- Java 8 atau versi yang lebih tinggi
- MySQL database
- Maven

## Getting Started
1. Clone repositori ini: git clone https://github.com/your-username/kutu-boncel.git
2. Konfigurasi koneksi database di file `application.properties`.
3. Jalankan perintah berikut untuk membangun dan menjalankan aplikasi: mvn spring-boot:run

Aplikasi akan berjalan di `http://localhost:8080`.

## Struktur Proyek
- `src/main/java`: Berisi kode Java untuk backend aplikasi.
- `src/main/resources`: Berisi file konfigurasi dan template HTML.
- `src/main/webapp`: Berisi file CSS dan JavaScript.

## API Endpoints
- `GET /api/products`: Mendapatkan daftar produk.
- `GET /api/products/{id}`: Mendapatkan detail produk berdasarkan ID.
- `POST /api/products`: Membuat produk baru.
- `PUT /api/products/{id}`: Mengupdate detail produk.
- `DELETE /api/products/{id}`: Menghapus produk.

## Kontribusi

Jika Anda ingin berkontribusi pada pengembangan aplikasi Kutu Boncel, silakan ikuti langkah-langkah berikut:

1. Fork repositori ini.

2. Buat branch baru: `git checkout -b fitur-baru`.

3. Lakukan perubahan yang diinginkan dan commit perubahan Anda: `git commit -m 'Menambahkan fitur baru'`.

4. Push ke branch Anda: `git push origin fitur-baru`.

5. Buat pull request dari branch Anda ke branch `main` repositori ini.

6. Kami akan memeriksa dan mengulas kontribusi Anda. Setelah itu, jika diterima, perubahan Anda akan digabungkan ke repositori utama.

## Lisensi

Aplikasi Kutu Boncel dilisensikan di bawah lisensi pembuat langsung Christina Apriani dengan email kutuboncel@gmail.com


