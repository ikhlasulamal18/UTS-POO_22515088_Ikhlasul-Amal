package bioskop;

import java.util.Scanner;

/**
 * Class Main berisi menu console untuk sistem bioskop mini.
 * Seluruh input pengguna divalidasi agar program tidak crash.
 */
public class Main {
    private static Bioskop bioskop;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        bioskop = new Bioskop("Bioskop Mini Kendari", "Jl. Contoh No. 1, Kendari");
        siapkanStudioAwal();

        boolean lanjut = true;
        while (lanjut) {
            tampilkanMenu();
            int pilihan = bacaInt("Pilih menu (1-7): ");
            try {
                switch (pilihan) {
                    case 1:
                        menuFilm();
                        break;
                    case 2:
                        menuPelanggan();
                        break;
                    case 3:
                        menuBeliTiket();
                        break;
                    case 4:
                        menuCariFilm();
                        break;
                    case 5:
                        menuAdt();
                        break;
                    case 6:
                        bioskop.buatLaporan();
                        break;
                    case 7:
                        lanjut = false;
                        System.out.println("Terima kasih. Program selesai.");
                        break;
                    default:
                        System.out.println("Pilihan tidak tersedia. Pilih angka 1 sampai 7.");
                }
            } catch (IllegalArgumentException e) {
                // Pelanggaran validasi dari setter/konstruktor ditampilkan sebagai pesan
                System.out.println("Gagal: " + e.getMessage());
            }
        }
        scanner.close();
    }

    /** Menyiapkan tiga studio awal karena menu tidak menyediakan tambah studio. */
    private static void siapkanStudioAwal() {
        bioskop.tambahStudio(new Studio("S1", "Studio 1", 20));
        bioskop.tambahStudio(new Studio("S2", "Studio 2", 30));
        bioskop.tambahStudio(new Studio("S3", "Studio 3", 10));
    }

    public static void tampilkanMenu() {
        System.out.println();
        System.out.println("===== " + bioskop.getNamaBioskop().toUpperCase() + " =====");
        System.out.println("1. Data film (tambah / tampilkan)");
        System.out.println("2. Data pelanggan (tambah / tampilkan)");
        System.out.println("3. Beli tiket");
        System.out.println("4. Cari film berdasarkan judul");
        System.out.println("5. Operasi ADT (Sorted List film)");
        System.out.println("6. Laporan ringkasan");
        System.out.println("7. Keluar");
    }

    // ---------- Pembacaan input yang aman ----------

    /** Membaca teks yang tidak boleh kosong. Mengulang sampai valid. */
    public static String bacaString(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                System.out.println();
                System.out.println("Input berakhir. Program berhenti.");
                System.exit(0);
            }
            String teks = scanner.nextLine().trim();
            if (!teks.isEmpty()) {
                return teks;
            }
            System.out.println("Input tidak boleh kosong.");
        }
    }

    /** Membaca bilangan bulat. Mengulang jika input bukan angka. */
    public static int bacaInt(String prompt) {
        while (true) {
            String teks = bacaString(prompt);
            try {
                return Integer.parseInt(teks);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat.");
            }
        }
    }

    /** Membaca bilangan desimal. Mengulang jika input bukan angka. */
    public static double bacaDouble(String prompt) {
        while (true) {
            String teks = bacaString(prompt).replace(',', '.');
            try {
                return Double.parseDouble(teks);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    // ---------- Menu 1: film ----------

    private static void menuFilm() {
        System.out.println("\n--- Data Film ---");
        System.out.println("1. Tambah film");
        System.out.println("2. Tampilkan semua film");
        int pilihan = bacaInt("Pilih: ");
        if (pilihan == 1) {
            tambahFilm();
        } else if (pilihan == 2) {
            bioskop.getDaftarFilm().tampilkan();
        } else {
            System.out.println("Pilihan tidak tersedia.");
        }
    }

    private static void tambahFilm() {
        String kode = bacaString("Kode film     : ");
        String judul = bacaString("Judul film    : ");
        int durasi = bacaInt("Durasi (menit) : ");
        double harga = bacaDouble("Harga tiket    : ");
        Film film = new Film(kode, judul, durasi, harga); // validasi dilakukan setter
        if (bioskop.tambahFilm(film)) {
            System.out.println("Film \"" + film.getJudul() + "\" berhasil ditambahkan.");
        }
    }

    // ---------- Menu 2: pelanggan ----------

    private static void menuPelanggan() {
        System.out.println("\n--- Data Pelanggan ---");
        System.out.println("1. Tambah pelanggan");
        System.out.println("2. Tampilkan semua pelanggan");
        int pilihan = bacaInt("Pilih: ");
        if (pilihan == 1) {
            String id = bacaString("ID pelanggan : ");
            String nama = bacaString("Nama         : ");
            String noHp = bacaString("No HP        : ");
            Pelanggan pelanggan = new Pelanggan(id, nama, noHp);
            if (bioskop.tambahPelanggan(pelanggan)) {
                System.out.println("Pelanggan \"" + pelanggan.getNama() + "\" berhasil ditambahkan.");
            }
        } else if (pilihan == 2) {
            tampilkanSemuaPelanggan();
        } else {
            System.out.println("Pilihan tidak tersedia.");
        }
    }

    private static void tampilkanSemuaPelanggan() {
        if (bioskop.getDaftarPelanggan().isEmpty()) {
            System.out.println("Belum ada data pelanggan.");
            return;
        }
        for (Pelanggan p : bioskop.getDaftarPelanggan()) {
            p.tampilkanInfo();
            System.out.println("------------------------------");
        }
    }

    // ---------- Menu 3: beli tiket ----------

    private static void menuBeliTiket() {
        System.out.println("\n--- Beli Tiket ---");
        if (bioskop.getDaftarFilm().isEmpty()) {
            System.out.println("Belum ada film. Tambahkan film terlebih dahulu (menu 1).");
            return;
        }
        if (bioskop.getDaftarPelanggan().isEmpty()) {
            System.out.println("Belum ada pelanggan. Tambahkan pelanggan terlebih dahulu (menu 2).");
            return;
        }

        bioskop.getDaftarFilm().tampilkan();
        Film film = bioskop.cariFilm(bacaString("Judul film yang dipilih: "));
        if (film == null) {
            System.out.println("Film tidak ditemukan.");
            return;
        }

        tampilkanSemuaPelanggan();
        Pelanggan pelanggan = bioskop.cariPelanggan(bacaString("ID pelanggan: "));
        if (pelanggan == null) {
            System.out.println("Pelanggan tidak ditemukan.");
            return;
        }

        for (Studio s : bioskop.getDaftarStudio()) {
            System.out.println(s.getKodeStudio() + " - " + s.getNama()
                    + " (sisa kursi " + s.hitungSisaKursi() + "/" + s.getKapasitas() + ")");
        }
        Studio studio = bioskop.cariStudio(bacaString("Kode studio: "));
        if (studio == null) {
            System.out.println("Studio tidak ditemukan.");
            return;
        }

        studio.tampilkanDenahKursi();
        int nomorKursi = bacaInt("Nomor kursi: ");

        String kodeTiket = String.format("TKT-%03d", bioskop.getDaftarTiket().size() + 1);
        Tiket tiket = bioskop.beliTiket(kodeTiket, film, studio, pelanggan, nomorKursi);
        if (tiket != null) {
            System.out.println("Tiket berhasil dibuat:");
            tiket.tampilkanInfo();
        }
    }

    // ---------- Menu 4: cari film ----------

    private static void menuCariFilm() {
        System.out.println("\n--- Cari Film ---");
        Film film = bioskop.cariFilm(bacaString("Judul film: "));
        if (film == null) {
            System.out.println("Film tidak ditemukan.");
        } else {
            film.tampilkanInfo();
        }
    }

    // ---------- Menu 5: operasi ADT ----------

    private static void menuAdt() {
        DaftarFilmTerurut adt = bioskop.getDaftarFilm();
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n--- Operasi ADT Sorted List Film ---");
            System.out.println("1. insert (tambah film, tetap terurut)");
            System.out.println("2. remove (hapus film berdasarkan judul)");
            System.out.println("3. get(index)");
            System.out.println("4. tampilkan isi ADT");
            System.out.println("5. size");
            System.out.println("6. cek penuh / kosong");
            System.out.println("0. Kembali");
            int pilihan = bacaInt("Pilih: ");
            try {
                switch (pilihan) {
                    case 1:
                        tambahFilm();
                        break;
                    case 2:
                        String judul = bacaString("Judul film yang dihapus: ");
                        if (adt.remove(judul)) {
                            System.out.println("Film berhasil dihapus.");
                        }
                        break;
                    case 3:
                        int indeks = bacaInt("Indeks (mulai dari 0): ");
                        Film film = adt.get(indeks);
                        if (film != null) {
                            film.tampilkanInfo();
                        }
                        break;
                    case 4:
                        adt.tampilkan();
                        break;
                    case 5:
                        System.out.println("Jumlah film: " + adt.size() + " dari " + adt.getKapasitas());
                        break;
                    case 6:
                        System.out.println("Penuh : " + (adt.isFull() ? "ya" : "tidak"));
                        System.out.println("Kosong: " + (adt.isEmpty() ? "ya" : "tidak"));
                        break;
                    case 0:
                        kembali = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak tersedia.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Gagal: " + e.getMessage());
            }
        }
    }
}
