package Project;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class PlayLists {

	static int listSize = 5;

	public static DoublyLinkedList[] lists = new DoublyLinkedList[listSize];

	static Scanner scan = new Scanner(System.in);

	// Favorite List videos and creation
	public static DoublyLinkedList favList = new DoublyLinkedList();
	static DNode Fv1 = new DNode("Tom and jerry", "10:32");
	static DNode Fv2 = new DNode("Mr Bean", "12:01");
	static DNode Fv3 = new DNode("Top 10 fails", "8:02");
	static DNode Fv4 = new DNode("Shakira - waka waka", "3:40");
	static DNode Fv5 = new DNode("Top 10 goals all time", "5:41");
	static DNode Fv6 = new DNode("How to be a millionaire", "7:21");
	static DNode Fv7 = new DNode("Invest in your 20s", "12:00");
	static DNode Fv8 = new DNode("America's got talent magic show", "8:10");
	static DNode Fv9 = new DNode("Real Madrid top goals 2016", "9:12");
	static DNode Fv10 = new DNode("Barcelona wins Champions League", "30:21");

	// my list videos
	public static DoublyLinkedList myList = new DoublyLinkedList();
	static DNode MLv1 = new DNode("How to fix laptop", "7:32");
	static DNode MLv2 = new DNode("Print java material easy", "11:01");
	static DNode MLv3 = new DNode("Top 10 fails compilation 2020", "7:02");
	static DNode MLv4 = new DNode("Smack Down vs RAW 2011", "20:40");
	static DNode MLv5 = new DNode("Deep house music 1 hour remix", "1:00:41");
	static DNode MLv6 = new DNode("How to hack facebook account", "7:21");
	static DNode MLv7 = new DNode("A one minute video", "1:00");
	static DNode MLv8 = new DNode("Rap battle eminem vs ahmad helmy", "8:10");
	static DNode MLv9 = new DNode("Cristiano Ronaldo wins MVP", "9:12");
	static DNode MLv10 = new DNode("NBA: GSW vs ORL highlights", "12:21");

	// Mr Beast list videos
	public static DoublyLinkedList mrBeast = new DoublyLinkedList();
	static DNode mv1 = new DNode("I Spent 24 Hours Straight In Prison - Challenge", "14:51");
	static DNode mv2 = new DNode("I Opened A Free Car Dealership", "16:38");
	static DNode mv3 = new DNode("Giving 10,000 Presents To Kids For Christmas", "15:02");
	static DNode mv4 = new DNode("Anything You Can Carry, I'll Pay For Challenge", "20:40");
	static DNode mv5 = new DNode("Going Through The Same Drive Thru 1,000 Times", "15:41");
	static DNode mv6 = new DNode("Destroying My Friend's Car And Surprising Him With A New One", "7:21");
	static DNode mv7 = new DNode("Last To Take Hand Off $1,000,000 Keeps It", "20:10");
	static DNode mv8 = new DNode("Planting 20,000,000 Trees, My Biggest Project Ever!", "8:10");
	static DNode mv9 = new DNode("Spending $1,000,000 In 24 Hours", "15:32");
	static DNode mv10 = new DNode("Surviving 24 Hours Straight In A Rain Forest", "12:21");

	// Watch Later videos that was saved
	public static DoublyLinkedList watchLater = new DoublyLinkedList();
	static DNode w1 = new DNode("Watch how animals live", "10:00");
	static DNode w2 = new DNode("Hotel rooms under 500 dollars a night", "3:21");
	static DNode w3 = new DNode("Life under occupation for Palestininas", "6:23");
	static DNode w4 = new DNode("Learn english in 30 minutes", "29:59");
	static DNode w5 = new DNode("Use java to create a calculator", "12:24");
	static DNode w6 = new DNode("Software Engineer careers in USA", "3:50");
	static DNode w7 = new DNode("How google employees spend their day", "3:31");
	static DNode w8 = new DNode("A day at the life of a software engineer at facebook", "7:21");
	static DNode w9 = new DNode("Destroying my friend's house and buying him a new one", "15:21");
	static DNode w10 = new DNode("Q/A with the most talented NBA player", "5:23");

	public static DoublyLinkedList history = new DoublyLinkedList();

	public static void addToLists() { // O(1)
		favList.addFirst(Fv10);
		favList.addFirst(Fv9);
		favList.addFirst(Fv8);
		favList.addFirst(Fv7);
		favList.addFirst(Fv6);
		favList.addFirst(Fv5);
		favList.addFirst(Fv4);
		favList.addFirst(Fv3);
		favList.addFirst(Fv2);
		favList.addFirst(Fv1);

		myList.addFirst(MLv10);
		myList.addFirst(MLv9);
		myList.addFirst(MLv8);
		myList.addFirst(MLv7);
		myList.addFirst(MLv6);
		myList.addFirst(MLv5);
		myList.addFirst(MLv4);
		myList.addFirst(MLv3);
		myList.addFirst(MLv2);
		myList.addFirst(MLv1);

		mrBeast.addFirst(mv10);
		mrBeast.addFirst(mv9);
		mrBeast.addFirst(mv8);
		mrBeast.addFirst(mv7);
		mrBeast.addFirst(mv6);
		mrBeast.addFirst(mv5);
		mrBeast.addFirst(mv4);
		mrBeast.addFirst(mv3);
		mrBeast.addFirst(mv2);
		mrBeast.addFirst(mv1);

		watchLater.addFirst(w10);
		watchLater.addFirst(w9);
		watchLater.addFirst(w8);
		watchLater.addFirst(w7);
		watchLater.addFirst(w6);
		watchLater.addFirst(w5);
		watchLater.addFirst(w4);
		watchLater.addFirst(w3);
		watchLater.addFirst(w2);
		watchLater.addFirst(w1);

		history.addFirst(new DNode(Fv10.data, Fv10.time));
		history.addFirst(new DNode(Fv9.data, Fv9.time));
		history.addFirst(new DNode(Fv8.data, Fv8.time));
		history.addFirst(new DNode(Fv7.data, Fv7.time));
		history.addFirst(new DNode(Fv6.data, Fv6.time));
		history.addFirst(new DNode(Fv5.data, Fv5.time));
		history.addFirst(new DNode(Fv4.data, Fv4.time));
		history.addFirst(new DNode(Fv3.data, Fv3.time));
		history.addFirst(new DNode(Fv2.data, Fv2.time));
		history.addFirst(new DNode(Fv1.data, Fv1.time));

		history.addFirst(new DNode(w10.data, w10.time));
		history.addFirst(new DNode(w9.data, w9.time));
		history.addFirst(new DNode(w8.data, w8.time));
		history.addFirst(new DNode(w7.data, w7.time));
		history.addFirst(new DNode(w6.data, w6.time));
		history.addFirst(new DNode(w5.data, w5.time));
		history.addFirst(new DNode(w4.data, w4.time));
		history.addFirst(new DNode(w3.data, w3.time));
		history.addFirst(new DNode(w2.data, w2.time));
		history.addFirst(new DNode(w1.data, w1.time));

		history.addFirst(new DNode(mv10.data, mv10.time));
		history.addFirst(new DNode(mv9.data, mv9.time));
		history.addFirst(new DNode(mv8.data, Fv8.time));
		history.addFirst(new DNode(mv7.data, mv7.time));
		history.addFirst(new DNode(mv6.data, mv6.time));
		history.addFirst(new DNode(mv5.data, mv5.time));
		history.addFirst(new DNode(mv4.data, mv4.time));
		history.addFirst(new DNode(mv3.data, mv3.time));
		history.addFirst(new DNode(mv2.data, mv2.time));
		history.addFirst(new DNode(mv1.data, mv1.time));

		history.addFirst(new DNode(MLv10.data, MLv10.time));
		history.addFirst(new DNode(MLv9.data, MLv9.time));
		history.addFirst(new DNode(MLv8.data, MLv8.time));
		history.addFirst(new DNode(MLv7.data, MLv7.time));
		history.addFirst(new DNode(MLv6.data, MLv6.time));
		history.addFirst(new DNode(MLv5.data, MLv5.time));
		history.addFirst(new DNode(MLv4.data, MLv4.time));
		history.addFirst(new DNode(MLv3.data, MLv3.time));
		history.addFirst(new DNode(MLv2.data, MLv2.time));
		history.addFirst(new DNode(MLv1.data, MLv1.time));
	}
	public static void MoveVideos(int video_no, DoublyLinkedList list1, DoublyLinkedList list2) {//3n+11+13+3n+11=6n+35=O(n)
		list1.removeAt(video_no);//3n+11
		list2.addFirst(list1.removeAt(video_no));//13+3n+11
	}

	public static void copyVideos(int video_no, DoublyLinkedList list1, DoublyLinkedList list2) {//18+5n=O(n)
		DNode pointer = list1.header;//3
		for (int i = 0; i <= video_no; i++) {//1+n+1+2n
			pointer = pointer.next;//2n
		}
		list2.addFirst(pointer);//13
	}

	public static void videoSearch() {//O(n)
		System.out.println("Search:");//1
		String search = scan.nextLine();//3
		DNode pointer = favList.header.next;//4
		for (int i = 0; i < PlayLists.favList.size - 1; i++) {//1+n+2n
			if (pointer.data.contains(search)) {//3n
				System.out.println(pointer.data + "\nin Favorite List" + ""
						+ "\nDuration: \n" + pointer.time + "\n");//3n
			}
			pointer = pointer.next;//2n
		}
		DNode pointer2 = myList.header.next;//4
		for (int i = 0; i < PlayLists.myList.size - 1; i++) {//1+n+2n
			if (pointer2.data.contains(search)) {//3n
				System.out.println(pointer2.data + "\nin My List" + "\nDuration: \n" + pointer2.time + "\n");//3n
			}
			pointer2 = pointer2.next;//2n
		}
		DNode pointer3 = mrBeast.header.next;//4
		for (int i = 0; i < PlayLists.mrBeast.size - 1; i++) {//1+n+2n
			if (pointer3.data.contains(search)) {//3n
				System.out.println(pointer3.data + "\nin Mr Beast" + "\nDuration: \n" + pointer3.time + "\n");//3n
			}
			pointer3 = pointer3.next;//2n
		}
		DNode pointer4 = watchLater.header.next;//4
		for (int i = 0; i < PlayLists.watchLater.size - 1; i++) {//1+n+2n
			if (pointer4.data.contains(search)) {//3n
				System.out.println(pointer4.data + "\nin Watch Later" + "\nDuration: " + pointer4.time + "\n");//3n
			}
			pointer4 = pointer4.next;//2n
		}

	}

	public static void playVideo(DoublyLinkedList list, int videioNo) {//3+1+n+1+2n+2n+3=8+5n=O(n)
		DNode pointer = list.header;//3
		for (int i = 1; i <= videioNo; i++) {//1+n+1+2n
			pointer = pointer.next;//2n
		}
		System.out.println(pointer.data + "\nlength : " + pointer.time);//3
	}
	public static void listOperations(int vidNum, DoublyLinkedList list) {
		String name;
		String length;
		switch (vidNum) {
		case 0:
			System.out.println("Type the number of the video you wish to play: ");
			int play = scan.nextInt();
			PlayLists.playVideo(list, play);
			System.out.println("To play the next video press 1, \nOr to play the previous video press 0, "
					+ "\nPress any other number to return to the main menu");
			int choice = scan.nextInt();
			while (choice == 1 || choice == 0) {
				if (choice == 1) {
					int randomX = (int) (Math.random() * list.size + 1);

					if (play != randomX) {
						PlayLists.playVideo(list, randomX);
					} else
						play = randomX;

				} else if (choice == 0) {
					int randomX = (int) (Math.random() * 10 + 1);
					if (play != randomX) {
						PlayLists.playVideo(list, randomX);
					}
					play = randomX;
				}
				System.out.println("To play the next video  press 1, \nOr to play the previous video press 0");
				choice = scan.nextInt();

			}
			break;
		case 1:
			System.out.println("Type the number of the video you wish to play: ");
			play = scan.nextInt();
			PlayLists.playVideo(list, play);
			System.out.println("To play the next video press 1, \nOr to play the previous video press 0, "
					+ "\nPress any other number to return to the main menu");
			choice = scan.nextInt();
			while (choice == 1 || choice == 0) {

				if (choice == 1) {
					play++;
					PlayLists.playVideo(list, play);

				} else if (choice == 0) {
					play--;
					PlayLists.playVideo(list, play);

				}
				System.out.println("To play the next video  press 1, " + "\nOr to play the previous video press 0");
				choice = scan.nextInt();

			}
			break;
		case 2:
			list.autoPlayList();
			break;

		case 3:
			System.out.println("Type the name of the video:");
			name = scan.next();
			System.out.println("Enter the length of the video as 00:00");
			length = scan.next();
			DNode addedVideo = new DNode(name, length);
			list.addFirst(addedVideo);
			System.out.println("Video saved!");
			DNode n1 =  new DNode(addedVideo.getData(), addedVideo.getTime());
			PlayLists.history.addFirst(n1);
			break;
		case 4:
			System.out.println("Type the number of the video you want to remove: ");
			int remove = scan.nextInt();
			list.removeAt(remove);
			System.out.println("The video was removed sucessfully!");
			break;
		case 5:
			System.out.println("Type the number of the video you want to move: ");
			int numMove = scan.nextInt();
			System.out.println("Type the number of the list you want to move the video to:");
			int numListToMove = scan.nextInt();
			MoveVideos(numMove, list, lists[numListToMove - 1]);
			break;
		case 6:
			list.reverse(list);
			break;
			
		case 7:
			System.out.println("Going back to the main menu...");
			break;
		default:
			break;
		}
	}

	public static void shuffleAllVideos() {//O(n)
		favList.trailer.prev.next = myList.header.next;//6
		myList.trailer.prev.next = watchLater.header.next;//6
		watchLater.trailer.prev.next = mrBeast.header.next;//6
		mrBeast.trailer.prev.next = favList.header.next;//6
		int numOfVideos = myList.size+watchLater.size+mrBeast.size+ favList.size;//6
		DNode[] videos = new DNode[numOfVideos];//3
		DNode pointer = favList.header.next;//3
		for (int i = 0; i < numOfVideos; i++) {//1+n+1+2n
			videos[i] = pointer;//2n
			pointer = pointer.next;//2n

		}

		Random rand = new Random();//2

		for (int i = 0; i < videos.length; i++) {//1+n+1+2n
			int randomIndexToSwap = rand.nextInt(videos.length);//4n
			DNode temp = videos[randomIndexToSwap];//3n
			videos[randomIndexToSwap] = videos[i];//3n
			videos[i] = temp;//2n
		}
	
		for (int i = 0; i < videos.length; i++) {//1+n+1+2n
			System.out.println(i+1 + ". " + videos[i].data + "\n	Duration: " + videos[i].time);//3n
			try {
				Thread.sleep(1000);//2n
			} catch (InterruptedException e) {
				e.printStackTrace();//n
			}
		}
	}
}


