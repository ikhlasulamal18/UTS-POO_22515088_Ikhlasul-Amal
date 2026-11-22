package bioskop;

import java.util.ArrayList;

/**
 * ADT Sorted List untuk menyimpan Film yang selalu terurut berdasarkan judul
 * (tanpa membedakan huruf besar/kecil). Data disimpan dalam ArrayList
 * internal yang bersifat private, sedangkan antarmukanya berupa operasi publik.
 * Kapasitas dibatasi sehingga kondisi penuh dan kosong dapat ditangani
 * tanpa membuat program berhenti.
 */
public class DaftarFilmTerurut {
    private static final int KAPASITAS_DEFAULT = 20;

    private ArrayList<Film> data;
    private int kapasitas;

    /** Konstruktor default dengan kapasitas 20. */
    public DaftarFilmTerurut() {
        this(KAPASITAS_DEFAULT);
    }

    /** Konstruktor dengan kapasitas tertentu (harus lebih dari 0). */
    public DaftarFilmTerurut(int kapasitas) {
        if (kapasitas <= 0) {
            throw new IllegalArgumentException("Kapasitas daftar film harus lebih dari 0.");
        }
        this.kapasitas = kapasitas;
        this.data = new ArrayList<>();
    }

    public int getKapasitas() {
        return kapasitas;
    }

    /**
     * Menyisipkan film pada posisi yang menjaga urutan judul.
     * Prasyarat: daftar tidak penuh dan judul belum ada.
     * Mengembalikan true jika berhasil, false jika gagal (disertai pesan).
     */
    public boolean insert(Film film) {
        if (film == null) {
            System.out.println("Gagal: film tidak boleh kosong.");
            return false;
        }
        if (isFull()) {
            System.out.println("Gagal: daftar film penuh (kapasitas " + kapasitas + ").");
            return false;
        }
        if (search(film.getJudul()) != -1) {
            System.out.println("Gagal: film dengan judul \"" + film.getJudul() + "\" sudah ada.");
            return false;
        }
        // Cari posisi pertama yang judulnya lebih besar dari judul baru
        int posisi = 0;
        while (posisi < data.size()
                && data.get(posisi).getJudul().compareToIgnoreCase(film.getJudul()) < 0) {
            posisi++;
        }
        data.add(posisi, film); // elemen sesudah posisi otomatis bergeser
        return true;
    }

    /**
     * Menghapus film berdasarkan judul.
     * Prasyarat: daftar tidak kosong dan judul ditemukan.
     */
    public boolean remove(String judul) {
        if (isEmpty()) {
            System.out.println("Gagal: daftar film kosong.");
            return false;
        }
        int indeks = search(judul);
        if (indeks == -1) {
            System.out.println("Gagal: film \"" + judul + "\" tidak ditemukan.");
            return false;
        }
        data.remove(indeks);
        return true;
    }

    /**
     * Mencari judul dengan binary search (data sudah terurut).
     * Mengembalikan indeks, atau -1 jika tidak ditemukan.
     */
    public int search(String judul) {
        if (judul == null) {
            return -1;
        }
        int kiri = 0;
        int kanan = data.size() - 1;
        while (kiri <= kanan) {
            int tengah = (kiri + kanan) / 2;
            int hasil = data.get(tengah).getJudul().compareToIgnoreCase(judul.trim());
            if (hasil == 0) {
                return tengah;
            } else if (hasil < 0) {
                kiri = tengah + 1;
            } else {
                kanan = tengah - 1;
            }
        }
        return -1;
    }

    /**
     * Mengambil film pada indeks tertentu (mulai dari 0).
     * Mengembalikan null disertai pesan jika daftar kosong atau indeks di luar rentang.
     */
    public Film get(int index) {
        if (isEmpty()) {
            System.out.println("Gagal: daftar film kosong.");
            return null;
        }
        if (index < 0 || index >= data.size()) {
            System.out.println("Gagal: indeks harus antara 0 sampai " + (data.size() - 1) + ".");
            return null;
        }
        return data.get(index);
    }

    /** Menampilkan seluruh film secara terurut. */
    public void tampilkan() {
        if (isEmpty()) {
            System.out.println("Daftar film masih kosong.");
            return;
        }
        System.out.println("Daftar film (" + data.size() + "/" + kapasitas + "), urut berdasarkan judul:");
        for (int i = 0; i < data.size(); i++) {
            System.out.println("[" + i + "]");
            data.get(i).tampilkanInfo();
            System.out.println("------------------------------");
        }
    }

    /** Jumlah film saat ini. */
    public int size() {
        return data.size();
    }

    /** True jika jumlah film sama dengan kapasitas. */
    public boolean isFull() {
        return data.size() >= kapasitas;
    }

    /** True jika belum ada film. */
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
