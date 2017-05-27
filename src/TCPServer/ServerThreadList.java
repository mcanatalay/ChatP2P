/**
 * This class holds created ServerThread by TCPServer
 * for each new connection. It holds also a index_list
 * in order to track shifts after a remove operation
 */

package TCPServer;

import java.util.ArrayList;

public class ServerThreadList {
	private ArrayList<ServerThread> main_list;
	private ArrayList<Integer> index_list;
	
	public ServerThreadList(){
		main_list = new ArrayList<ServerThread>();
		index_list = new ArrayList<Integer>();
	}
	
	public int getRealSize(){
		return main_list.size();
	}
	
	public int getSize(){
		return index_list.size();
	}
	
	//add ServerThread to list
	public int add(ServerThread input){
		if(main_list.add(input)){
			int position = main_list.size()-1;
			index_list.add(position);
			return index_list.size()-1;
		} else{
			return -1;
		}
	}
	
	//removes ServerThread in given index
	public void remove(int index){
		if(index > index_list.size()-1){
			return;
		}
		
		int real_index = index_list.get(index).intValue();
				
		if(real_index != -1){
			main_list.remove(real_index);
			index_list.set(index, -1);
			//Shift all next items to left
			for(int i = index+1; index_list.size() > i; i++){
				int val = index_list.get(i);
				//if they are not already removed
				if(val != -1)
					index_list.set(i, val-1);
			}
		}
	}
	
	//Returns the ServerThread in given index
	public ServerThread get(int index){
		if(index > index_list.size()-1){
			return null;
		}
		
		int real_index = index_list.get(index).intValue();
		if(real_index != -1){
			return	main_list.get(real_index);
		} else{
			return null;
		}
	}
}
