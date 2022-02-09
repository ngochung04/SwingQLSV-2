/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingQLSV.DTO;

import java.time.Year;

/**
 *
 * @author NGOCHUNG
 */
public class SinhvienDTO {
    private int id;
    private String ten;
    private String gioitinh;
    private int namsinh;
    private float diemtoan;
    private float diemly;
    private float diemhoa;
    private float dtb;
    private String Hocluc;



    public SinhvienDTO(int id,String ten,String gioitinh ,int namsinh ,float diemtoan ,float diemly,float diemhoa ) {
        this.id =id;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.namsinh = namsinh;
        this.diemtoan = diemtoan;
        this.diemly = diemly;
        this.diemhoa = diemhoa;

        
    }

    public SinhvienDTO() {
    }
    public SinhvienDTO(SinhvienDTO sv) {
        this.id = sv.id;
        this.ten = sv.ten;
        this.gioitinh = sv.gioitinh;
        this.namsinh = sv.namsinh;
        this.diemtoan = sv.diemtoan;
        this.diemly = sv.diemly;
        this.diemhoa = sv.diemhoa;


    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
    
    public int getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(int namsinh) {
        this.namsinh = namsinh;
    }

    public float getDiemtoan() {
        return diemtoan;
    }

    public void setDiemtoan(float diemtoan) {
        this.diemtoan = diemtoan;
    }

    public float getDiemly() {
        return diemly;
    }

    public void setDiemly(float diemly) {
        this.diemly = diemly;
    }

    public float getDiemhoa() {
        return diemhoa;
    }

    public void setDiemhoa(float diemhoa) {
        this.diemhoa = diemhoa;
    }

    public float getDtb() {
        return dtb;
    }

    public void setDtb(float dtb) {
        this.dtb = dtb;
    }

    public String getHocluc() {
        return Hocluc;
    }

    public void setHocluc(String Hocluc) {
        this.Hocluc = Hocluc;
    }

    public float tinhDTB(){
        return (float) ((this.getDiemly() +this.getDiemtoan()+this.getDiemhoa())/3);
    }

    public int tinhtuoi(){
        int year = Year.now().getValue();
        int tuoi = year - this.namsinh;
        return tuoi;
    }

    public String xethocluc(){
        dtb = tinhDTB();
        if (dtb < 5) {
            return "Yeu";
        } else if (dtb >= 5 && dtb < 6.5) {
            return "Trung binh";
        } else if (dtb >= 6.5 && dtb < 8) {
            return "Kha";
        } else if (dtb >= 8 ) {
            return "Gioi";
        }
        return null;
    }
}
