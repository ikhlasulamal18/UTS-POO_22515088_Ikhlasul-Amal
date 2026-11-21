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