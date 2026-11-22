package bioskop;

/**
 * Class Film merepresentasikan film yang ditayangkan di bioskop.
 * Semua atribut bersifat private dan hanya diakses lewat getter/setter
 * yang memuat validasi.
 */
public class Film {
    private String kodeFilm;
    private String judul;
    private int durasiMenit;
    private double hargaTiket;
    private boolean sedangTayang;

    /** Konstruktor default: mengisi nilai awal yang valid. */
    public Film() {
        this.kodeFilm = "-";
        this.judul = "Tanpa Judul";
        this.durasiMenit = 1;
        this.hargaTiket = 1;
        this.sedangTayang = false;
    }

    /**
     * Konstruktor berparameter. Memakai setter agar validasi ikut berjalan.
     * Film yang baru dibuat otomatis berstatus sedang tayang.
     */
    public Film(String kodeFilm, String judul, int durasiMenit, double hargaTiket) {
        setKodeFilm(kodeFilm);
        setJudul(judul);
        setDurasiMenit(durasiMenit);
        setHargaTiket(hargaTiket);
        this.sedangTayang = true;
    }

    // ---------- Getter ----------
    public String getKodeFilm() {
        return kodeFilm;
    }

    public String getJudul() {
        return judul;
    }

    public int getDurasiMenit() {
        return durasiMenit;
    }

    public double getHargaTiket() {
        return hargaTiket;
    }

    public boolean isSedangTayang() {
        return sedangTayang;
    }

    // ---------- Setter dengan validasi ----------
    public void setKodeFilm(String kodeFilm) {
        if (kodeFilm == null || kodeFilm.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode film tidak boleh kosong.");
        }
        this.kodeFilm = kodeFilm.trim();
    }

    public void setJudul(String judul) {
        if (judul == null || judul.trim().isEmpty()) {
            throw new IllegalArgumentException("Judul film tidak boleh kosong.");
        }
        this.judul = judul.trim();
    }

    public void setDurasiMenit(int durasiMenit) {
        if (durasiMenit <= 0) {
            throw new IllegalArgumentException("Durasi film harus lebih dari 0 menit.");
        }
        this.durasiMenit = durasiMenit;
    }

    public void setHargaTiket(double hargaTiket) {
        if (hargaTiket <= 0) {
            throw new IllegalArgumentException("Harga tiket harus lebih dari 0.");
        }
        this.hargaTiket = hargaTiket;
    }

    public void setSedangTayang(boolean sedangTayang) {
        this.sedangTayang = sedangTayang;
    }

    // ---------- Method perilaku ----------

    /** Menampilkan informasi film ke console. */
    public void tampilkanInfo() {
        System.out.println("Kode Film   : " + kodeFilm);
        System.out.println("Judul       : " + judul);
        System.out.println("Durasi      : " + durasiMenit + " menit ("
                + String.format("%.1f", hitungDurasiJam()) + " jam)");
        System.out.println("Harga Tiket : Rp" + String.format("%,.0f", hargaTiket));
        System.out.println("Status      : " + (sedangTayang ? "Sedang tayang" : "Tidak tayang"));
    }

    /** Membalik status tayang (tayang menjadi tidak tayang, dan sebaliknya). */
    public void ubahStatusTayang() {
        this.sedangTayang = !this.sedangTayang;
    }

    /** Menghitung durasi film dalam satuan jam. */
    public double hitungDurasiJam() {
        return durasiMenit / 60.0;
    }
}
