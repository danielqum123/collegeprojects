package Project;

public class DoublyLinkedList {
	public DNode header;
	public DNode trailer;
	int size;
	String name;

	public DoublyLinkedList() {
		header = new DNode();
		trailer = new DNode();
		header.next = trailer;
		trailer.prev = header;
	}

	DoublyLinkedList(String nameEntered) {
		this.name = nameEntered;
		header = new DNode();
		trailer = new DNode();
		header.next = trailer;
		trailer.prev = header;
	}

	public void addFirst(DNode newDNode) {//13 = O(1)

		DNode pointer = header.next;//2
		newDNode.next = header.next;//3
		header.next = newDNode;//2
		// newDNode.next.prev=newDNode;
		pointer.prev = newDNode;//2
		newDNode.prev = header;//2
		size++;//2
	}
	
	public void printList() {//=2+1+3n+2n+2n=7n+3=O(n)
		DNode pointer; //1
		pointer = header.next;//2
		int videoNumber = 1;//1
		while (pointer != trailer) {
			System.out.println(videoNumber + ". " + pointer.data + "\nDuration: " + pointer.time + " Minutes\n");//3n
			pointer = pointer.next;//2n
			videoNumber++;//2n
		}
	}

	public DNode removeAt(int index) {//2+3n++2+4+2+1=3n+11=O(n)
		DNode tmp = header;//2
		for (int i = 0; i < index; i++) {//1+n+1+2n =3n+2
			tmp = tmp.next;//2n
		}
		if (tmp.prev != null) {
			tmp.prev.next = tmp.next;//4
		} else {
			header = tmp.next;//2
		}
		if (tmp.next != null) {
			tmp.next.prev = tmp.prev;//4
		}
		size--;//2
		return tmp;//1
	}
	public static void reverse(DoublyLinkedList list) {//2+3+2n+3n+2n+2n+3+3+2+1+3+3n+2n+2n=17+16n=O(n)
		DNode temp = null;//2
		DNode current = list.header;//3
		while (current != null) {
			temp = current.prev;//2n
			current.prev = current.next;//3n
			current.next = temp;//2n
			current = current.prev;//2n
		}
		if (temp != null) {
			list.header = temp.prev;//3
		}
		DNode pointer;//1
		pointer = list.header.next;//3
		int videoNumber = 1;//2
		while (pointer.next != list.trailer.prev) {
			System.out.println(videoNumber + ". " + pointer.data + "\nDuration: " + pointer.time + " Minutes\n");//3n
			pointer = pointer.next;//2n
			try {
				Thread.sleep(1000);//2n
			} catch (InterruptedException e) {
				e.printStackTrace();//n
			}
			videoNumber++;//2n
		}
	}

	public void autoPlayList() {//1+2+2+3n+1n+n+2n+2n=5+9n=O(n)
		DNode pointer;//1
		pointer = header.next;//2
		int videoNumber = 1;//2
		while (pointer != trailer) {
			System.out.println(videoNumber + ". " + pointer.data + "\nDuration: " + pointer.time + " Minutes\n");//3n
			try {
				Thread.sleep(3000);//1n
			} catch (InterruptedException e) {
				e.printStackTrace();//n
			}
			pointer = pointer.next;//2n
			videoNumber++;//2n
		}
	}
	
}




