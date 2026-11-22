package bioskop;

import java.util.ArrayList;

/**
 * Class Bioskop mengelola seluruh data: film (lewat ADT), studio,
 * pelanggan, dan tiket, serta menjalankan aturan bisnis pembelian tiket.
 */
public class Bioskop {
    // Kapasitas daftar film dibuat kecil agar kondisi "ADT penuh" mudah diuji.
    // Ubah nilainya bila ingin menampung lebih banyak film.
    private static final int KAPASITAS_FILM = 5;

    private String namaBioskop;
    private String alamat;
    private ArrayList<Studio> daftarStudio;
    private ArrayList<Pelanggan> daftarPelanggan;
    private ArrayList<Tiket> daftarTiket;
    private DaftarFilmTerurut daftarFilm;

    /** Konstruktor default. */
    public Bioskop() {
        this("Bioskop", "-");
    }

    /** Konstruktor berparameter. */
    public Bioskop(String namaBioskop, String alamat) {
        setNamaBioskop(namaBioskop);
        setAlamat(alamat);
        this.daftarStudio = new ArrayList<>();
        this.daftarPelanggan = new ArrayList<>();
        this.daftarTiket = new ArrayList<>();
        this.daftarFilm = new DaftarFilmTerurut(KAPASITAS_FILM);
    }

    // ---------- Getter ----------
    public String getNamaBioskop() {
        return namaBioskop;
    }

    public String getAlamat() {
        return alamat;
    }

    public ArrayList<Studio> getDaftarStudio() {
        return daftarStudio;
    }

    public ArrayList<Pelanggan> getDaftarPelanggan() {
        return daftarPelanggan;
    }

    public ArrayList<Tiket> getDaftarTiket() {
        return daftarTiket;
    }

    public DaftarFilmTerurut getDaftarFilm() {
        return daftarFilm;
    }

    // ---------- Setter dengan validasi ----------
    public void setNamaBioskop(String namaBioskop) {
        if (namaBioskop == null || namaBioskop.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama bioskop tidak boleh kosong.");
        }
        this.namaBioskop = namaBioskop.trim();
    }

    public void setAlamat(String alamat) {
        if (alamat == null || alamat.trim().isEmpty()) {
            throw new IllegalArgumentException("Alamat bioskop tidak boleh kosong.");
        }
        this.alamat = alamat.trim();
    }

    // ---------- Method perilaku ----------

    /** Menambah film ke ADT. Mengembalikan false jika penuh atau judul duplikat. */
    public boolean tambahFilm(Film film) {
        return daftarFilm.insert(film);
    }

    /** Menambah studio. Kode studio harus unik. */
    public boolean tambahStudio(Studio studio) {
        if (studio == null) {
            return false;
        }
        if (cariStudio(studio.getKodeStudio()) != null) {
            System.out.println("Gagal: kode studio \"" + studio.getKodeStudio() + "\" sudah dipakai.");
            return false;
        }
        daftarStudio.add(studio);
        return true;
    }

    /** Menambah pelanggan. ID pelanggan harus unik. */
    public boolean tambahPelanggan(Pelanggan pelanggan) {
        if (pelanggan == null) {
            return false;
        }
        if (cariPelanggan(pelanggan.getIdPelanggan()) != null) {
            System.out.println("Gagal: ID pelanggan \"" + pelanggan.getIdPelanggan() + "\" sudah dipakai.");
            return false;
        }
        daftarPelanggan.add(pelanggan);
        return true;
    }

    /**
     * Membuat transaksi pembelian tiket.
     * Aturan: film harus sedang tayang, nomor kursi harus valid dan
     * kursi belum dipesan. Mengembalikan null (disertai pesan) jika gagal.
     */
    public Tiket beliTiket(String kodeTiket, Film film, Studio studio,
                           Pelanggan pelanggan, int nomorKursi) {
        if (film == null || studio == null || pelanggan == null) {
            System.out.println("Gagal: film, studio, dan pelanggan harus dipilih.");
            return null;
        }
        if (!film.isSedangTayang()) {
            System.out.println("Gagal: film \"" + film.getJudul() + "\" sedang tidak tayang.");
            return null;
        }
        // pesanKursi menampilkan pesan sendiri bila kursi sudah dipesan / nomor tidak valid
        if (!studio.pesanKursi(nomorKursi)) {
            return null;
        }
        Tiket tiket = new Tiket(kodeTiket, film, studio, pelanggan, nomorKursi);
        pelanggan.tambahJumlahTiket();
        daftarTiket.add(tiket);
        return tiket;
    }

    /** Mencari film berdasarkan judul lewat ADT. Mengembalikan null jika tidak ada. */
    public Film cariFilm(String judul) {
        int indeks = daftarFilm.search(judul);
        if (indeks == -1) {
            return null;
        }
        return daftarFilm.get(indeks);
    }

    /** Mencari pelanggan berdasarkan ID. Mengembalikan null jika tidak ada. */
    public Pelanggan cariPelanggan(String idPelanggan) {
        for (Pelanggan p : daftarPelanggan) {
            if (p.getIdPelanggan().equalsIgnoreCase(idPelanggan)) {
                return p;
            }
        }
        return null;
    }

    /** Mencari studio berdasarkan kode. Mengembalikan null jika tidak ada. */
    public Studio cariStudio(String kodeStudio) {
        for (Studio s : daftarStudio) {
            if (s.getKodeStudio().equalsIgnoreCase(kodeStudio)) {
                return s;
            }
        }
        return null;
    }

    /** Menampilkan ringkasan: jumlah data, tiket aktif, pendapatan, dan sisa kursi. */
    public void buatLaporan() {
        int tiketAktif = 0;
        double pendapatan = 0;
        for (Tiket t : daftarTiket) {
            if (!t.isDibatalkan()) {
                tiketAktif++;
                pendapatan += t.getTotalHarga();
            }
        }
        System.out.println("===== LAPORAN " + namaBioskop.toUpperCase() + " =====");
        System.out.println("Alamat            : " + alamat);
        System.out.println("Jumlah film       : " + daftarFilm.size() + " dari " + daftarFilm.getKapasitas());
        System.out.println("Jumlah pelanggan  : " + daftarPelanggan.size());
        System.out.println("Jumlah studio     : " + daftarStudio.size());
        System.out.println("Tiket terjual     : " + tiketAktif);
        System.out.println("Total pendapatan  : Rp" + String.format("%,.0f", pendapatan));
        System.out.println("Sisa kursi per studio:");
        for (Studio s : daftarStudio) {
            System.out.println("  - " + s.getNama() + " (" + s.getKodeStudio() + "): "
                    + s.hitungSisaKursi() + " dari " + s.getKapasitas());
        }
    }
}
