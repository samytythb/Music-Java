package View;

import Models.Song;

import java.util.Scanner;

public class SongView {
    public Song insert() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Title Song: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter Genre Song: ");
        String genre = scanner.nextLine().trim();
        System.out.print("Enter Release Year Song: ");
        int releaseYear = scanner.nextInt();
        System.out.print("Enter Duration Song: ");
        double duration = scanner.nextDouble();
        return new Song(title, genre, releaseYear, duration);
    }
    public String searchByTitle(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Title Song: ");
        String title = scanner.nextLine().trim();
        return title;
    }
    public String searchByArtist(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Artist Of Song: ");
        String artist = scanner.nextLine().trim();
        return artist;
    }
    public void displaySongDetail(String Title,String Genre,int ReleaseYear, Double Duration,String NameAlbum,String Artist ){
        System.out.println("Title : "+Title);
        System.out.println("Genre : "+Genre);
        System.out.println("ReleaseYear : "+ReleaseYear);
        System.out.println("Duration : "+Duration);
        System.out.println("Album : "+NameAlbum);
        System.out.println("Artist : "+Artist);
    }
    public Song update() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter New Title Song: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter New Genre Song: ");
        String genre = scanner.nextLine().trim();
        System.out.print("Enter New Release Year Song: ");
        int releaseYear = scanner.nextInt();
        System.out.print("Enter New Duration Song: ");
        double duration = scanner.nextDouble();
        return new Song(title, genre, releaseYear, duration);
    }
    public void displayListSong(String Title,String Artist ){
        System.out.print("Title : "+Title);
        System.out.println("- Artist : "+Artist);
    }
}
