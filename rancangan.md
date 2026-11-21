# Daftar Class

1. Film
2. Studio
3. Pelanggan
4. Tiket
5. Bioskop
6. DaftarFilmTerurut (ADT Sorted List)
7. Main (Menu)

## Film
Atribut:
1. kodeFilm: String
2. judul: String
3. durasiMenit: int
4. hargaTiket: double
5. sedangTayang: boolean

Konstruktor:
1. Film()
2. Film(String kodeFilm, String judul, int durasiMenit, double hargaTiket)

Method perilaku:
1. tampilkanInfo()
2. ubahStatusTayang()
3. hitungDurasiJam(): double

Validasi:
1. judul tidak kosong
2. durasi lebih dari 0
3. harga lebih dari 0 

## Studio
Atribut:
1. kodeStudio: String
2. nama: String
3. kapasitas: int
4. kursiTerpesan: boolean[]

Konstruktor:
1. Studio()
2. Studio(String kodeStudio, String nama, int kapasitas)

Method perilaku:
1. isKursiTersedia(int nomor): boolean
2. pesanKursi(int nomor): boolean
3. hitungSisaKursi(): int
4. tampilkanDenahKursi()

Validasi:
1. kapasitas lebih dari 0
2. nomor kursi harus 1 sampai kapasitas

## Pelanggan
Atribut:
1. idPelanggan: String
2. nama: String
3. noHp: String
4. jumlahTiket: int

Konstruktor:
1. Pelanggan()
2. Pelanggan(String idPelanggan, String nama, String noHp)

Method perilaku:
1. tampilkanInfo()
2. tambahJumlahTiket()
3. isDataLengkap(): boolean

Validasi:
1. nama tidak kosong
2. noHp hanya berisi angka

## Tiket
Atribut:
1. kodeTiket: String
2. nomorKursi: int
3. totalHarga: double
4. dibatalkan: boolean
5. film: Film
6. studio: Studio
7. pelanggan: Pelanggan

Konstruktor:
1. Tiket()
2. Tiket(String kodeTiket, Film film, Studio studio, Pelanggan pelanggan, int nomorKursi)

Method perilaku:
1. hitungHarga(): double
2. batalkan()
3. tampilkanInfo()

## Bioskop
Atribut:
1. namaBioskop: String
2. alamat: String
3. daftarStudio: ArrayList<Studio>
4. daftarPelanggan: ArrayList<Pelanggan>
5. daftarTiket: ArrayList<Tiket>
6. daftarFilm: DaftarFilmTerurut

Konstruktor:
1. Bioskop()
2. Bioskop(String namaBioskop, String alamat)

Method perilaku:
1. tambahFilm(Film film): boolean
2. tambahStudio(Studio studio)
3. tambahPelanggan(Pelanggan pelanggan)
4. beliTiket(...): Tiket
5. cariFilm(String judul): Film
6. buatLaporan()

## DaftarFilmTerurut (ADT)
Atribut:
1. data: ArrayList<Film>
2. kapasitas: int (misalnya 20)

Konstruktor:
1. DaftarFilmTerurut() (kapasitas default)
2. DaftarFilmTerurut(int kapasitas)

Spesifikasi operasi publik:
1. insert(Film f): boolean
    - Prasyarat: ADT tidak penuh dan judul belum ada
    - Efek: film disisipkan pada posisi yang menjaga urutan judul. Kembalian false jika penuh atau duplikat
    - Kompleksitas: O(n)
2. remove(String judul): boolean
    - Prasyarat: ADT tidak kosong dan judul ada
    - Efek: film dihapus, elemen sesudahnya bergeser. Kembalian false jika kosong atau tidak ditemukan
    - Kompleksitas: O(n)
3. search(String judul): int
    - Prasyarat: tidak ada
    - Efek: mencari dengan binary search, mengembalikan indeks atau -1 jika tidak ditemukan
    - Kompleksitas: O(log n)
4. get(int index): Film
    - Prasyarat: 0 <= index < size
    - Efek: mengembalikan film pada indeks tersebut, atau null dengan pesan jika di luar rentang
    - Kompleksitas: O(1)
5. tampilkan(): void
    - Prasyarat: tidak ada
    - Efek: mencetak semua film secara terurut, atau pesan bila kosong
    - Kompleksitas: O(n)
6. size(): int
    - Efek: mengembalikan jumlah film saat ini. Kompleksitas O(1)
7. isFull(): boolean
    - Efek: true jika size sama dengan kapasitas. Kompleksitas O(1)
8. isEmpty(): boolean
    - Efek: true jika size sama dengan 0. Kompleksitas O(1)