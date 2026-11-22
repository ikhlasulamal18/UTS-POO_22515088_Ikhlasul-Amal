package bioskop;

/**
 * Class Studio merepresentasikan ruang pemutaran film.
 * Status setiap kursi disimpan dalam array boolean (true = sudah dipesan).
 * Kapasitas studio terbatas, sehingga kursi di luar rentang ditolak.
 */
public class Studio {
    private static final int KAPASITAS_MAKSIMAL = 200;
    private static final int KURSI_PER_BARIS = 10;

    private String kodeStudio;
    private String nama;
    private int kapasitas;
    private boolean[] kursiTerpesan;

    /** Konstruktor default: studio berkapasitas 10 kursi. */
    public Studio() {
        this.kodeStudio = "-";
        this.nama = "Studio";
        this.kapasitas = 10;
        this.kursiTerpesan = new boolean[10];
    }

    /** Konstruktor berparameter. Memakai setter agar validasi ikut berjalan. */
    public Studio(String kodeStudio, String nama, int kapasitas) {
        setKodeStudio(kodeStudio);
        setNama(nama);
        setKapasitas(kapasitas);
    }

    // ---------- Getter ----------
    public String getKodeStudio() {
        return kodeStudio;
    }

    public String getNama() {
        return nama;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    /** Mengembalikan salinan array agar status kursi tidak diubah dari luar. */
    public boolean[] getKursiTerpesan() {
        return kursiTerpesan.clone();
    }

    // ---------- Setter dengan validasi ----------
    public void setKodeStudio(String kodeStudio) {
        if (kodeStudio == null || kodeStudio.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode studio tidak boleh kosong.");
        }
        this.kodeStudio = kodeStudio.trim();
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama studio tidak boleh kosong.");
        }
        this.nama = nama.trim();
    }

    /** Mengubah kapasitas. Seluruh kursi direset menjadi kosong. */
    public void setKapasitas(int kapasitas) {
        if (kapasitas <= 0 || kapasitas > KAPASITAS_MAKSIMAL) {
            throw new IllegalArgumentException(
                    "Kapasitas studio harus antara 1 sampai " + KAPASITAS_MAKSIMAL + ".");
        }
        this.kapasitas = kapasitas;
        this.kursiTerpesan = new boolean[kapasitas];
    }

    // ---------- Method perilaku ----------

    /** Memeriksa apakah nomor kursi berada dalam rentang 1..kapasitas. */
    private boolean isNomorValid(int nomor) {
        return nomor >= 1 && nomor <= kapasitas;
    }

    /** Mengembalikan true jika nomor valid dan kursi belum dipesan. */
    public boolean isKursiTersedia(int nomor) {
        return isNomorValid(nomor) && !kursiTerpesan[nomor - 1];
    }

    /**
     * Memesan kursi. Aturan bisnis: kursi yang sudah dipesan tidak bisa
     * dipesan lagi. Pelanggaran aturan menampilkan pesan dan mengembalikan
     * false tanpa membuat program berhenti.
     */
    public boolean pesanKursi(int nomor) {
        if (!isNomorValid(nomor)) {
            System.out.println("Gagal: nomor kursi harus antara 1 sampai " + kapasitas + ".");
            return false;
        }
        if (kursiTerpesan[nomor - 1]) {
            System.out.println("Gagal: kursi nomor " + nomor + " sudah dipesan.");
            return false;
        }
        kursiTerpesan[nomor - 1] = true;
        return true;
    }

    /** Melepas kursi (dipakai saat tiket dibatalkan). */
    public void lepasKursi(int nomor) {
        if (isNomorValid(nomor)) {
            kursiTerpesan[nomor - 1] = false;
        }
    }

    /** Menghitung jumlah kursi yang masih kosong. */
    public int hitungSisaKursi() {
        int sisa = 0;
        for (boolean terpesan : kursiTerpesan) {
            if (!terpesan) {
                sisa++;
            }
        }
        return sisa;
    }

    /** Menampilkan denah kursi. XX berarti sudah dipesan. */
    public void tampilkanDenahKursi() {
        System.out.println("Denah " + nama + " (XX = sudah dipesan)");
        for (int i = 0; i < kapasitas; i++) {
            String label = kursiTerpesan[i] ? "XX" : String.format("%02d", i + 1);
            System.out.print("[" + label + "] ");
            if ((i + 1) % KURSI_PER_BARIS == 0) {
                System.out.println();
            }
        }
        if (kapasitas % KURSI_PER_BARIS != 0) {
            System.out.println();
        }
    }
}
