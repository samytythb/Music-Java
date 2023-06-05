package Controllers;

import Models.Album;
import Models.Dao;
import Models.Song;
import View.SongView;

import java.sql.*;

public class SongController {
    public boolean insertSong(Song song, Album album) {
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;
        int idAlbum = 0;
        int idSong = 0;
        try (
                Connection conn = DriverManager.getConnection(Dao.getDB_URl(), Dao.getDbUser(), Dao.getDbPassword());
                CallableStatement statement = conn.prepareCall("{CALL InsertSong(?, ?, ?, ?, ?, ?)}");
        ) {
            statement.setString(1, song.getTitle());
            statement.setString(2, song.getGenre());
            statement.setInt(3, song.getReleaseYear());
            statement.setDouble(4, song.getDuration());
            statement.setString(5, album.getName());
            statement.setString(6, album.getArtist());
            statement.execute();

            if (statement.getMoreResults()) {
                resultSet1 = statement.getResultSet();


                while (resultSet1.next()) {

                    idAlbum = resultSet1.getInt("Id");
                }
            }
            if (statement.getMoreResults()) {
                resultSet2 = statement.getResultSet();


                while (resultSet2.next()) {

                    idSong = resultSet2.getInt("InsertedID");
                    System.out.println(idSong + "   " + idAlbum);
                }
            }
            if (idAlbum > 0 && idSong > 0) {
                Statement stmt = conn.createStatement();
                String insertToAlbumDetails = "EXEC InsertAlbumDetails " + idAlbum + ", " + idSong;
                stmt.execute(insertToAlbumDetails);
            }
            System.out.println("Song has been inserted");
            resultSet1.close();
            resultSet2.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean searchSong(String title) {
        try (
                Connection conn = DriverManager.getConnection(Dao.getDB_URl(), Dao.getDbUser(), Dao.getDbPassword());
                Statement stmt = conn.createStatement();
        ) {
            String search = "EXEC SearchSongByTitle '" + title+"'";
            stmt.execute(search);
            ResultSet resultSet = stmt.getResultSet();
            if (resultSet.isBeforeFirst()) {

                while (resultSet.next()) {

                    String titleSong = resultSet.getString("Title");
                    String genre = resultSet.getString("Genre");
                    int releaseYear = resultSet.getInt("ReleaseYear");
                    Double duration = resultSet.getDouble("Duration");
                    String album = resultSet.getString("NameAlbum");
                    String artist = resultSet.getString("Artist");
                    SongView songView=new SongView();
                    songView.displaySongDetail(titleSong,genre,releaseYear,duration,album,artist);
                }
                resultSet.close();
            }else{
                System.out.println("Music track not found");
                resultSet.close();
            }
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean searchSongByArtist(String artist) {
        try (
                Connection conn = DriverManager.getConnection(Dao.getDB_URl(), Dao.getDbUser(), Dao.getDbPassword());
                Statement stmt = conn.createStatement();
        ) {
            String search = "EXEC SearchSongByArtist '" + artist+"'";
            stmt.execute(search);
            ResultSet resultSet = stmt.getResultSet();
            if (resultSet.isBeforeFirst()) {

                while (resultSet.next()) {

                    String titleSong = resultSet.getString("Title");
                    String genre = resultSet.getString("Genre");
                    int releaseYear = resultSet.getInt("ReleaseYear");
                    Double duration = resultSet.getDouble("Duration");
                    String album = resultSet.getString("NameAlbum");
                    String artistOfSong = resultSet.getString("Artist");
                    SongView songView=new SongView();
                    songView.displaySongDetail(titleSong,genre,releaseYear,duration,album,artistOfSong);
                }
                resultSet.close();
            }else {
                System.out.println("Artist not found");
                resultSet.close();
            }
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean updateSong(String title) {
        try (
                Connection conn = DriverManager.getConnection(Dao.getDB_URl(), Dao.getDbUser(), Dao.getDbPassword());
                Statement stmt = conn.createStatement();
        ) {
            String search = "EXEC SearchSongByTitle '" + title+"'";
            stmt.execute(search);
            ResultSet resultSet = stmt.getResultSet();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {

                    String titleSong = resultSet.getString("Title");
                    String genre = resultSet.getString("Genre");
                    int releaseYear = resultSet.getInt("ReleaseYear");
                    Double duration = resultSet.getDouble("Duration");
                    String album = resultSet.getString("NameAlbum");
                    String artist = resultSet.getString("Artist");
                    SongView songView=new SongView();
                    songView.displaySongDetail(titleSong,genre,releaseYear,duration,album,artist);
                }
                SongView songView= new SongView();
                Song song= songView.update();
                String update="EXEC UpdateSong '"+title+"','"+song.getTitle()+"','"+song.getGenre()+"',"+song.getReleaseYear()+","+song.getDuration();
                stmt.execute(update);
                System.out.println("Updated Success");
                resultSet.close();

            }else{
                System.out.println("Music track not found");
                resultSet.close();
            }

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean getListSongs() {
        try (
                Connection conn = DriverManager.getConnection(Dao.getDB_URl(), Dao.getDbUser(), Dao.getDbPassword());
                Statement stmt = conn.createStatement();
        ) {
            String listSongs = "EXEC ListSong";
            stmt.execute(listSongs);
            ResultSet resultSet = stmt.getResultSet();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {

                    String titleSong = resultSet.getString("Title");
                    String artist = resultSet.getString("Artist");
                    SongView songView=new SongView();
                    songView.displayListSong(titleSong,artist);
                }
            }else{
                System.out.println("No music track exists");
                resultSet.close();
            }

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
