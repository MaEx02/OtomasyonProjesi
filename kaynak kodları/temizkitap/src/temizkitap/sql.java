/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temizkitap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shaco
 */
public class sql {
     private int port = 3306;
private String ad="root";
private String parola ="";
private String db_ismi="kitap_satis";
private String host ="localhost";
private Connection con =null;
private Statement statment =null;
public sql(){
    String url ="jdbc:mysql://"+host+":"+port+"/"+db_ismi;
    try{
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException ex) {
        System.out.println("Driver yok");
    }
    try {
     con=(Connection) DriverManager.getConnection(url, ad, parola);
        System.out.println("Başarılı");
    } catch (SQLException ex) {
        System.out.println("Bağlantı başarısız");
    }
}
    public void musteriekle(String adres,String adsoyad,String sifre,String eposta){
    try {
        statment=con.createStatement();
        // INSERT INTO `hasta`(`tc`, `adsoyad`, `sifre`, `cinsyet`, `yas`, `eposta`, `dogumyeri`) VALUES (12,22,33,44,55,66,123)
String sorgu;
sorgu="INSERT INTO musteri (adres,adsoyad,sifre,eposta) VALUES ";
String tamamla ="('"+adres+"','"+adsoyad+"','"+sifre+"','"+eposta+"')";
sorgu=sorgu+tamamla;
        statment.executeUpdate(sorgu);
    } catch (SQLException ex) {
        Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
    public String yoneticisifre(String id){
      String sifre="z";  
       String sorgu ="Select sifre FROM yonetici WHERE id='"+id+"'";
    try {
        statment=con.createStatement();
       
        ResultSet rs = statment.executeQuery(sorgu);
      while(rs.next()){
          sifre = rs.getString("sifre");
      }
         
    } catch (SQLException ex) {
        Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
    }
   return sifre;
    }
    public String musteriadres(String eposta){
        String sifre="z";
        //SELECT sifre FROM `musteri` WHERE eposta='akifergunduz27@gmail.com'
    String sorgu ="Select adres FROM musteri WHERE eposta='"+eposta+"'";
    try {
        statment=con.createStatement();
       
        ResultSet rs = statment.executeQuery(sorgu);
      while(rs.next()){
          sifre = rs.getString("adres");
      }
         
    } catch (SQLException ex) {
        Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
    }
   return sifre;
    }
    public String musterisifre(String eposta){
        String sifre="z";
        //SELECT sifre FROM `musteri` WHERE eposta='akifergunduz27@gmail.com'
    String sorgu ="Select sifre FROM musteri WHERE eposta='"+eposta+"'";
    try {
        statment=con.createStatement();
       
        ResultSet rs = statment.executeQuery(sorgu);
      while(rs.next()){
          sifre = rs.getString("sifre");
      }
         
    } catch (SQLException ex) {
        Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
    }
   return sifre;
    }
    public String musteriisim(String eposta){
        String sifre="z";
        //SELECT sifre FROM `musteri` WHERE eposta='akifergunduz27@gmail.com'
    String sorgu ="Select adsoyad FROM musteri WHERE eposta='"+eposta+"'";
    try {
        statment=con.createStatement();
       
        ResultSet rs = statment.executeQuery(sorgu);
      while(rs.next()){
          sifre = rs.getString("adsoyad");
      }
         
    } catch (SQLException ex) {
        Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
    }
   return sifre;
    }
        public void musterisil(String eposta){
        try {
            statment = con.createStatement();
               String sorgu="Delete from musteri WHERE eposta='"+eposta+"'";
               int deger = statment.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
         public void kitapsil(String eposta){
        try {
            statment = con.createStatement();
               String sorgu="Delete from kitaplar WHERE barkodno='"+eposta+"'";
               int deger = statment.executeUpdate(sorgu);
        } catch (SQLException ex) {
            Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    public void siparisver(String kitapismi){
        System.out.println("1.");
       String sec="Select stok FROM kitaplar WHERE 'kitap adı'='"+kitapismi+"'";
       String stok="12";
        try {
        statment=con.createStatement();
       
        ResultSet rs = statment.executeQuery(sec);
      while(rs.next()){
          
          stok = rs.getString("stok");
          System.out.println(stok);
      }
         
    } catch (SQLException ex) {
        Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
    }
     int stokdegeri=Integer.parseInt(stok.trim());
    stokdegeri-=1;
    stok=String.valueOf(stokdegeri);
        System.out.println("2.");
    String sql = "UPDATE kitaplar set stok= '"+stok+"' WHERE 'kitap adı' ='"+kitapismi+"'";
         try {
             statment.executeUpdate(sql);
         } catch (SQLException ex) {
             Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
   
    
       public void guncelle(String eposta,String ilkeposta,String adres,String adsoyad,String sifre){
     String sql="UPDATE musteri SET eposta = ? , "
                +"adres = ? ,"
                +"adsoyad = ? ,"
                +"sifre = ? "
                + "WHERE eposta ='"+ilkeposta+"'";
        try {
            Statement ste =con.createStatement();
            PreparedStatement stat =con.prepareStatement(sql);
            stat.setString(1, eposta);
            stat.setString(2,adres);
               stat.setString(3,adsoyad);
                stat.setString(4,sifre);
                stat.executeUpdate();
             
            
                
        } catch (SQLException ex) {
            Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
           public void kitapekle(String fiyat,String kitapadi,String barkodno,String stok,String aciklama,String yazar){
    try {
        statment=con.createStatement();
        // INSERT INTO `hasta`(`tc`, `adsoyad`, `sifre`, `cinsyet`, `yas`, `eposta`, `dogumyeri`) VALUES (12,22,33,44,55,66,123)
String sorgu;
sorgu="INSERT INTO kitaplar (fiyat,stok,barkodno,aciklama,kitapadı,yazar) VALUES ";
String tamamla ="('"+fiyat+"','"+stok+"','"+barkodno+"','"+aciklama+"','"+kitapadi+"','"+yazar+"')";
sorgu=sorgu+tamamla;
        System.out.println(tamamla);
        statment.executeUpdate(sorgu);
    } catch (SQLException ex) {
        Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
public void kitapduzenle(String kitapadi,String yazar,String stok,String barkodno,String fiyat,String aciklama,String ilkbarkodno){
         String sql="UPDATE kitaplar SET stok = ? , "
                +"yazar= ? ,"
                +"kitapadı = ? ,"
                 +"fiyat= ? ,"
                 +"aciklama= ? ,"
                +"barkodno = ? "
                + "WHERE barkodno ='"+ilkbarkodno+"'";
        try {
            Statement ste =con.createStatement();
            PreparedStatement stat =con.prepareStatement(sql);
            stat.setString(1, stok);
            stat.setString(2,yazar);
               stat.setString(3,kitapadi);
                stat.setString(4,fiyat);
                stat.setString(5, aciklama);
                stat.setString(6, barkodno);
       stat.executeUpdate();
            
                
        } catch (SQLException ex) {
            Logger.getLogger(sql.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
