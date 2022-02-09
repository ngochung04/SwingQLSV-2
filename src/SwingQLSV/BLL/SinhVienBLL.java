/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingQLSV.BLL;

import SwingQLSV.DAO.SinhVienDao;
import SwingQLSV.DTO.SinhvienDTO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NGOCHUNG
 */
public class SinhVienBLL {
    private ArrayList<SinhvienDTO> listsv;
    private SinhVienDao svDao;
    public SinhVienBLL() {
        listsv = new ArrayList<SinhvienDTO>();
        svDao = new SinhVienDao();
    }

    public void themsinhvien(SinhvienDTO sv) {
        svDao.themsvDB(sv);
        
    }
    public int getmaxid() {
        int id = 0;
        for (SinhvienDTO sinhvien : listsv) {
            if (id <= sinhvien.getId()) {
                id = sinhvien.getId() + 1;
            }
        }
        return id;
    }

    public void Xoasinhvien(SinhvienDTO sv) {
        svDao.Xoasv(sv);
    }

    public void Suasinhvien(SinhvienDTO svm) {
        SinhvienDTO sv = new SinhvienDTO();
        sv.setId(svm.getId());
        sv.setTen(svm.getTen());
        sv.setGioitinh(svm.getGioitinh());
        sv.setNamsinh(svm.getNamsinh());
        sv.setDiemtoan((float) svm.getDiemtoan());
        sv.setDiemly((float) svm.getDiemly());
        sv.setDiemhoa((float) svm.getDiemhoa());
        sv.setDtb((float) svm.getDtb());
        svDao.SuasvDB(svm);
        
    }

    static <T> List<List<T>> chopped(List<T> list, final int L) {
        List<List<T>> parts = new ArrayList<List<T>>();
        final int N = list.size();
        for (int i = 0; i < N; i += L) {
            parts.add(new ArrayList<T>(
                    list.subList(i, Math.min(N, i + L)))
            );
        }
        return parts;
    }

    public ArrayList<SinhvienDTO> ghifile(String fileSavename, JTable jtb) throws IOException {
        DefaultTableModel model = (DefaultTableModel) jtb.getModel();
        FileWriter writer = new FileWriter(fileSavename);
        BufferedWriter bw = new BufferedWriter(writer);
        List<String> numdata = new ArrayList<String>();
        for (int count = 0; count < model.getRowCount(); count++) {
            numdata.add(model.getValueAt(count, 0).toString());
            numdata.add(model.getValueAt(count, 1).toString());
            numdata.add(model.getValueAt(count, 2).toString());
            numdata.add(model.getValueAt(count, 3).toString());
            numdata.add(model.getValueAt(count, 4).toString());
            numdata.add(model.getValueAt(count, 5).toString());
            numdata.add(model.getValueAt(count, 6).toString());
            numdata.add(model.getValueAt(count, 7).toString());
            numdata.add(model.getValueAt(count, 8).toString());

        }
        List<List<String>> line = chopped(numdata, 9);
        for (int i = 0; i < line.size(); i++) {
            writer.write(line.get(i).get(0)+","+line.get(i).get(1)+","+line.get(i).get(2)+","+line.get(i).get(3)+","+line.get(i).get(4)+","+line.get(i).get(5)
                    +","+line.get(i).get(6)+","+line.get(i).get(7)+","+line.get(i).get(8)+"\n");
            
        }


        bw.close();
        writer.close();
        return this.listsv;
    }
    public ArrayList<SinhvienDTO> sapxeptheodtb(){
        Collections.sort(this.listsv, new Comparator<SinhvienDTO>() {
            @Override
            public int compare(SinhvienDTO o1, SinhvienDTO o2) {
                return (int) Double.compare(-o1.getDtb(), -o2.getDtb());
            }
        });
        return this.listsv;
    }
    public void sxdtb(JTable jtb) {
        ArrayList<SinhvienDTO> arr = new ArrayList<SinhvienDTO>();
        arr = sapxeptheodtb();
        this.LayDSSVFromDB();
        DefaultTableModel model = (DefaultTableModel) jtb.getModel();
        model.setRowCount(0);
        Object rowdata[] = new Object[9];
        for (int i = 0; i < arr.size(); i++) {

            rowdata[0] = arr.get(i).getId();
            rowdata[1] = arr.get(i).getTen();
            rowdata[2] = arr.get(i).getGioitinh();
            rowdata[3] = arr.get(i).tinhtuoi();
            rowdata[4] = arr.get(i).getDiemtoan();
            rowdata[5] = arr.get(i).getDiemly();
            rowdata[6] = arr.get(i).getDiemhoa();
            rowdata[7] = arr.get(i).tinhDTB();
            rowdata[8] = arr.get(i).xethocluc();
            model.addRow(rowdata);
        } 

    }
    public ArrayList<SinhvienDTO> sapxeptheoten(){
        Collections.sort(this.listsv, new Comparator<SinhvienDTO>() {
            @Override
            public int compare(SinhvienDTO o1, SinhvienDTO o2) {
                return o1.getTen().compareToIgnoreCase(o2.getTen());
            }
        });
        return this.listsv;
    }
    public void sxten(JTable jtb) {
        ArrayList<SinhvienDTO> arr = new ArrayList<SinhvienDTO>();
        arr = sapxeptheoten();
        this.LayDSSVFromDB();
        DefaultTableModel model = (DefaultTableModel) jtb.getModel();
        model.setRowCount(0);
        Object rowdata[] = new Object[9];
        for (int i = 0; i < arr.size(); i++) {

            rowdata[0] = arr.get(i).getId();
            rowdata[1] = arr.get(i).getTen();
            rowdata[2] = arr.get(i).getGioitinh();
            rowdata[3] = arr.get(i).tinhtuoi();
            rowdata[4] = arr.get(i).getDiemtoan();
            rowdata[5] = arr.get(i).getDiemly();
            rowdata[6] = arr.get(i).getDiemhoa();
            rowdata[7] = arr.get(i).tinhDTB();
            rowdata[8] = arr.get(i).xethocluc();
            model.addRow(rowdata);
        } 

    }
    public ArrayList<SinhvienDTO> Timkiem(String name,JTable jtb){
        DefaultTableModel model = (DefaultTableModel) jtb.getModel();
        model.setRowCount(0);
        Object rowdata[] = new Object[9];
        for (SinhvienDTO sinhvienDTO : listsv) {
            if (sinhvienDTO.getTen().toLowerCase().equals(name.toLowerCase())) {               
                rowdata[0] = sinhvienDTO.getId();
                rowdata[1] = sinhvienDTO.getTen();
                rowdata[2] = sinhvienDTO.getGioitinh();
                rowdata[3] = sinhvienDTO.tinhtuoi();
                rowdata[4] = sinhvienDTO.getDiemtoan();
                rowdata[5] = sinhvienDTO.getDiemly();
                rowdata[6] = sinhvienDTO.getDiemhoa();
                rowdata[7] = sinhvienDTO.tinhDTB();
                rowdata[8] = sinhvienDTO.xethocluc();
                model.addRow(rowdata);
            }
        }
        return this.listsv;
    }
    public int Laynamsinh(int tuoi) {
        int year = Year.now().getValue();
        int namsinh = year - tuoi;
        return namsinh;
    }

