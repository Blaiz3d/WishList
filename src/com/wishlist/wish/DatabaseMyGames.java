/**
 * Stores and manages the wish listed games
 */

package com.wishlist.wish;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class DatabaseMyGames
{
    private static ObservableList<MyGame> game = FXCollections.observableArrayList();

    /**
     * Adds a wish listed game to the List
     */
    public static void addToMyGames(String title, String appid, String currentDate)
    {
        game.add(new MyGame(title, appid, currentDate));
    }

    /**
     *
     * Removes a game from the wish list
     */
    public static void removeGame(MyGame row )
    {
        for (int i = 0; i < game.size(); i++)
        {
            if (game.get(i) == row)
            {
                game.remove(i);
            }
        }
    }

    /**
     * Saves the wish listed games to a file
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static void writeToFile() throws FileNotFoundException, UnsupportedEncodingException
    {
        PrintWriter writer = new PrintWriter("wishlist.txt", "UTF-8");
        for (int i = 0; i < game.size(); i++)
        {
            writer.print(game.get(i).getTitle() + "," + game.get(i).getAppid() + "," + game.get(i).getDateAdded() + "\n");
        }
        System.out.println(">> Saved to file");
        writer.close();
    }

    /**
     * Loads the wish listed games from a file
     */
    public static void readFromFile()
    {
        File file = new File("wishlist.txt");
        try
        {
            Scanner input = new Scanner(file);
            String line;
            String[] lineArray;
            String title;
            String appid;
            String dateAdded;

            int gameCount = 0;

            while (input.hasNextLine())
            {
                line = input.nextLine();
                lineArray = line.split(",");
                title = lineArray[0];
                appid = lineArray[1];
                dateAdded = lineArray[2];

                game.add(new MyGame(title, appid, dateAdded));
                gameCount++;
            }

            System.out.println(">> Read the wishlist to a List");
            System.out.println("    >> Total games added: " + gameCount);
        }
        catch (FileNotFoundException e)
        {
            System.out.println(">> Wishlist file not found");
        }
    }

    /**
     * @return the list of wish listed games
     */
    public static ObservableList<MyGame> getGamesMy()
    {
        return game;
    }
}
