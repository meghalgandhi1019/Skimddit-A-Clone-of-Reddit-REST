package LinksDAO;

public class linkData {

	
	public int id;
	public String name;
	public String url;
	public String username;
	public int votes;
	
	public linkData(int i,String j,String k,String l,int votes)
	{
		this.id = i;
		this.name = j;
		this.url = k;
		this.votes = votes;
		this.username = l; 
		
	}
	
	public linkData()
	{
		
	}
}
