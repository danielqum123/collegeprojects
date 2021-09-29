package Project;

import java.util.Scanner;

public class Menu {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		PlayLists.addToLists();
		PlayLists.lists[0] = PlayLists.favList;
		PlayLists.lists[1] = PlayLists.history;
		PlayLists.lists[2] = PlayLists.watchLater;
		PlayLists.lists[3] = PlayLists.myList;
		PlayLists.lists[4] = PlayLists.mrBeast;
		int input = 0;
		while (input <= 9) {
			System.out.println("Playlists:");
			System.out.println("1. Favourites" + "\n2. History" + "\n3. Watch Later" + "\n4. My List" + "\n5. Mr Beast"
					+ "\n6. Search for a video " + "\n7. Shuffle videos across all lists "
					+ "\n8. To exit the program.");
			input = scan.nextInt();
			int in;
			switch (input) {
			case 1:
				System.out.println("Welcome to Favourites list!" + "\nFavourite list videos:");
				PlayLists.favList.printList();
				System.out.println("Type 0 to shuffle videos\n" + "Type 1 to play videos in order\n"
						+ "Type 2 to autoplay a playlist\n" + "Type 3 to add a new video\n"
						+ "Type 4 to delete a video \n" + "Type 5 to move a video from this list to another list\n"
						+ "Type 6 to play the list in reverse\n"
						+ "Type 7 to go back to main menu\n");
				
				in = scan.nextInt();
				PlayLists.listOperations(in, PlayLists.favList);
				break;

			case 2:
				System.out.println("Welcome to History list!" + "\nYour last watched videos are:");
				System.out.println("");
				PlayLists.history.printList();
				break;
			case 3:
				System.out.println(
						"Welcome to Watch Later list!" + "\nThese are the videos you scheduled to watch later:");
				PlayLists.watchLater.printList();
				System.out.println("Type 0 to shuffle videos\n" + "Type 1 to play videos in order\n"
						+ "Type 2 to autoplay a playlist\n" + "Type 3 to add a new video\n"
						+ "Type 4 to delete a video \n" + "Type 5 to move a video from this list to another list\n"
						+ "Type 6 to play the list in reverse\n"
						+ "Type 7 to go back to main menu\n");
				in = scan.nextInt();
				PlayLists.listOperations(in, PlayLists.watchLater);
				break;
			case 4:
				System.out.println("Welcome to My List!" + "\nThese are the videos you added:");
				PlayLists.myList.printList();
				System.out.println("Type 0 to shuffle videos\n" + "Type 1 to play videos in order\n"
						+ "Type 2 to autoplay a playlist\n" + "Type 3 to add a new video\n"
						+ "Type 4 to delete a video \n" + "Type 5 to move a video from this list to another list\n"
						+ "Type 6 to play the list in reverse\n"
						+ "Type 7 to go back to main menu\n");
				in = scan.nextInt();
				PlayLists.listOperations(in, PlayLists.myList);
				break;

			case 5:
				System.out.println("Welcome to MrBeast list!" + "\nMr beast's latest videos are:");
				PlayLists.mrBeast.printList();
				System.out.println("Type 0 to shuffle videos\n" + "Type 1 to play videos in order\n"
						+ "Type 2 to autoplay a playlist\n" + "Type 3 to add a new video\n"
						+ "Type 4 to delete a video \n" + "Type 5 to move a video from this list to another list\n"
						+ "Type 6 to play the list in reverse\n"
						+ "Type 7 to go back to main menu\n");
				in = scan.nextInt();
				PlayLists.listOperations(in, PlayLists.mrBeast);
				break;
			case 6:
				PlayLists.videoSearch();
				break;

			case 7:
				PlayLists.shuffleAllVideos();
				break;
			default:
				System.out.println("Good Bye! :)\n"
						+ "\nProject done by: "
						+ "\nDanial Qumsieh"
						+ "\nFeras Bannoura"
						+ "\nHakam Jubran");
				System.exit(0);
				break;
			}

		}
	}

}
