package com.example.new2.veganwebsite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VeganwebsiteDAO {
    private Connection conn;
    private ResultSet rs;

    public VeganwebsiteDAO() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/VeganWebSite";
            String dbID = "root";
            String dbPassword = "aa147147!";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL,dbID,dbPassword);


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDate() {
        String SQL = "SELECT NOW()";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                return rs.getString(1);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return ""; //데이터베이스 오류
    }

    public int getNext() {
        String SQL = "SELECT boardID FROM BOARD ORDER BY boardID DESC"; //VeganWebSite 아니면 BOARD
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                return rs.getInt(1)+1;
            }
            return 1; //첫 번째 게시물인 경우

        }catch (Exception e) {
            e.printStackTrace();
        }

        return -1; //데이터베이스 오류
    }

    public int write(String boardTitle,String userID, String boardContent) {
        String SQL = "INSERT INTO BOARD VALUES (?,?,?,?,?,?)"; //VeganWebSite 아니면 BOARD
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, getNext());
            pstmt.setString(2, boardTitle);
            pstmt.setString(3, userID);
            pstmt.setString(4, getDate());
            pstmt.setString(5, boardContent);
            pstmt.setInt(6,1);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; //데이터베이스 오류
    }

    public ArrayList<Veganwebsite> getList(int pageNumber){
        String SQL = "SELECT * FROM BOARD WHERE boardID < ? AND boardAvailable = 1 ORDER BY  boardID DESC LIMIT 10";
        ArrayList<Veganwebsite> list = new ArrayList<Veganwebsite>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Veganwebsite veganwebsite = new Veganwebsite();
                veganwebsite.setBoardID(rs.getInt(1));
                veganwebsite.setBoardTitle(rs.getString(2));
                veganwebsite.setUserID(rs.getString(3));
                veganwebsite.setBoardDate(rs.getString(4));
                veganwebsite.setBoardContent(rs.getString(5));
                veganwebsite.setBoardAvailable(rs.getInt(6));
                list.add(veganwebsite);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean nextPage(int pageNumber) {
        String SQL = "SELECT * FROM BOARD WHERE boardID < ? AND boardAvailable = 1";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Veganwebsite getVeganwebsite(int boardID) {
        String SQL = "SELECT * FROM BOARD WHERE boardID  = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, boardID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Veganwebsite veganwebsite = new Veganwebsite();
                veganwebsite.setBoardID(rs.getInt(1));
                veganwebsite.setBoardTitle(rs.getString(2));
                veganwebsite.setUserID(rs.getString(3));
                veganwebsite.setBoardDate(rs.getString(4));
                veganwebsite.setBoardContent(rs.getString(5));
                veganwebsite.setBoardAvailable(rs.getInt(6));
                return veganwebsite;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int update(int boardID, String boardTitle, String boardContent) {
        String SQL = "UPDATE BOARD SET boardTitle = ?, boardContent = ? WHERE boardID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, boardTitle);
            pstmt.setString(2, boardContent);
            pstmt.setInt(3, boardID);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; //데이터베이스 오류
    }

    public int delete(int boardID) {
        String SQL = "UPDATE BOARD SET boardAvailable = 0 WHERE boardID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, boardID);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; //데이터베이스 오류
    }
}