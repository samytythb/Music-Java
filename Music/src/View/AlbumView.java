package View;

import Models.Album;

import java.util.Scanner;

public class AlbumView {
    public Album insertSongToAlbum(){
        Scanner scanner= new Scanner(System.in);
        System.out.print("Enter Name Album: ");
        String album=scanner.nextLine().trim();
        System.out.print("Enter Name Artist: ");
        String artist=scanner.nextLine().trim();
        return new Album(album,artist);
    }
    public Album update(){
        Scanner scanner= new Scanner(System.in);
        System.out.print("Enter New Name Album: ");
        String album=scanner.nextLine().trim();
        System.out.print("Enter New Name Artist: ");
        String artist=scanner.nextLine().trim();
        return new Album(album,artist);
    }
}
