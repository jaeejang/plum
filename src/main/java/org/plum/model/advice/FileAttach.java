package org.plum.model.advice;

public class FileAttach {
    private Integer id;

    private String filename;

    private String path;

    private Integer attachtype;

    private Integer sourceid;
    
    private String filetype;
    
    private String filesize;

    public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getAttachtype() {
        return attachtype;
    }

    public void setAttachtype(Integer attachtype) {
        this.attachtype = attachtype;
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }
}