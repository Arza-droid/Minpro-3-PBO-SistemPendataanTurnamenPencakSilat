package controller;

import model.*;
import java.util.ArrayList;

public class DaftarControl {
    private ArrayList<Peserta> daftarPeserta;
    private ArrayList<Pertandingan> daftarPertandingan;
    private int idPesertaCounter;
    private int idPertandinganCounter;

    public DaftarControl() {
        daftarPeserta = new ArrayList<>();
        daftarPertandingan = new ArrayList<>();
        idPesertaCounter = 1;
        idPertandinganCounter = 1;
        isiDataAwal();
    }

    private void isiDataAwal() {
        Peserta p1 = new PesertaTanding(idPesertaCounter++, "Budi Santoso", "Perguruan Merpati Putih", "Kelas A (45-50 kg)");
        Peserta p2 = new PesertaTanding(idPesertaCounter++, "Andi Wijaya", "Perguruan Tapak Suci", "Kelas A (45-50 kg)");
        Peserta p3 = new PesertaSeni(idPesertaCounter++, "Siti Aminah", "Perguruan Cimande", "Tunggal");

        daftarPeserta.add(p1);
        daftarPeserta.add(p2);
        daftarPeserta.add(p3);

        Pertandingan pt1 = new Pertandingan(idPertandinganCounter++, p1, p2, "Tanding", "Belum Bertanding");
        daftarPertandingan.add(pt1);
    }

    public Peserta cariPesertaById(int id) {
        for (Peserta peserta : daftarPeserta) {
            if (peserta.getId() == id) {
                return peserta;
            }
        }
        return null;
    }

    public Pertandingan cariPertandinganById(int id) {
        for (Pertandingan pertandingan : daftarPertandingan) {
            if (pertandingan.getId() == id) {
                return pertandingan;
            }
        }
        return null;
    }


    public void tambahPesertaTanding(String nama, String asalPerguruan, String kelasBerat) {
        Peserta peserta = new PesertaTanding(idPesertaCounter++, nama, asalPerguruan, kelasBerat);
        daftarPeserta.add(peserta);
    }

    public void tambahPesertaSeni(String nama, String asalPerguruan, String jenisSeni) {
        Peserta peserta = new PesertaSeni(idPesertaCounter++, nama, asalPerguruan, jenisSeni);
        daftarPeserta.add(peserta);
    }

    public ArrayList<Peserta> getDaftarPeserta() {
        return daftarPeserta;
    }

    public boolean ubahPeserta(int id, String namaBaru, String asalPerguruanBaru, String atributKhusus) {
        Peserta peserta = cariPesertaById(id);
        if (peserta == null) return false;

        peserta.setNama(namaBaru);
        peserta.setAsalPerguruan(asalPerguruanBaru);

        
        if (peserta instanceof PesertaTanding) {
            ((PesertaTanding) peserta).setKelasBerat(atributKhusus);
        } else if (peserta instanceof PesertaSeni) {
            ((PesertaSeni) peserta).setJenisSeni(atributKhusus);
        }

        return true;
    }

    public boolean hapusPeserta(int id) {
        Peserta peserta = cariPesertaById(id);
        if (peserta == null) return false;
        daftarPeserta.remove(peserta);
        return true;
    }


    public boolean tambahPertandingan(int idPeserta1, int idPeserta2, String kategori, String hasil) {
        Peserta p1 = cariPesertaById(idPeserta1);
        Peserta p2 = cariPesertaById(idPeserta2);
        if (p1 == null || p2 == null) return false;

        Pertandingan pertandingan = new Pertandingan(idPertandinganCounter++, p1, p2, kategori, hasil);
        daftarPertandingan.add(pertandingan);
        return true;
    }


    public boolean tambahPertandingan(int idPeserta1, String kategori, String hasil) {
        Peserta p1 = cariPesertaById(idPeserta1);
        if (p1 == null) return false;

        Pertandingan pertandingan = new Pertandingan(idPertandinganCounter++, p1, null, kategori, hasil);
        daftarPertandingan.add(pertandingan);
        return true;
    }

    public ArrayList<Pertandingan> getDaftarPertandingan() {
        return daftarPertandingan;
    }

    public boolean ubahPertandingan(int id, int idPeserta1, int idPeserta2, String kategori, String hasil) {
        Pertandingan pertandingan = cariPertandinganById(id);
        if (pertandingan == null) return false;

        Peserta p1 = cariPesertaById(idPeserta1);
        Peserta p2 = (idPeserta2 != -1) ? cariPesertaById(idPeserta2) : null;

        if (p1 == null || (idPeserta2 != -1 && p2 == null)) return false;

        pertandingan.setPeserta1(p1);
        pertandingan.setPeserta2(p2);
        pertandingan.setKategori(kategori);
        pertandingan.setHasil(hasil);
        return true;
    }

    public boolean hapusPertandingan(int id) {
        Pertandingan pertandingan = cariPertandinganById(id);
        if (pertandingan == null) return false;
        daftarPertandingan.remove(pertandingan);
        return true;
    }
}