    public ArrayList<SinhvienDTO> LayDSSV(String filename) {
        listsv = new ArrayList<SinhvienDTO>();
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) //returns a Boolean value  
            {
                String[] svarr = line.split(splitBy);    // use comma as separator  
                SinhvienDTO sv = new SinhvienDTO();
                sv.setId(Integer.parseInt(svarr[0]));
                sv.setTen(svarr[1]);
                sv.setGioitinh(svarr[2]);
                sv.setNamsinh(Laynamsinh(Integer.parseInt(svarr[3])));
                sv.setDiemtoan(Float.parseFloat(svarr[4]));
                sv.setDiemly(Float.parseFloat(svarr[5]));
                sv.setDiemhoa(Float.parseFloat(svarr[6]));
                sv.setDtb(Float.parseFloat(svarr[7]));
                sv.setHocluc(svarr[8]);
                this.listsv.add(sv);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.listsv;
    }

    public void HienthiDSSV(JTable jtb) {
        this.LayDSSVFromDB();
        DefaultTableModel model = (DefaultTableModel) jtb.getModel();
        model.setRowCount(0);
        Object rowdata[] = new Object[9];
        for (int i = 0; i < listsv.size(); i++) {

            rowdata[0] = listsv.get(i).getId();
            rowdata[1] = listsv.get(i).getTen();
            rowdata[2] = listsv.get(i).getGioitinh();
            rowdata[3] = listsv.get(i).tinhtuoi();
            rowdata[4] = listsv.get(i).getDiemtoan();
            rowdata[5] = listsv.get(i).getDiemly();
            rowdata[6] = listsv.get(i).getDiemhoa();
            rowdata[7] = listsv.get(i).tinhDTB();
            rowdata[8] = listsv.get(i).xethocluc();
            model.addRow(rowdata);
        } 

    }
    public ArrayList<SinhvienDTO> LayDSSVFromDB() {
        
        this.listsv= svDao.LaydlFromDB();
        return this.listsv;
    }
}
