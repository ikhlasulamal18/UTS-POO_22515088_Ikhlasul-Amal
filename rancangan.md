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