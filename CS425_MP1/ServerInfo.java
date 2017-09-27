//package mp1_grep;

public class ServerInfo {
	private int vmNum;
	private String ip;
	private int port;
	//private String path;
	public ServerInfo(int num, String ip, int port){
		this.vmNum = num;
		this.ip = ip;
		//this.path = path;
		this.port = port;
	}
	public int getNum(){
		return vmNum;
	}
	public String getIp(){
		return ip;
	}
	public int getPort(){
		return port;
	}
	public void ipSetter(String ip){
		this.ip = ip;
	}
}
