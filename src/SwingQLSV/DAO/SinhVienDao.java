/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingQLSV.DAO;
import SwingQLSV.DTO.SinhvienDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
/**
 *
 * @author NGOCHUNG
 */
public class SinhVienDao {


    public ArrayList<SinhvienDTO> LaydlFromDB() {
        ArrayList<SinhvienDTO> listsv = new ArrayList<SinhvienDTO>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/qlsv?useUnicode=true&characterEncoding=utf-8", "root", "");
//here sonoo is database name, root is username and password  
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select id,hovaten,gioitinh,namsinh,diemtoan,diemly,diemhoa,dtb,hocluc from sinhvien where trangthai != 0 or trangthai <=> null");
            while (rs.next()) {
                SinhvienDTO sv = new SinhvienDTO();
                sv.setId(rs.getInt(1));
                sv.setTen(rs.getString(2));
                sv.setGioitinh(rs.getString(3));
                sv.setNamsinh(rs.getInt(4));
                sv.setDiemtoan(rs.getFloat(5));
                sv.setDiemly(rs.getFloat(6));
                sv.setDiemhoa(rs.getFloat(7));
                sv.setDtb(rs.getFloat(8));
                sv.setHocluc(rs.getString(9));
                listsv.add(sv);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return listsv;
    }
    public void themsvDB(SinhvienDTO sv) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/qlsv?useUnicode=true&characterEncoding=utf-8", "root", "");
//here sonoo is database name, root is username and password 
            String query ="INSERT INTO sinhvien ( hovaten, gioitinh, namsinh, diemtoan, diemly, diemhoa, dtb, hocluc,khoaid) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,'1')";
            PreparedStatement stmt = con.prepareStatement(query);


                //stmt.setId();
                stmt.setString(1, sv.getTen());
                stmt.setString(2, sv.getGioitinh());
                stmt.setInt(3, sv.getNamsinh());
                stmt.setFloat(4, sv.getDiemtoan());
                stmt.setFloat(5, sv.getDiemly());
                stmt.setFloat(6, sv.getDiemhoa());
                stmt.setFloat(7, sv.tinhDTB());
                stmt.setString(8, sv.xethocluc());
                stmt.executeUpdate();
                stmt.close();
                con.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void Xoasv(SinhvienDTO sv) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/qlsv?useUnicode=true&characterEncoding=utf-8", "root", "");
//here sonoo is database name, root is username and password 
            String query ="UPDATE sinhvien SET trangthai = 0 WHERE id =?";
            PreparedStatement stmt = con.prepareStatement(query);


                //stmt.setId();
                stmt.setInt(1, sv.getId());
                stmt.executeUpdate();
                stmt.close();
                con.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void SuasvDB(SinhvienDTO sv) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/qlsv?useUnicode=true&characterEncoding=utf-8", "root", "");
//here sonoo is database name, root is username and password 
            String query ="Update sinhvien SET hovaten = ?, gioitinh= ?, namsinh= ?, diemtoan = ?, diemly = ?, diemhoa = ?, dtb = ?, hocluc = ? WHERE id =?";
            PreparedStatement stmt = con.prepareStatement(query);


                
                stmt.setString(1, sv.getTen());
                stmt.setString(2, sv.getGioitinh());
                stmt.setInt(3, sv.getNamsinh());
                stmt.setFloat(4, sv.getDiemtoan());
                stmt.setFloat(5, sv.getDiemly());
                stmt.setFloat(6, sv.getDiemhoa());
                stmt.setFloat(7, sv.tinhDTB());
                stmt.setString(8, sv.xethocluc());
                stmt.setInt(9, sv.getId());
                stmt.executeUpdate();
                stmt.close();
                con.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
