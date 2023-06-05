package View;

import Controllers.SongController;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        menu();
    }
    public static void menu()  {
        Scanner scanner= new Scanner(System.in);
        int choice;
        do{
            System.out.println("1. Add Song\n" +
                    "2. Search Song by Title\n"+
                    "3. Search Song by Artist\n"+
                    "4. Update Song \n"+
                    "6. List Song \n"+
                    "0.Exit");
            System.out.print("Your choice: ");
            choice=scanner.nextInt();
            switch (choice){
                case 1 : {
                    SongController songController = new SongController();
                    SongView songView = new SongView();
                    AlbumView albumView = new AlbumView();
                    songController.insertSong(songView.insert(), albumView.insertSongToAlbum());
                    try {
                        TimeUnit.SECONDS.sleep(2);

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 2 : {
                    SongController songController = new SongController();
                    SongView songView=new SongView();
                    songController.searchSong(songView.searchByTitle());
                    break;
                }
                case 3 : {
                    SongController songController = new SongController();
                    SongView songView=new SongView();
                    songController.searchSongByArtist(songView.searchByArtist());
                    break;
                }
                case 4 : {
                    SongController songController=new SongController();
                    SongView songView= new SongView();
                    songController.updateSong(songView.searchByTitle());
                    break;
                }
                case 6 : {
                    SongController songController=new SongController();
                    songController.getListSongs();
                    break;
                }
                case 0 :
                    System.exit(0);
            }
        }while (choice>=0);
    }
}