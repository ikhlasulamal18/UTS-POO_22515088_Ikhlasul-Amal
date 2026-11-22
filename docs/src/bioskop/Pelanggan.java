package bioskop;

/**
 * Class Pelanggan merepresentasikan pembeli tiket bioskop.
 */
public class Pelanggan {
    private String idPelanggan;
    private String nama;
    private String noHp;
    private int jumlahTiket;

    /** Konstruktor default. */
    public Pelanggan() {
        this.idPelanggan = "-";
        this.nama = "Tanpa Nama";
        this.noHp = "00000000";
        this.jumlahTiket = 0;
    }

    /** Konstruktor berparameter. Memakai setter agar validasi ikut berjalan. */
    public Pelanggan(String idPelanggan, String nama, String noHp) {
        setIdPelanggan(idPelanggan);
        setNama(nama);
        setNoHp(noHp);
        this.jumlahTiket = 0;
    }

    // ========== Getter ==========
    public String getIdPelanggan() {
        return idPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public int getJumlahTiket() {
        return jumlahTiket;
    }

    // ========== Setter dengan validasi ==========
    public void setIdPelanggan(String idPelanggan) {
        if (idPelanggan == null || idPelanggan.trim().isEmpty()) {
            throw new IllegalArgumentException("ID pelanggan tidak boleh kosong.");
        }
        this.idPelanggan = idPelanggan.trim();
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pelanggan tidak boleh kosong.");
        }
        this.nama = nama.trim();
    }

    public void setNoHp(String noHp) {
        // Nomor HP harus berupa 8 sampai 15 digit angka
        if (noHp == null || !noHp.trim().matches("\\d{8,15}")) {
            throw new IllegalArgumentException("Nomor HP harus berupa 8 sampai 15 digit angka.");
        }
        this.noHp = noHp.trim();
    }

    public void setJumlahTiket(int jumlahTiket) {
        if (jumlahTiket < 0) {
            throw new IllegalArgumentException("Jumlah tiket tidak boleh negatif.");
        }
        this.jumlahTiket = jumlahTiket;
    }

    // ---------- Method perilaku ----------

    /** Menampilkan informasi pelanggan ke console. */
    public void tampilkanInfo() {
        System.out.println("ID Pelanggan : " + idPelanggan);
        System.out.println("Nama         : " + nama);
        System.out.println("No HP        : " + noHp);
        System.out.println("Jumlah Tiket : " + jumlahTiket);
    }

    /** Menambah jumlah tiket aktif milik pelanggan sebanyak satu. */
    public void tambahJumlahTiket() {
        this.jumlahTiket++;
    }

    /** Mengurangi jumlah tiket (dipakai saat tiket dibatalkan). */
    public void kurangiJumlahTiket() {
        if (this.jumlahTiket > 0) {
            this.jumlahTiket--;
        }
    }

    /** Memeriksa apakah data identitas pelanggan terisi lengkap. */
    public boolean isDataLengkap() {
        return !idPelanggan.equals("-") && !nama.equals("Tanpa Nama");
    }
}
