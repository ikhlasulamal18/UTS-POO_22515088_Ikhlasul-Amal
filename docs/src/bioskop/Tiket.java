package bioskop;

/**
 * Class Tiket merepresentasikan transaksi pembelian tiket.
 * Tiket menghubungkan tiga objek: Film, Studio, dan Pelanggan.
 */
public class Tiket {
    private String kodeTiket;
    private int nomorKursi;
    private double totalHarga;
    private boolean dibatalkan;
    private Film film;
    private Studio studio;
    private Pelanggan pelanggan;

    /** Konstruktor default: tiket kosong tanpa relasi. */
    public Tiket() {
        this.kodeTiket = "-";
        this.nomorKursi = 0;
        this.totalHarga = 0;
        this.dibatalkan = false;
        this.film = null;
        this.studio = null;
        this.pelanggan = null;
    }

    /**
     * Konstruktor berparameter. Relasi diisi dahulu agar nomor kursi
     * dapat divalidasi terhadap kapasitas studio.
     */
    public Tiket(String kodeTiket, Film film, Studio studio, Pelanggan pelanggan, int nomorKursi) {
        setKodeTiket(kodeTiket);
        setFilm(film);
        setStudio(studio);
        setPelanggan(pelanggan);
        setNomorKursi(nomorKursi);
        this.totalHarga = hitungHarga();
        this.dibatalkan = false;
    }

    // ========== Getter ==========
    public String getKodeTiket() {
        return kodeTiket;
    }

    public int getNomorKursi() {
        return nomorKursi;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public boolean isDibatalkan() {
        return dibatalkan;
    }

    public Film getFilm() {
        return film;
    }

    public Studio getStudio() {
        return studio;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    // ========== Setter dengan validasi ==========
    public void setKodeTiket(String kodeTiket) {
        if (kodeTiket == null || kodeTiket.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode tiket tidak boleh kosong.");
        }
        this.kodeTiket = kodeTiket.trim();
    }

    public void setNomorKursi(int nomorKursi) {
        if (nomorKursi <= 0) {
            throw new IllegalArgumentException("Nomor kursi harus lebih dari 0.");
        }
        if (studio != null && nomorKursi > studio.getKapasitas()) {
            throw new IllegalArgumentException("Nomor kursi melebihi kapasitas studio.");
        }
        this.nomorKursi = nomorKursi;
    }

    public void setTotalHarga(double totalHarga) {
        if (totalHarga < 0) {
            throw new IllegalArgumentException("Total harga tidak boleh negatif.");
        }
        this.totalHarga = totalHarga;
    }

    public void setFilm(Film film) {
        if (film == null) {
            throw new IllegalArgumentException("Film pada tiket tidak boleh kosong.");
        }
        this.film = film;
    }

    public void setStudio(Studio studio) {
        if (studio == null) {
            throw new IllegalArgumentException("Studio pada tiket tidak boleh kosong.");
        }
        this.studio = studio;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        if (pelanggan == null) {
            throw new IllegalArgumentException("Pelanggan pada tiket tidak boleh kosong.");
        }
        this.pelanggan = pelanggan;
    }

    // ---------- Method perilaku ----------

    /** Menghitung harga tiket berdasarkan harga film. */
    public double hitungHarga() {
        if (film == null) {
            return 0;
        }
        return film.getHargaTiket();
    }

    /** Membatalkan tiket: kursi dilepas dan jumlah tiket pelanggan dikurangi. */
    public void batalkan() {
        if (dibatalkan) {
            System.out.println("Tiket " + kodeTiket + " sudah dibatalkan sebelumnya.");
            return;
        }
        this.dibatalkan = true;
        if (studio != null) {
            studio.lepasKursi(nomorKursi);
        }
        if (pelanggan != null) {
            pelanggan.kurangiJumlahTiket();
        }
    }

    /** Menampilkan bukti tiket ke console. */
    public void tampilkanInfo() {
        System.out.println("Kode Tiket  : " + kodeTiket);
        System.out.println("Film        : " + (film != null ? film.getJudul() : "-"));
        System.out.println("Studio      : " + (studio != null ? studio.getNama() : "-"));
        System.out.println("Pelanggan   : " + (pelanggan != null ? pelanggan.getNama() : "-"));
        System.out.println("Nomor Kursi : " + nomorKursi);
        System.out.println("Total Harga : Rp" + String.format("%,.0f", totalHarga));
        System.out.println("Status      : " + (dibatalkan ? "Dibatalkan" : "Aktif"));
    }
}
