package org.plum.tools.ui;
 
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
 
//ignore "bytes" when return json format
@JsonIgnoreProperties({"bytes"}) 
public class FileMeta {

    private int id;
    private String name;
    private String size;
    private String thumbnailUrl;
    private String error;
    private String url;
    private String deleteUrl;
    private String deleteWithCredentials;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getDeleteUrl() {
		return deleteUrl;
	}
	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}
	public String getDeleteWithCredentials() {
		return deleteWithCredentials;
	}
	public void setDeleteWithCredentials(String deleteWithCredentials) {
		this.deleteWithCredentials = deleteWithCredentials;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
 


}