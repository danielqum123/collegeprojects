package Project;
public class DNode {
	public DNode getNext() {
		return next;
	}
	public void setNext(DNode next) {
		this.next = next;
	}
	public DNode getPrev() {
		return prev;
	}
	public void setPrev(DNode prev) {
		this.prev = prev;
	}
	public String getData() {
		return data;
	}
	public String getTime() {
		return time;
	}
	String data;
	String time;
	DNode next;
	DNode prev; 
	
	DNode(String i, String playTime){
		data=i;
		time = playTime;
	}
	DNode(){
	}
}
