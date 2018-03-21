package DATA;

public class article {
       int id;
       String author;
       String title;
       String summary;
       String publish_time;
       String get_time;
       public article(int id,String author,String title,String summary,String publish_time,String get_time) {
    	   this.id=id;
    	   this.author=author;
    	   this.title=title;
    	   this.summary=summary;
    	   this.publish_time=publish_time;
    	   this.get_time=get_time;
       }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}
	public String getGet_time() {
		return get_time;
	}
	public void setGet_time(String get_time) {
		this.get_time = get_time;
	}
}
